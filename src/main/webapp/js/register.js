    <script>
        // Display error message if present in URL
        const urlParams = new URLSearchParams(window.location.search);
        const error = urlParams.get("error");
        if (error) {
            document.getElementById("errorMessage").textContent = error;
            // Remove 'error' from URL
            window.history.replaceState({}, document.title, window.location.pathname);
        }
    </script>