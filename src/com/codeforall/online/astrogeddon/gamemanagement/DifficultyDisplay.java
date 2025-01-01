package com.codeforall.online.astrogeddon.gamemanagement;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Displays the current difficulty level on the game screen.
 */
public class DifficultyDisplay {

    // Reference to the DifficultyManager to get the current difficulty level
    private DifficultyManager difficultyManager;

    // Text object to display the difficulty level on screen
    private Text difficultyText;

    /**
     * Constructor to initialize the DifficultyDisplay.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param difficultyManager the DifficultyManager to get the current difficulty level
     */
    public DifficultyDisplay(int screenWidth, int screenHeight, DifficultyManager difficultyManager) {

        // Assign the passed DifficultyManager to the local variable
        this.difficultyManager = difficultyManager;

        // Initialize the Text object with the current difficulty level
        this.difficultyText = new Text(600, 50, "DIFFICULTY: " + difficultyManager.getDifficultyLevel());
        // Set the color of the difficulty text
        this.difficultyText.setColor(Color.CYAN);
        // Grow the text size to make it more readable
        this.difficultyText.grow(60, 20);
        // Draw the text on the screen
        this.difficultyText.draw();
    }

    /**
     * Method to update the displayed difficulty level.
     */
    public void updateDifficulty() {
        // Update the text with the current difficulty level from the DifficultyManager
        this.difficultyText.setText("DIFFICULTY: " + this.difficultyManager.getDifficultyLevel());
    }
}
