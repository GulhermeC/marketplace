const cartContainer = document.getElementById("cartContainer");

function loadCart() {

    const utilizador = JSON.parse(localStorage.getItem("utilizador"));

    if (!utilizador) {
        window.location.href = "login.html";
        return;
    }

    const cart = JSON.parse(
        localStorage.getItem(`cart_${utilizador.id}`)
    ) || [];

    if (cart.length === 0) {
        cartContainer.innerHTML = "<p>Cart is empty.</p>";
        return;
    }

    cartContainer.innerHTML ="";

    let total = 0;

    cart.forEach(item => {

        total += item.preco * item.quantidade;

        const cartItem = document.createElement("div");

        cartItem.innerHTML = `
            <h3>${item.nome}</h3>
            <p>Price: ${item.preco}€</p>
            <p>Quantity: ${item.quantidade}</p>
        `;

        cartContainer.appendChild(cartItem);
    });

    const totalElement = document.createElement("h2");

    totalElement.textContent = `Total: ${total.toFixed(2)}€`;

    cartContainer.appendChild(totalElement);
}

loadCart();