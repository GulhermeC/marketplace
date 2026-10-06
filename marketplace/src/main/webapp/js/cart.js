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

            <div class="cart-quantity">
                <button class="quantity-button decrease-button">−</button>

                <span>${item.quantidade}</span>

                <button class="quantity-button increase-button">+</button>
            </div>

            <button class="remove-button">Remove</button>
        `;

        cartItem
            .querySelector(".decrease-button")
            .addEventListener("click", () => {
                changeQuantity(item.id, -1);
            });

        cartItem
            .querySelector(".increase-button")
            .addEventListener("click", () => {
                changeQuantity(item.id, 1);
            });

        cartItem
            .querySelector(".remove-button")
            .addEventListener("click", () => {
                removeFromCart(item.id);
            });

        cartContainer.appendChild(cartItem);
    });

    const totalElement = document.createElement("h2");

    totalElement.textContent = `Total: ${total.toFixed(2)}€`;

    cartContainer.appendChild(totalElement);
}

function changeQuantity(productId, change) {

    const utilizador = JSON.parse(localStorage.getItem("utilizador"));

    const cartKey = `cart_${utilizador.id}`;

    const cart = JSON.parse(localStorage.getItem(cartKey)) || [];

    const item = cart.find(item => item.id === productId);

    if (!item) {
        return;
    }

    item.quantidade += change;

    if (item.quantidade <= 0) {
        item.quantidade = 1;
    }

    localStorage.setItem(cartKey, JSON.stringify(cart));

    loadCart();
}

function removeFromCart(productId) {

    const utilizador = JSON.parse(localStorage.getItem("utilizador"));

    const cartKey = `cart_${utilizador.id}`;

    const cart = JSON.parse(localStorage.getItem(cartKey)) || [];

    const updatedCart = cart.filter(item => item.id !== productId);

    localStorage.setItem(cartKey, JSON.stringify(updatedCart));

    loadCart();
}

loadCart();