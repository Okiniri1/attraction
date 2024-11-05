const baseUrl = "/api/attractions";
const attractionForm = document.getElementById("attractionForm");
const attractionList = document.getElementById("attractionList");

async function fetchAttractions() {
    try {
        const response = await fetch(baseUrl);
        const attractions = await response.json();
        console.log("Response from API:", response);
        console.log("Parsed attractions:", attractions);
        displayAttractions(attractions);
    } catch (error) {
        console.error("Error fetching attractions:", error);
    }
}



function displayAttractions(attractions) {
    attractionList.innerHTML = "";

    const attractionArray = Array.isArray(attractions) ? attractions : attractions.content;

    if (!Array.isArray(attractionArray)) {
        console.error("Expected an array but got:", attractionArray);
        return;
    }

    attractionArray.forEach(attraction => {
        const li = document.createElement("li");
        li.innerHTML = `
            <strong>${attraction.name}</strong> - ${attraction.location} <br>
            ${attraction.description} <br>
            Rating: ${attraction.rating} <br>
            <button onclick="deleteAttraction(${attraction.id})">Delete</button>
        `;
        attractionList.appendChild(li);
    });
}

attractionForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const newAttraction = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        location: document.getElementById("location").value,
        rating: parseFloat(document.getElementById("rating").value)
    };

    await fetch(baseUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newAttraction)
    });

    attractionForm.reset();
    fetchAttractions();
});

async function deleteAttraction(id) {
    await fetch(`${baseUrl}/${id}`, {
        method: "DELETE"
    });
    fetchAttractions();
}

fetchAttractions();
