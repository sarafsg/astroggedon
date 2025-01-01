package com.codeforall.online.astrogeddon.gamemanagement;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;

/**
 * This class manages the Game Over menu, including displaying options
 * for restarting the game or quitting, and handling keyboard input.
 */
public class GameOverMenu implements KeyboardHandler {

    // Image displayed when the game is over
    private Picture gameOverPicture;
    // Keyboard object to listen for key presses
    private Keyboard keyboard;
    // Reference to the GameEngine to control game flow
    private GameEngine gameEngine;
    // Text to display high scores
    private Text highScoresText;

    /**
     * Constructor to initialize the GameOverMenu.
     * @param gameEngine The game engine instance to interact with the game
     */
    public GameOverMenu(GameEngine gameEngine) {
        // Assign the game engine
        this.gameEngine = gameEngine;

        // Load and display the game over image
        gameOverPicture = new Picture(10, 10, Game.PREFIX + "game_over_menu.png"); // Create a new Picture object
        gameOverPicture.draw(); // Draw the game over picture on the screen

        // Initialize keyboard controls
        keyboard = new Keyboard(this); // Create a new Keyboard object and set this as the handler

        // Create and configure the keyboard event for the 'R' key
        KeyboardEvent restartEvent = new KeyboardEvent(); // Create a new KeyboardEvent object
        restartEvent.setKey(KeyboardEvent.KEY_R); // Set the key to 'R'
        restartEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(restartEvent); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'Q' key
        KeyboardEvent quitEvent = new KeyboardEvent(); // Create a new KeyboardEvent object
        quitEvent.setKey(KeyboardEvent.KEY_Q); // Set the key to 'Q'
        quitEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(quitEvent); // Add this event listener to the keyboard

        // Display high scores
        displayHighScores();
    }

    /**
     * Displays the high scores on the screen.
     */
    public void displayHighScores() {
        // Get high scores from the game engine
        List<Integer> highScores = gameEngine.getHighScoreManager().getHighScores();

        // Create a string to represent high scores
        StringBuilder sb = new StringBuilder("HIGH SCORES:\n");
        // Iterate through each score in the high scores list
        for (int i = 0; i < highScores.size(); i++) {
            // Append each score to the string
            sb.append(highScores.get(i));
            // Append a separator if this is not the last score
            if (i < highScores.size() - 1) {
                sb.append(" / ");
            }
        }

        // Create and display high scores text
        highScoresText = new Text(90, 500, sb.toString());
        highScoresText.setColor(Color.WHITE);
        highScoresText.grow(60, 20);
        highScoresText.draw();
    }

    /**
     * Hides the Game Over menu by removing all its components from the screen.
     */
    public void hide() {
        gameOverPicture.delete(); // Remove the game over picture from the screen
        highScoresText.delete(); // Remove the high scores text
        highScoresText = null; // Clean up reference
    }

    /**
     * Handles key press events to restart the game or quit the game.
     * @param keyboardEvent The keyboard event containing information about the key pressed
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) { // Check if the 'R' key was pressed
            gameEngine.restartGame(); // Restart the game
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) { // Check if the 'Q' key was pressed
            gameEngine.quitGame(); // Quit the game
        }
    }

    /**
     * Handles key release events (not used in this implementation).
     * @param keyboardEvent The keyboard event containing information about the key released
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        // No action needed when a key is released
    }
}
