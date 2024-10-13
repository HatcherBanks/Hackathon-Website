const gameData = {
    title: "The Legend of Zelda: Breath of the Wild",
    achievement: "Mastered Cooking",
    hoursPlayed: 120,
    image: "path_to_game_image.jpg",
};

function renderGameCard(data) {
    if (!data) {
        document.getElementById("game-container").innerHTML = "";
        return;
    }

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

    document.getElementById("game-container").innerHTML = gameCardHTML;
}

renderGameCard(gameData);
