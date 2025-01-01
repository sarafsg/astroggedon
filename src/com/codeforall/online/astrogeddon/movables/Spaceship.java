package com.codeforall.online.astrogeddon.movables;

import com.codeforall.online.astrogeddon.RectangleSize;
import com.codeforall.online.astrogeddon.gamemanagement.Game;
import com.codeforall.online.astrogeddon.movables.NPC.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import com.codeforall.online.astrogeddon.gamemanagement.MusicPlayer;

import java.io.File;

/**
 * This class represents the spaceship controlled by the player in the game.
 * Manages spaceship properties, movements, collisions, health and score.
 */
public class Spaceship extends AbstractMovable {

    private int health; // Health of the spaceship
    private int score; // Score of the spaceship
    private boolean isEasyModeActive; // Track if easy mode is active
    private int easyModeFrames; // Track frames for easy mode
    private Picture easyModePicture; // Picture for easy mode
    private Picture defaultPicture; // Default picture
    private MusicPlayer gameplaySoundtrack;

    /**
     * Constructor to initialize the spaceship with a picture.
     * @param picture The picture representing the spaceship
     */
    public Spaceship(Picture picture) {
        super(picture); // Call the constructor of the superclass
        this.defaultPicture = picture; // Set the default picture
        picture.draw(); // Draw the spaceship picture
        // Initialize the easy mode picture with a different image
        this.easyModePicture = new Picture(picture.getX(), picture.getY(), Game.PREFIX + "spaceship_easy_mode.png");
        this.health = 100; // Set initial health to 100
        this.score = 0; // Set initial score to 0
        this.isEasyModeActive = false; // Initialize easy mode as inactive
        this.easyModeFrames = 0; // Initialize easy mode frame counter
        gameplaySoundtrack = new MusicPlayer();
    }

    /**
     * Method to handle the activation of easy mode.
     */
    public void activateEasyMode() {
        // Check if easy mode is not already active
        if (!isEasyModeActive) {
            isEasyModeActive = true; // Set easy mode as active
            easyModeFrames = 200; // Set how many frames easy mode is active
            switchPicture(easyModePicture); // Switch to easy mode picture
        }
    }

    /**
     * Method to update the frame count for easy mode
     */
    public void updateEasyMode() {
        // Check if easy mode is active
        if (isEasyModeActive) {
            // If there are frames left for easy mode
            if (easyModeFrames > 0) {
                easyModeFrames--; // Decrease the frame count
            } else {
                // Deactivate easy mode if no frames are left
                isEasyModeActive = false;
                switchPicture(defaultPicture); // Switch back to default picture
            }
        }
    }

    /**
     * Method to switch the spaceship's picture.
     * @param newPicture The new picture to display
     */
    private void switchPicture(Picture newPicture) {
        // Store the current position of the spaceship
        int x = picture.getX();
        int y = picture.getY();

        // Remove the current picture
        this.picture.delete();

        // Update the spaceship to use the new picture
        this.picture = newPicture;

        // Adjust the new picture's position to match the old picture's position
        // Translate the new picture by the difference in their positions
        this.picture.translate(x - newPicture.getX(), y - newPicture.getY());

        // Draw the new picture at the updated position
        this.picture.draw();
    }


    public void crashAsteroid() {
        System.out.println("i crash asteroid");
        this.health -= 10; // Reduce health by 10
        System.out.println(this.health);
    }

    /**
     * Method to check if the spaceship is colliding with an asteroid.
     * @param asteroid The asteroid to check collision with
     * @return true if colliding, false otherwise
     */
    public boolean isCollidingWithAsteroid(Asteroid asteroid) {
        return asteroid.getLeft() < this.getRight() &&
                asteroid.getRight() > this.getLeft() &&
                asteroid.getTop() < this.getBottom() &&
                asteroid.getBottom() > this.getTop();
    }

    /**
     * Method to check if the spaceship is colliding with a stronger asteroid.
     * @param strongerAsteroid The stronger asteroid to check collision with
     * @return true if colliding, false otherwise
     */
    public boolean isCollidingWithStrongerAsteroid(StrongerAsteroid strongerAsteroid) {
        return strongerAsteroid.getLeft() < this.getRight() &&
                strongerAsteroid.getRight() > this.getLeft() &&
                strongerAsteroid.getTop() < this.getBottom() &&
                strongerAsteroid.getBottom() > this.getTop();
    }

    /**
     * Method to check if the spaceship is colliding with a star.
     * @param star The star to check collision with
     * @return true if colliding, false otherwise
     */
    public boolean isCollidingWithStar(Star star) {
        return star.getLeft() < this.getRight() &&
                star.getRight() > this.getLeft() &&
                star.getTop() < this.getBottom() &&
                star.getBottom() > this.getTop();
    }

    /**
     * Method to check if the spaceship is colliding with a special item.
     * @param specialItem The special item to check collision with
     * @return true if colliding, false otherwise
     */
    public boolean isCollidingWithSpecialItem(SpecialItem specialItem) {
        return specialItem.getLeft() < this.getRight() &&
                specialItem.getRight() > this.getLeft() &&
                specialItem.getTop() < this.getBottom() &&
                specialItem.getBottom() > this.getTop();
    }

    /**
     * Method to check if the spaceship is colliding with an easy mode item.
     * @param easyModeItem The easy mode item to check collision with
     * @return true if colliding, false otherwise
     */
    public boolean isCollidingWithEasyModeItem(EasyModeItem easyModeItem) {
        return easyModeItem.getLeft() < this.getRight() &&
                easyModeItem.getRight() > this.getLeft() &&
                easyModeItem.getTop() < this.getBottom() &&
                easyModeItem.getBottom() > this.getTop();
    }

    /**
     * Method to reduce the spaceship's health by 10,
     * ensuring it doesn't go below 0.
     */
    public void reduceHealth() {
        // If easy mode is not active
        if (!isEasyModeActive) {
            // Reduce health
            this.health -= 10;
            if (this.health < 0) {
                this.health = 0;
            }
        }
    }

    /**
     * Method to increase the spaceship's health by 10,
     * ensuring it doesn't exceed 100.
     */
    public void increaseHealth() {
        this.health += 10;
        if (this.health > 100) {
            this.health = 100;
            //increase life
        }
    }

    /**
     * Method to get the current health of the spaceship.
     * @return The health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method to increase the spaceship's score by 10.
     */
    public void increaseScore () {
        this.score += 10;
    }

    /**
     * Method to get the current score of the spaceship.
     * @return The score value
     */
    public int getScore() {
        return score;
    }

    public int setScore(int newScore){
        return this.score += newScore;
    }

    public void addSoundtrack(){
        File musicFile = new File(Game.PREFIX + "space_odyssey.wav");
        if (musicFile.exists()) {
            gameplaySoundtrack.playMusic(Game.PREFIX + "space_odyssey.wav");
        } else {
            System.out.println("Audio file not found: " + Game.PREFIX + "space_odyssey.wav");
        }
    }

    public void stopMusic(){
        gameplaySoundtrack.stopMusic();
    }

    /**
     * Method to move the spaceship left within the game boundary.
     */
    @Override
    public void moveLeft() {
        if (picture.getX() > 20) {
            picture.translate(-20, 0);
        }
    }

    /**
     * Method to move the spaceship right within the game boundary.
     */
    @Override
    public void moveRight() {
        if (picture.getX() + picture.getWidth() < RectangleSize.STANDARDSIZE.getWidth()) {
            picture.translate(20, 0);
        }
    }

    /**
     * Method to move the spaceship up within the game boundary.
     */
    @Override
    public void moveUp() {
        int topMargin = 90;
        if (picture.getY() > topMargin) {
            picture.translate(0, -20);
        }
    }

    /**
     * Method to move the spaceship down within the game boundary.
     */
    @Override
    public void moveDown() {
        if (picture.getY() + picture.getHeight() < RectangleSize.STANDARDSIZE.getHeight()) {
            picture.translate(0, 20);
        }
    }

    /**
     * Method to reset the spaceship to its initial state.
     */
    public void reset() {
        // Reset the spaceship's health to 100
        this.health = 100;
        // Reposition the spaceship to the initial coordinates (15, 300)
        // Calculate the difference needed to move from the last position
        picture.translate(15 - picture.getX(), 300 - picture.getY());
    }
}
