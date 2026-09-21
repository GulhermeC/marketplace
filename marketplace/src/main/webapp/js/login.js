document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const message = document.getElementById("loginMessage");

    try {
        const response = await fetch(
            `/api/utilizadores/login?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
        );

        if (response.ok) {
            const utilizador = await response.json();

            localStorage.setItem("utilizador", JSON.stringify(utilizador));

            window.location.href = "index.html";
        } else if (response.status === 401) {
            message.textContent = "Invalid email or password.";
        } else {
            message.textContent = "Something went wrong.";
        }

    } catch (error) {
        console.error("Login error:", error);
        message.textContent = "Could not connect to the server.";
    }
});