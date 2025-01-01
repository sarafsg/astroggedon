package com.codeforall.online.astrogeddon.gamemanagement;

/**
 * Manages the difficulty levels by adjusting NPC spawn rates.
 */
public class DifficultyManager {

    // Constants for initial spawn rates: determine how frequently NPCs are spawned
    private static final int INITIAL_ASTEROID_SPAWN_RATE = 30; // Spawns asteroids every 30 frames
    private static final int INITIAL_STRONGER_ASTEROID_SPAWN_RATE = 60; // Spawns stronger asteroids every 60 frames
    private static final int INITIAL_STAR_SPAWN_RATE = 90; // Spawns stars every 90 frames
    private static final int INITIAL_SPECIAL_ITEM_SPAWN_RATE = 150; // Spawns special items every 150 frames

    // Constants for minimum allowed spawn rates (to prevent game from crashing)
    private static final int MIN_ASTEROID_SPAWN_RATE = 5;
    private static final int MIN_STRONGER_ASTEROID_SPAWN_RATE = 10;
    private static final int MIN_STAR_SPAWN_RATE = 15;
    private static final int MIN_SPECIAL_ITEM_SPAWN_RATE = 20;

    // Difficulty level starts at 0
    private int difficultyLevel = 0;

    /**
     * Increases the difficulty level.
     */
    public void increaseDifficulty() {
        difficultyLevel++;
        System.out.println("Difficulty level updated to: " + difficultyLevel);
    }

    /**
     * Resets the difficulty to its initial state.
     */
    public void resetDifficulty() {
        difficultyLevel = 0;
    }

    /**
     * Returns the current difficulty level.
     * @return The current difficulty level.
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Calculates the asteroid spawn rate based on difficulty.
     * Ensures it does not go below the minimum rate.
     * @return Asteroid spawn rate.
     */
    public int getAsteroidSpawnRate() {
        return Math.max(INITIAL_ASTEROID_SPAWN_RATE - difficultyLevel, MIN_ASTEROID_SPAWN_RATE);
    }

    /**
     * Calculates the stronger asteroid spawn rate based on difficulty.
     * Ensures it does not go below the minimum rate.
     * @return Stronger asteroid spawn rate.
     */
    public int getStrongerAsteroidSpawnRate() {
        return Math.max(INITIAL_STRONGER_ASTEROID_SPAWN_RATE - difficultyLevel, MIN_STRONGER_ASTEROID_SPAWN_RATE);
    }

    /**
     * Calculates the star spawn rate based on difficulty.
     * Ensures it does not go below the minimum rate.
     * @return Star spawn rate.
     */
    public int getStarSpawnRate() {
        return Math.max(INITIAL_STAR_SPAWN_RATE - difficultyLevel, MIN_STAR_SPAWN_RATE);
    }

    /**
     * Calculates the special item spawn rate based on difficulty.
     * Ensures it does not go below the minimum rate.
     * @return Special item spawn rate.
     */
    public int getSpecialItemSpawnRate() {
        return Math.max(INITIAL_SPECIAL_ITEM_SPAWN_RATE - difficultyLevel, MIN_SPECIAL_ITEM_SPAWN_RATE);
    }
}
