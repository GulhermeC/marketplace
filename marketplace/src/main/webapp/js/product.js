const params = new URLSearchParams(window.location.search);
const productId = params.get("id");

const productContainer = document.getElementById("productContainer");

async function loadProduct() {

    if (!productId) {
        productContainer.innerHTML = "<p>Product not found.</p>";
        return;
    }

    try {

        const response = await fetch(`/api/produtos/${productId}`);

        if (!response.ok) {
            throw new Error("Product not found");
        }

        const produto = await response.json();

        productContainer.innerHTML = `
            <div class="product-details">

                <h1>${produto.nome}</h1>

                <p class="product-price">
                    ${produto.preco}€
                </p>

                <p>
                    Category: ${produto.categoria}
                </p>

                <p>
                    Stock: ${produto.stock}
                </p>

                <p>
                    Seller: ${produto.vendedor.nome}
                </p>

                <button id="addToCartButton">
                    Add to cart
                </button>

            </div>
        `;

    } catch (error) {

        console.error("Error loading product:", error);

        productContainer.innerHTML = `
            <p>Could not load product.</p>
        `;
    }
}

loadProduct();