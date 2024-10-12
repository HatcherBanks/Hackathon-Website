// gameCard.js

// Example game data; replace this with your actual data fetching logic
const gameData = {
    title: "The Legend of Zelda: Breath of the Wild",
    achievement: "Mastered Cooking",
    hoursPlayed: 120,
    image: "path_to_game_image.jpg", // URL to the game image
    // Uncomment the line below to simulate no game data
    // gameData: null
};

// Function to render the game card
function renderGameCard(data) {
    if (!data) {
        // No game data, do not show the format
        document.getElementById("game-container").innerHTML = "";
        return;
    }

    // Create the game card HTML
    const gameCardHTML = `
        <div class="game-card">
            <img src="${data.image}" alt="${data.title}" class="game-image">
            <div class="game-details">
                <h3 class="game-title">${data.title}</h3>
                <p class="recent-achievement">Recent Achievement: ${data.achievement}</p>
                <p class="hours-played">Hours Played: ${data.hoursPlayed} hours</p>
            </div>
        </div>
    `;

    // Insert the game card into the container
    document.getElementById("game-container").innerHTML = gameCardHTML;
}

// Call the function with the game data
renderGameCard(gameData); // Replace with null to simulate no data
