document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("loginForm");
    const errorMessageDiv = document.getElementById("error-message");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission

        errorMessageDiv.textContent = ''; // Clear error messages

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        if (username === 'admin' && password === 'admin') {
            fetch("../sessionCheck", {
                method: "POST",
                credentials: "include" // 🔹 Ensures session cookies are sent
            })
            .then(response => response.json())
            .then(data => {
                if (data.loggedIn) {
                    console.log("Login successful, session created.");
                    window.location.href = "./home.html";
                } else {
                    console.log("Session creation failed.");
                }
            })
            .catch(error => console.error("Error setting session:", error));
        } else {
            errorMessageDiv.textContent = "Invalid Credentials. Please try again.";
        }
    });
});
