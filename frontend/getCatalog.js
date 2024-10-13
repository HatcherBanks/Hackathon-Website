class Game {
    constructor(image, title, description, genre, type, price) {
        this.image = image; // Base64 string
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.type = type;
        this.price = price;
    }
}


const getAllGames = () => {
    fetch('http://localhost:8080/games', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.json())
    .then(data => {
        const games = data.map(gameData => new Game(
            gameData.image,
            gameData.title,
            gameData.description,
            gameData.genre,
            gameData.type,
            gameData.price
        ));

        console.log(games);
        // Handle the list of Game objects
    })
    .catch(error => {
        console.error('Error:', error);
    });
};

// Call the function to get all games
getAllGames();