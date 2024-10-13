// Data from Currently Playing
const currentlyPlayingData = {
    title: "The Legend of Zelda: Breath of the Wild",
    achievement: "Mastered Cooking",
    hoursPlayed: 120,
    image: "path_to_game_image.jpg",
};

// Data from Games Played
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
];

// Data from Want to Play
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
];

// Function to render Currently Playing Game
function renderCurrentlyPlaying(data) {
    const container = document.getElementById("currently-playing-container");
    if (!data) {
        container.innerHTML = "<p>No current games playing.</p>";
        return;
    }

    const gameHTML = `
        <div class="game-card">
            <img src="${data.image}" alt="${data.title}" class="game-image">
            <div class="game-details">
                <h3 class="game-title">${data.title}</h3>
                <p class="recent-achievement">Recent Achievement: ${data.achievement}</p>
                <p class="hours-played">Hours Played: ${data.hoursPlayed} hours</p>
            </div>
        </div>
    `;
    container.innerHTML = gameHTML;
}

// Function to render Played Games
function renderPlayedGames(data) {
    const container = document.getElementById("played-games-container");
    if (!data || data.length === 0) {
        container.innerHTML = "<p>No played games.</p>";
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

    container.innerHTML = gamesHTML;
}

// Function to render Want to Play Games
function renderWantToPlayGames(data) {
    const container = document.getElementById("want-to-play-container");
    if (!data || data.length === 0) {
        container.innerHTML = "<p>No games in 'Want to Play' list.</p>";
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

    container.innerHTML = wantToPlayHTML;
}

// Render all sections
renderCurrentlyPlaying(currentlyPlayingData);
renderPlayedGames(gamesPlayedData);
renderWantToPlayGames(wantToPlayData);
