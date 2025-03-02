document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("loginForm");
    const errorMessageDiv = document.getElementById("error-message");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission

        errorMessageDiv.textContent = ''; // Clear error messages

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        fetch("../LoginServlet", {  // 🔹 Send request to servlet
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`,
            credentials: "include" // 🔹 Ensure session cookies are sent
        })
        .then(response => response.json())  // Parse JSON response
        .then(data => {
            if (data.success) {
                console.log("Login successful, session created.");
                window.location.href = "./home.html";  // Redirect to home page
            } else {
                errorMessageDiv.textContent = data.error; // Show error message
            }
        })
        .catch(error => console.error("Error during login:", error));
    });
});
