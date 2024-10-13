const gamesPlayedData = [
    {
        title: "The Witcher 3: Wild Hunt",
        achievement: "Completed Main Story",
        hoursPlayed: 200,
        image: "path_to_witcher_image.jpg",
    },
    {
        title: "Dark Souls III",
        achievement: "Defeated Nameless King",
        hoursPlayed: 150,
        image: "path_to_dark_souls_image.jpg",
    },
    {
        title: "Hades",
        achievement: "Escaped the Underworld",
        hoursPlayed: 50,
        image: "path_to_hades_image.jpg",
    }
    // You can add more games here
];

function renderGamesPlayed(data) {
    if (!data || data.length === 0) {
        document.getElementById("games-played-container").innerHTML = "<p>No games played yet.</p>";
        return;
    }

    let gamesHTML = "";
    data.forEach(game => {
        gamesHTML += `
            <div class="game-card">
                <img src="${game.image}" alt="${game.title}" class="game-image">
                <div class="game-details">
                    <h3 class="game-title">${game.title}</h3>
                    <p class="recent-achievement">Recent Achievement: ${game.achievement}</p>
                    <p class="hours-played">Hours Played: ${game.hoursPlayed} hours</p>
                </div>
            </div>
        `;
    });

    document.getElementById("games-played-container").innerHTML = gamesHTML;
}

renderGamesPlayed(gamesPlayedData);
