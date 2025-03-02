document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("donorForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Stop default form submission

        let formData = new FormData(this);

        fetch("../RegisterDonorServlet", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" }, // ✅ Fixing missing headers
            body: new URLSearchParams(formData) // ✅ Convert FormData to URL parameters
        })
        .then(response => response.text())
        .then(data => {
            console.log("Response from server:", data); // ✅ Debugging

            if (data.trim() === "success") {
                window.location.href = "donors.html"; // ✅ Redirects properly on success
            } else {
                document.getElementById("errorMessage").textContent = "Registration failed. Try again.";
            }
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("errorMessage").textContent = "An error occurred. Try again.";
        });
    });
});
