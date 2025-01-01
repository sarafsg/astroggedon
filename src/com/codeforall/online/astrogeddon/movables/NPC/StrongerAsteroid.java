package com.codeforall.online.astrogeddon.movables.NPC;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Class representing a Stronger Asteroid NPC.
 * Extends the Npc class and inherits its functionality.
 */
public class StrongerAsteroid extends Npc {

    private int health; // Health of the stronger asteroid

    /**
     * Constructor to initialize a Stronger Asteroid with a given picture.
     * @param picture The Picture object representing the Stronger Asteroid.
     */
    public StrongerAsteroid(Picture picture) {
        super(picture); // Call the constructor of the superclass (Npc)
        picture.draw(); // Draw the picture
        this.health = 40; // Set initial health to 40
    }

    /**
     * Reduces the health of the stronger asteroid by a specified amount.
     * Ensures the health does not drop below 0.
     * @param amount The amount to reduce the stronger asteroid's health by.
     */
    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0; // Ensure health does not go below 0
        }
    }

    /**
     * Checks if the stronger asteroid is destroyed (health is 0 or less).
     * @return true if the stronger asteroid is destroyed, false otherwise.
     */
    public boolean isDestroyed() {
        return health <= 0;
    }

    /**
     * Gets the current health of the stronger asteroid.
     * @return The current health of the stronger asteroid.
     */
    public int getHealth() {
        return health;
    }
}
