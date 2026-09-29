const utilizadorAtual = localStorage.getItem("utilizador");

const loginLink = document.getElementById("loginLink");

if (utilizadorAtual) {

    loginLink.textContent = "Logout";
    loginLink.href = "#";

    loginLink.addEventListener("click", function(event) {

        event.preventDefault();

        localStorage.removeItem("utilizador");

        window.location.href = "index.html";
    });
}