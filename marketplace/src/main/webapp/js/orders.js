document.addEventListener("DOMContentLoaded", loadOrders);

async function loadOrders() {
  const utilizador = JSON.parse(localStorage.getItem("utilizador"));
  const ordersContainer = document.getElementById("ordersContainer");

  if (!utilizador) {
    window.location.href = "login.html";
    return;
  }

  try {
    const response = await fetch(`/api/encomendas/comprador/${utilizador.id}`);

    if (!response.ok) {
      throw new Error("Could not load orders");
    }

    const encomendas = await response.json();

    if (encomendas.length === 0) {
      ordersContainer.innerHTML = "<p>You have no orders yet.</p>";
      return;
    }

    ordersContainer.innerHTML = "";

    encomendas.forEach((encomenda) => {
      const orderElement = document.createElement("div");
      orderElement.classList.add("order-card");

      let linhasHTML = "";

      encomenda.linhas.forEach((linha) => {
        linhasHTML += `
                <div class="order-line">
                    <div>
                        <p class="product-name">${linha.nomeProduto}</p>
                        <p class="product-quantity">Quantity: ${linha.quantidade}</p>
                    </div>

                    <p class="product-price">
                        ${linha.preco.toFixed(2)}€
                    </p>
                </div>
            `;
      });

      orderElement.innerHTML = `
            <div class="order-header">
                <h3>Order #${encomenda.id}</h3>
                <span class="order-status">${encomenda.estado}</span>
            </div>

            <div class="order-lines">
                ${linhasHTML}
            </div>

            <div class="order-total">
                <span>Total</span>
                <strong>${encomenda.preco_total.toFixed(2)}€</strong>
            </div>

            ${
              encomenda.estado === "Pendente"
                ? ` 
                <button class="pay-order-button" data-id="${encomenda.id}"> 
                    Pay now 
                </button> 
            `
                : ""
            }
        `;

      ordersContainer.appendChild(orderElement);

      orderElement
        .querySelector(".pay-order-button")
        ?.addEventListener("click", () => {
          const paymentModal = document.getElementById("paymentModal");
          document.getElementById("paymentOrderId").textContent =
            `Order #${encomenda.id}`;
          document.getElementById("paymentTotal").textContent =
            `Total: ${encomenda.preco_total.toFixed(2)}€`;
          paymentModal.dataset.orderId = encomenda.id;
          paymentModal.hidden = false;
        });
    });
  } catch (error) {
    console.error("Error loading orders:", error);
    ordersContainer.innerHTML = "<p>Could not load your orders.</p>";
  }
}

document.getElementById("closePaymentButton").addEventListener("click", () => {
  document.getElementById("paymentModal").hidden = true;
});

document
  .getElementById("confirmPaymentButton")
  .addEventListener("click", async () => {
    const paymentModal = document.getElementById("paymentModal");
    const orderId = paymentModal.dataset.orderId;
    const button = document.getElementById("confirmPaymentButton");

    button.disabled = true;
    button.textContent = "Processing...";

    try {
      const response = await fetch(
        `/api/encomendas/${orderId}?estado=${encodeURIComponent("Em processamento")}`,
        {
          method: "PUT",
        },
      );

      if (!response.ok) {
        throw new Error("Could not update order status");
      }

      paymentModal.hidden = true;
      const successMessage = document.getElementById("orderSuccessMessage");
      successMessage.hidden = false;
      await loadOrders();
      setTimeout(() => {
        successMessage.hidden = true;
      }, 3000);
    } catch (error) {
      console.error("Payment simulation error:", error);
      alert("Could not confirm the simulated payment. Please try again.");
    } finally {
      button.disabled = false;
      button.textContent = "Confirm simulated payment";
    }
  });
