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

        document
            .getElementById("addToCartButton")
            .addEventListener("click", () => {
                addToCart(produto);
            });

    } catch (error) {

        console.error("Error loading product:", error);

        productContainer.innerHTML = `
            <p>Could not load product.</p>
        `;
    }
}

function addToCart(produto) {    
    const utilizador = JSON.parse(localStorage.getItem("utilizador"));

    if (!utilizador) {
        window.location.href = "login.html";
        return;
    }
    
    let cart = JSON.parse(localStorage.getItem(`cart_${utilizador.id}`)) || [];

    const existingProduct = cart.find(item => item.id === produto.id);

    if (existingProduct) {
        existingProduct.quantidade++;
    } else {
        cart.push({
            id: produto.id,
            nome: produto.nome,
            preco: produto.preco,
            quantidade: 1
        });
    }

    localStorage.setItem(`cart_${utilizador.id}`, JSON.stringify(cart));

    alert("Product added to cart.");
}

loadProduct();