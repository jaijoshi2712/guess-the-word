# Word Guessing Game

This is a  word guessing game built using Java and Spring Boot. The game randomly selects a 5-letter word from a database, and the user has to guess the word by entering one character at a time. The user has a limited number of tries (5) to guess the word correctly.

## How to Run

1. Clone the repository: `git clone https://github.com/jaijoshi2712/guess-the-word.git`
2. Navigate to the project directory: `cd word-guessing-game`
3. Build the project: `./mvnw clean package`
4. Run the application: `java -jar target/guesstheword-0.0.1-SNAPSHOT.jar`
5. Open your web browser and go to `http://localhost:8080/game-home`

## Flow of the Code

The application follows a Model-View-Controller (MVC) architecture pattern. Here's a breakdown of the main components:

### Controller

The `GameController` class is the entry point for handling HTTP requests. It has two main methods:

1. `showGameHomePage`: This method handles the `/game-home` endpoint and displays the game page. It checks if a character has been guessed and updates the game state accordingly.
2. `reloadGame`: This method handles the `/reload` endpoint and resets the game state.

### Service

The `GameService` class is responsible for managing the game logic. It selects a random word from the database, stores it in the `randomlyChoosenWord` variable, and initializes an array `allCharactersOfTheWord` to keep track of the guessed characters.

The `addGuess` method checks if the guessed character is present in the word and updates the `allCharactersOfTheWord` array accordingly.

### Utils

The `GameUtils` class provides utility methods for the game:

1. `reduceTry`: Decrements the number of remaining tries.
2. `getTriesRemaining`: Returns the number of remaining tries.
3. `resetTries`: Resets the number of remaining tries to the maximum value (5).
4. `reload`: Creates a new instance of `GameService` to reset the game state.

### View

The `game-home-page.html` file is the Thymeleaf template that renders the game page. It displays the word to be guessed (with underscores for unguessed characters), the number of remaining tries, and a form to input the guessed character.

## Database Population

The application uses a MySQL database named `word_game_db` to store random words for the game. When the application starts, the `GameService` class fetches a random 5-letter word from the `https://random-word-api.herokuapp.com/word?length=5` API and inserts it into the `words` table of the `word_game_db` database.

Before running the application, make sure you have a MySQL server running locally, and the `word_game_db` database is created. 
