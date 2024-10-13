const wantToPlayData = [
    {
        title: "Elden Ring",
        description: "Explore a vast open world filled with challenges and lore.",
        image: "path_to_elden_ring_image.jpg",
    },
    {
        title: "Cyberpunk 2077",
        description: "Experience a futuristic world with deep storylines.",
        image: "path_to_cyberpunk_image.jpg",
    },
    {
        title: "Hollow Knight",
        description: "Embark on an epic adventure in a beautifully crafted world.",
        image: "path_to_hollow_knight_image.jpg",
    }
    // Add more games as needed
];

function renderWantToPlayGames(data) {
    if (!data || data.length === 0) {
        document.getElementById("want-to-play-container").innerHTML = "<p>No games in your 'Want to Play' list.</p>";
        return;
    }

    let wantToPlayHTML = "";
    data.forEach(game => {
        wantToPlayHTML += `
            <div class="game-card">
                <img src="${game.image}" alt="${game.title}" class="game-image">
                <div class="game-details">
                    <h3 class="game-title">${game.title}</h3>
                    <p class="game-description">${game.description}</p>
                </div>
            </div>
        `;
    });

    document.getElementById("want-to-play-container").innerHTML = wantToPlayHTML;
}

renderWantToPlayGames(wantToPlayData);
