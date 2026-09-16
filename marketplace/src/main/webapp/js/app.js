// wait until page is loaded
document.addEventListener("DOMContentLoaded", loadProducts);

async function loadProducts() {
    try {
        const response = await fetch("/api/produtos");

        if(!response.ok)
        {
            throw new Error("Failed to fetch products");
        }

        const products = await response.json();

        displayProducts(products);
    } catch (error) {
        console.error("Error loading products: ", error);
    }
}

function displayProducts(products) {
    const container = document.getElementById("productsContainer");

    container.innerHTML = "";

    products.forEach(product => {
        const card = document.createElement("div");

        card.classList.add("product-card");

        card.innerHTML = `
            <h3>${product.nome}</h3>
            <p class="price">${product.preco.toFixed(2)} €</p>
            <p class="category">Category: ${product.categoria}</p>
            <p class="stock">Stock: ${product.stock}</p>
        `;

        container.appendChild(card);
    })
}