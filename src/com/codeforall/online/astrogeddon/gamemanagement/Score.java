package com.codeforall.online.astrogeddon.gamemanagement;

import com.codeforall.online.astrogeddon.movables.Spaceship;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.List;

/**
 * Displays the current score and the highest score of the spaceship on the game screen.
 */
public class Score {

    // Reference to the Spaceship to get the current score
    private Spaceship spaceship;

    // Text object to display the score on the screen
    private Text scoreText;

    // Text object to display the highest score on the screen
    private Text highScoreText;

    // Reference to the HighScoreManager to get the highest score
    private HighScoreManager highScoreManager;

    /**
     * Constructor to initialize the Score display.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param spaceship the Spaceship whose score will be displayed
     * @param highScoreManager the HighScoreManager to get the highest score
     */
    public Score(int screenWidth, int screenHeight, Spaceship spaceship, HighScoreManager highScoreManager) {

        // Assign the passed Spaceship and HighScoreManager to local variables
        this.spaceship = spaceship;
        this.highScoreManager = highScoreManager;

        // Initialize the Text objects with the current score and highest score
        this.scoreText = new Text(60, 50, "SCORE: " + spaceship.getScore());
        this.highScoreText = new Text(270, 50, " [" + "HIGH: " + getHighestScore() + "]");

        // Set the color of the score and high score text
        this.scoreText.setColor(Color.WHITE);
        this.highScoreText.setColor(Color.WHITE);

        // Grow the text size to make it more readable
        this.scoreText.grow(40, 20);
        this.highScoreText.grow(60, 20);

        // Draw the text on the screen
        this.scoreText.draw();
        this.highScoreText.draw();
    }

    /**
     * Method to update the displayed score and highest score.
     */
    public void updateScore() {
        // Update the text with the current score from the Spaceship
        this.scoreText.setText("SCORE: " + this.spaceship.getScore());
        // Update the text with the highest score
        this.highScoreText.setText(" [" + "HIGH: " + getHighestScore() + "]");
    }

    /**
     * Method to get the highest score from the HighScoreManager.
     * @return the highest score
     */
    private int getHighestScore() {
        // Retrieve the list of high scores from the HighScoreManager
        List<Integer> highScores = highScoreManager.getHighScores();
        // Check if the list of high scores is empty
        // If it is empty, return 0 as the highest score
        // Otherwise, return the first score in the list, which is the highest
        return highScores.isEmpty() ? 0 : highScores.get(0);
    }
}
