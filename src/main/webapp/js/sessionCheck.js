document.addEventListener("DOMContentLoaded", function () {
         fetch("../sessionCheck") // Call the existing session-checking servlet
             .then(response => response.json())
             .then(data => {
                 if (!data.loggedIn) {
                     window.location.href = "login.html"; // Redirect if session is invalid
                 }
             })
             .catch(error => console.error("Error checking session:", error));
     });