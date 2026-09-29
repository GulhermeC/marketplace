// wait until page is loaded
document.addEventListener("DOMContentLoaded", loadProducts);

let allProducts = [];

// load all produtos

async function loadProducts() {
    try {
        const response = await fetch("/api/produtos");

        if(!response.ok)
        {
            throw new Error("Failed to fetch products");
        }

        const products = await response.json();

        allProducts = products;

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

        card.addEventListener("click", function() {
            window.location.href = `product.html?id=${product.id}`;
        });

        container.appendChild(card);
    })
}

const utilizador = localStorage.getItem("utilizador");
const addProductButton = document.getElementById("addProductButton");

if (!utilizador) {
    addProductButton.style.display = "none";
}

// search

const searchInput = document.getElementById("searchInput");
const searchButton = document.getElementById("searchButton");

searchButton.addEventListener("click", searchProducts);

searchInput.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        searchProducts();
    }
});

function searchProducts() {
    const searchTerm = searchInput.value.toLowerCase().trim();

    if (searchTerm === "") {
        displayProducts(allProducts);
        return;
    }

    const filteredProducts = allProducts.filter(product => {       
        const name = product.nome.toLowerCase();
        const category = product.categoria.toLowerCase();

        return name.includes(searchTerm) || category.includes(searchTerm);
    });
    
    displayProducts(filteredProducts);
}