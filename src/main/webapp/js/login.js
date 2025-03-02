document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("loginForm");
    const errorMessageDiv = document.getElementById("error-message");

    form.addEventListener("submit", function(event) {
        event.preventDefault();  // Prevent form submission for validation

        // Clear previous error messages
        errorMessageDiv.textContent = '';

        // Get form values
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        // Simulating server-side validation
        if (username === 'admin' && password === 'admin') {
            // If credentials are correct, navigate to home.html
            window.location.href = "./home.html";
        } else {
            // If credentials are incorrect, display an error message
            errorMessageDiv.textContent = "Invalid Credentials. Please try again.";
        }
    });
});
