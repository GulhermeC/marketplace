document.getElementById("registerForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const numTele = document.getElementById("numTele").value;
    const local = document.getElementById("local").value;
    const tipo = document.getElementById("tipo").value;

    const message = document.getElementById("registerMessage");

    try {
        const response = await fetch(
            `/api/utilizadores?nome=${encodeURIComponent(nome)}&password=${encodeURIComponent(password)}&email=${encodeURIComponent(email)}&numTele=${encodeURIComponent(numTele)}&local=${encodeURIComponent(local)}&tipo=${encodeURIComponent(tipo)}`,
            {
                method: "POST"
            }
        );

        if (response.ok) {
            window.location.href = "login.html";
        } else {
            message.textContent = "Could not create account.";
        }

    } catch (error) {
        console.error("Registration error:", error);
        message.textContent = "Could not connect to the server.";
    }
});