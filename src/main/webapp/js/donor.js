function searchDonors() {
    let bloodGroup = document.getElementById("bloodGroup").value;
    console.log("Selected Blood Group:", bloodGroup); // Debugging

    fetch("http://localhost:8080/bloodbank/SearchDonorServlet?bloodGroup=" + encodeURIComponent(bloodGroup))
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok, status: " + response.status);
            }
            return response.text();
        })
        .then(data => {
            console.log("Response from Servlet:", data); // Debugging
            document.getElementById("donorTableBody").innerHTML = data;
        })
        .catch(error => console.error("Error fetching data:", error));
}

// Load donors on page load
document.addEventListener("DOMContentLoaded", function () {
    searchDonors();  // Fetch all donors initially
});
