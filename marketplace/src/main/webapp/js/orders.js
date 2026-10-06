document.addEventListener("DOMContentLoaded", loadOrders);

async function loadOrders() {

    const utilizador = JSON.parse(localStorage.getItem("utilizador"));
    const ordersContainer = document.getElementById("ordersContainer");

    if (!utilizador) {
        window.location.href = "login.html";
        return;
    }

    try {
        const response = await fetch(
            `/api/encomendas/comprador/${utilizador.id}`
        );

        if (!response.ok) {
            throw new Error("Could not load orders");
        }

        const encomendas = await response.json();

        if (encomendas.length === 0) {
            ordersContainer.innerHTML = "<p>You have no orders yet.</p>";
            return;
        }

        ordersContainer.innerHTML = "";

        encomendas.forEach(encomenda => {
        const orderElement = document.createElement("div");
        orderElement.classList.add("order-card");

        let linhasHTML = "";

        encomenda.linhas.forEach(linha => {
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
        `;

        ordersContainer.appendChild(orderElement);
    });

    } catch (error) {
        console.error("Error loading orders:", error);
        ordersContainer.innerHTML = "<p>Could not load your orders.</p>";
    }
}