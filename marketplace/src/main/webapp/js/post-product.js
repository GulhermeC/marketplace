const utilizador = JSON.parse(localStorage.getItem("utilizador"));

if (!utilizador) {
    window.location.href = "login.html";
}

document.getElementById("postProductForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const nome = document.getElementById("name").value;
    const preco = parseFloat(document.getElementById("price").value);
    const stock = parseInt(document.getElementById("stock").value);
    const categoria = document.getElementById("category").value;

    const message = document.getElementById("productMessage");

    try{
        const response = await fetch(
            `/api/produtos?nome=${encodeURIComponent(nome)}&preco=${encodeURIComponent(preco)}&stock=${encodeURIComponent(stock)}&categoria=${encodeURIComponent(categoria)}&idVendedor=${utilizador.id}`,
            {
                method: "POST"
            }
        );

        if (response.ok){
            message.textContent = "Product posted successfully!";

            document.getElementById("postProductForm").reset();
        } else {
            message.textContent = "Could not post product.";
        }
    } catch (error) {
        console.error("Post product error:", error);
        message.textContent = "Could not connect to the server.";
    }
});