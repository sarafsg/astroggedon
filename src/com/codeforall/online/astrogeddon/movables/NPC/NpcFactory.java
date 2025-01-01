package com.codeforall.online.astrogeddon.movables.NPC;

import com.codeforall.online.astrogeddon.gamemanagement.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class responsible for creating NPCs (asteroids, stars, and special items).
 * It handles the creation of different NPC types and ensures that they do not overlap.
 */
public class NpcFactory {

    // Random number generator for creating random y positions for NPCs
    private Random randomGenerator;

    // List to keep track of y positions that are currently occupied by NPCs
    private List<Integer> occupiedYPositions;

    // Constant for total height of the screen
    private static final int SCREEN_HEIGHT = 600;
    // Constant for height of each NPC
    private static final int NPC_HEIGHT = 100;
    // Constant for minimum vertical distance between NPCs to avoid overlap
    private static final int MIN_DISTANCE = 100;
    // Constant for vertical margin from top of the screen
    private static final int TOP_MARGIN = 70;
    // Constant for vertical margin from bottom of the screen
    private static final int BOTTOM_MARGIN = 0;

    /**
     * Constructor for the NpcFactory class.
     */
    public NpcFactory() {
        // Create a new Random object for generating random numbers
        randomGenerator = new Random();
        // Initialize the list to keep track of occupied y positions
        occupiedYPositions = new ArrayList<>();
    }

    /**
     * Method to create an asteroid.
     * @return A new Asteroid object
     */
    public Asteroid createAsteroid() {
        // Get a y position that is available and does not overlap with other NPCs
        int y = getAvailableYPosition();

        // Create a picture for the asteroid at the specified y position
        Picture picture = new Picture(1300 - 90, y, Game.PREFIX + "asteroid.png");

        // Return a new Asteroid object with the created picture
        return new Asteroid(picture);
    }

    /**
     * Method to create a stronger asteroid.
     * @return A new Stronger Asteroid object
     */
    public StrongerAsteroid createStrongerAsteroid() {
        // Get a y position that is available and does not overlap with other NPCs
        int y = getAvailableYPosition();

        // Create a picture for the Stronger Asteroid at the specified y position
        Picture picture = new Picture(1300 - 50, y, Game.PREFIX + "stronger_asteroid.png");

        // Return a new Stronger Asteroid object with the created picture
        return new StrongerAsteroid(picture);
    }

    /**
     * Method to create a star.
     * @return A new Star object
     */
    public Star createStar() {
        // Get a y position that is available and does not overlap with other NPCs
        int y = getAvailableYPosition();

        // Create a picture for the star at the specified y position
        Picture picture = new Picture(1300 - 90, y, Game.PREFIX + "star.png");

        // Return a new Star object with the created picture
        return new Star(picture);
    }

    /**
     * Method to create a special item.
     * @return A new SpecialItem object
     */
    public SpecialItem createSpecialItem() {
        // Get a y position that is available and does not overlap with other NPCs
        int y = getAvailableYPosition();

        // Create a picture for the special item at the specified y position
        Picture picture = new Picture(1300 - 50, y, Game.PREFIX + "special_item.png");

        // Return a new SpecialItem object with the created picture
        return new SpecialItem(picture);
    }

    /**
     * Method to create an easy mode item.
     * @return A new EasyModeItem object
     */
    public EasyModeItem createEasyModeItem() {
        // Get a y position that is available and does not overlap with other NPCs
        int y = getAvailableYPosition();

        // Create a picture for the easy mode item at the specified y position
        Picture picture = new Picture(1300 - 90, y, Game.PREFIX + "easy_mode_item.png");

        // Return a new EasyModeItem object with the created picture
        return new EasyModeItem(picture);
    }

    /**
     * Method to get a y position that is available
     * and does not overlap with existing NPCs.
     * @return A valid y position for creating an NPC
     */
    private int getAvailableYPosition() {

        // Define the vertical range for NPC y positions
        int minY = TOP_MARGIN;
        int maxY = SCREEN_HEIGHT - BOTTOM_MARGIN - NPC_HEIGHT;

        // Variable to hold the randomly generated y position
        int yPosition;

        // Variable to indicate if the generated y position overlaps with any existing NPC
        boolean isOverlapping;

        // Loop until a non-overlapping y position is found
        while (true) {
            // Generate a random y position within the defined vertical range
            yPosition = randomGenerator.nextInt(maxY - minY) + minY;

            // Assume the generated position does not overlap initially
            isOverlapping = false;

            // Iterate through all currently occupied y positions to check for overlap
            for (int existingYPosition : occupiedYPositions) {
                // If the distance between existing y position and new y position is less than MIN_DISTANCE
                if (Math.abs(existingYPosition - yPosition) < MIN_DISTANCE) {
                    // Mark the position as overlapping
                    isOverlapping = true;
                    // Exit the loop as we found an overlap
                    break;
                }
            }

            // If no overlap was found
            if (!isOverlapping) {
                // Exit the loop with a valid y position
                break;
            }
        }

        // Add the valid y position to the list of occupied positions
        occupiedYPositions.add(yPosition);

        // Return the valid y position
        return yPosition;
    }

    /**
     * Method to clear the list of occupied y positions.
     */
    public void clearOccupiedYPositions() {
        // Remove all y positions from the list to reset for the next frame
        occupiedYPositions.clear();
    }
}
