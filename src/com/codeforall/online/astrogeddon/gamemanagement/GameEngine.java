package com.codeforall.online.astrogeddon.gamemanagement;

import com.codeforall.online.astrogeddon.movables.NPC.*;
import com.codeforall.online.astrogeddon.movables.Spaceship;
import com.codeforall.online.astrogeddon.RectangleSize;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages game logic, including NPC spawning, updates, and collisions.
 * Controls the game loop.
 */
public class GameEngine {

    private List<Npc> npcs; // Lists to keep track of all NPCs in the game
    private Spaceship spaceship; // Spaceship controlled by the player
    private NpcFactory npcFactory; // Factory to create NPCs
    private DifficultyManager difficultyManager; // Difficulty manager to adjust spawn rates
    private HealthBar healthBar; // Health bar to visually display the current health
    private Score score; // Score text to visualize the score in game
    private DifficultyDisplay difficultyDisplay; // Difficulty display to visualize the difficulty in game
    private boolean isGameOver; // Indicates if the game is over
    private GameOverMenu gameOverMenu; // The menu displayed when the game ends
    private boolean isGameStarted;  // Indicates if the start button was pressed
    private MainMenu mainMenu; // The main menu is displayed before game starts
    private CheatCode cheatCode; // The cheat codes start with the game
    private int frameCount; // Counts the number of frames
    private HighScoreManager highScoreManager; // Manages the high scores

    /**
     * Constructor to initialize the game engine.
     */
    public GameEngine() {

        // Initialize the list for storing all NPCs
        this.npcs = new ArrayList<>();
        // Initialize the NPC factory to create new NPCs
        this.npcFactory = new NpcFactory();
        // Initialize the spaceship for this game instance
        this.spaceship = new Spaceship(new Picture(15, 300, Game.PREFIX + "spaceship.png"));
        // Initialize the difficulty manager to adjust game difficulty
        this.difficultyManager = new DifficultyManager();
        // Initialize the Health bar
        this.healthBar = new HealthBar(20, 10);
        // Initialize the HighScoreManager
        this.highScoreManager = new HighScoreManager();
        // Initialize the score text with reference to HighScoreManager
        this.score = new Score(RectangleSize.STANDARDSIZE.getWidth(), RectangleSize.STANDARDSIZE.getHeight(), spaceship, highScoreManager);
        // Initialize the difficulty display
        this.difficultyDisplay = new DifficultyDisplay(RectangleSize.STANDARDSIZE.getWidth(), RectangleSize.STANDARDSIZE.getHeight(), difficultyManager);
        // Set the game over variable to false initially
        this.isGameOver = false;
        // Initialize the game over menu as null
        this.gameOverMenu = null;
        // Initialize frameCount to zero
        this.frameCount = 0;
        // Set the Main menu to false initially
        this.isGameStarted = false;
        // Initialize the main menu
        this.mainMenu = new MainMenu(this);
        // Initialize cheat codes
        this.cheatCode = new CheatCode(spaceship, score);
    }

    // Getter method for HighScoreManager
    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    /**
     * Method to start the game loop.
     * This loop continuously updates the game state, spawns NPCs, checks for collisions, and removes NPCs.
     * @throws InterruptedException if the game loop is interrupted
     */
    public void start() throws InterruptedException {
        // Reset frame count at the start of the game
        frameCount = 0;

        // Display the main menu until the game starts
        displayMainMenu();

        // Loop to keep the game running until game over
        while (!isGameOver && isGameStarted) {
            // Increment the frame counter on each loop iteration
            frameCount++;

            // Clear the list of occupied y positions to allow new positions in the next frame
            npcFactory.clearOccupiedYPositions();

            // Check if it's time to spawn a new NPC
            spawnNPCs();

            // Increase the difficulty periodically
            increaseDifficultyPeriodically();

            // Update and remove NPCs
            updateAndRemoveNPCs();

            // Check if the game should end
            checkGameOver();

            // Update easy mode frame count
            spaceship.updateEasyMode();

            sendFrameCountCheatCore();
            cheatCode.scoreCheatPictureRemove();

            // Pause the game loop for 50 milliseconds to control the speed of the game
            Thread.sleep(50);
        }

        // Ensure the game over menu is displayed after exiting the loop
        displayGameOverMenu();
    }

    /**
     * Method to check if it's time to spawn new NPCs (asteroids, stars, special items)
     */
    private void spawnNPCs() {
        // If the current frameCount is a multiple of Asteroid spawn rate
        if (frameCount % difficultyManager.getAsteroidSpawnRate() == 0) {
            npcs.add(npcFactory.createAsteroid()); // Add a new asteroid to the list
        }
        // If the current frameCount is a multiple of StrongerAsteroid spawn rate
        if (frameCount % difficultyManager.getStrongerAsteroidSpawnRate() == 0) {
            npcs.add(npcFactory.createStrongerAsteroid()); // Add a new stronger asteroid to the list
        }
        // If the current frameCount is a multiple of Star spawn rate
        if (frameCount % difficultyManager.getStarSpawnRate() == 0) {
            npcs.add(npcFactory.createStar()); // Add a new star to the list
        }
        // If the current frameCount is a multiple of SpecialItem spawn rate
        if (frameCount % difficultyManager.getSpecialItemSpawnRate() == 0) {
            npcs.add(npcFactory.createSpecialItem()); // Add a new special item to the list
        }
        // Spawn EasyModeItem every 500 frames
        if (frameCount % 500 == 0) {
            npcs.add(npcFactory.createEasyModeItem()); // Add a new easy mode item to the list
        }
    }

    /**
     * Method to increase the game difficulty periodically
     */
    private void increaseDifficultyPeriodically() {
        // Increase difficulty every 100 frames
        if (frameCount % 100 == 0) {
            difficultyManager.increaseDifficulty();
            // Update difficulty display
            difficultyDisplay.updateDifficulty();
            System.out.println("Difficulty increased! Current difficulty level: " + difficultyManager.getDifficultyLevel());
        }
    }

    /**
     * Method to update and remove NPCs (asteroids, stars, special items)
     */
    private void updateAndRemoveNPCs() {
        // Create an iterator to iterate through the list of NPCs
        Iterator<Npc> npcIterator = npcs.iterator();

        // Iterate through each NPC in the list
        while (npcIterator.hasNext()) {
            Npc npc = npcIterator.next(); // Get the current NPC
            npc.updateNPC(); // Update the NPC's position

            // Check if the NPC has moved off the left side of the screen
            if (npc.getLeft() <= 0) {
                npc.deleteNPCPicture(); // Delete the NPC's picture
                npcIterator.remove(); // Remove the NPC from the list
            }
            // Check for collisions between the spaceship and the NPC
            else if (checkSpaceshipCollision(npc)) {
                handleCollision(npc); // Handle the collision
                npc.deleteNPCPicture(); // Delete the NPC's picture
                npcIterator.remove(); // Remove the NPC from the list
            }
        }
    }

    /**
     * Method to check if the spaceship collides with the given NPC.
     * @param npc The NPC to check collision with.
     * @return true if colliding, false otherwise.
     */
    private boolean checkSpaceshipCollision(Npc npc) {
        // If NPC is an Asteroid, check collision with Asteroid
        if (npc instanceof Asteroid) {
            return spaceship.isCollidingWithAsteroid((Asteroid) npc);
        }
        // If NPC is a StrongerAsteroid, check collision with StrongerAsteroid
        else if (npc instanceof StrongerAsteroid) {
            return spaceship.isCollidingWithStrongerAsteroid((StrongerAsteroid) npc);
        }
        // If NPC is a Star, check collision with Star
        else if (npc instanceof Star) {
            return spaceship.isCollidingWithStar((Star) npc);
        }
        // If NPC is a SpecialItem, check collision with SpecialItem
        else if (npc instanceof SpecialItem) {
            return spaceship.isCollidingWithSpecialItem((SpecialItem) npc);
        }
        // If NPC is an EasyModeItem, check collision with EasyModeItem
        else if (npc instanceof EasyModeItem) {
            return spaceship.isCollidingWithEasyModeItem((EasyModeItem) npc);
        }
        // If no match, return false
        return false;
    }

    /**
     * Method to handle collisions between the spaceship and the NPC.
     * Handle collision based on the type of NPC.
     * @param npc The NPC involved in the collision.
     */
    private void handleCollision(Npc npc) {
        // Handle collision with Asteroid
        if (npc instanceof Asteroid) {
            spaceship.reduceHealth(); // Reduce health of spaceship
            healthBar.updateHealth(spaceship.getHealth()); // Update the health bar
        }
        // Handle collision with StrongerAsteroid
        else if (npc instanceof StrongerAsteroid) {
            spaceship.reduceHealth();
            spaceship.reduceHealth(); // Reduce health of spaceship by double
            healthBar.updateHealth(spaceship.getHealth()); // Update the health bar
        }
        // Handle collision with Star
        else if (npc instanceof Star) {
            spaceship.increaseScore(); // Increase score of spaceship
            score.updateScore(); // Update the score
        }
        // Handle collision with SpecialItem
        else if (npc instanceof SpecialItem) {
            spaceship.increaseHealth(); // Increase health of spaceship
            healthBar.updateHealth(spaceship.getHealth()); // Update the health bar
        }
        // Handle collision with EasyModeItem
        else if (npc instanceof EasyModeItem) {
            spaceship.activateEasyMode(); // Activate easy mode (immunity)
        }
    }

    /**
     * Method to check if the game has started.
     * If the game has not started, it will display the main menu.
     */
    private void checkGameStarted(){
        if (!this.isGameStarted) {
            displayMainMenu();
        }
    }

    /**
     * Method to check if the game is over based on the spaceship's health
     */
    private void checkGameOver() {
        // If the spaceship's health is zero
        if (spaceship.getHealth() == 0) {
            // Set the game over variable to true
            isGameOver = true;
            // Display the game over menu
            displayGameOverMenu();
        }
    }

    /**
     * Method to display the main menu and wait for the game to start
     */
    private void displayMainMenu(){
        // Display the main menu until the game is started
        while (!isGameStarted) {
            try {
                Thread.sleep(100); // Brief pause to slow down the loop
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruption
            }
            if(isGameStarted){
                spaceship.addSoundtrack();
            }
        }
    }

    /**
     * Method to display the game over menu when the game ends
     */
    private void displayGameOverMenu() {
        // Initialize the game over menu if it's null
        if (gameOverMenu == null) {
            gameOverMenu = new GameOverMenu(this);
        }

        // Add current score to high scores if it qualifies
        highScoreManager.addScore(spaceship.getScore());
        // Save the updated high scores list to the persistent storage
        highScoreManager.saveHighScores();

        // Keep the game over menu visible while the game is over
        while (isGameOver) {
            try {
                Thread.sleep(100); // Brief pause to slow down the loop
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruption
            }
        }
    }

    /**
     * Method to restart the game.
     * Resets game variables and clears NPC lists
     */
    public void restartGame() {
        isGameOver = true; // Set the game over variable to true
        spaceship.reset(); // Reset spaceship state
        clearNpcs(); // Clear lists of NPCs
        difficultyManager.resetDifficulty(); // Reset game difficulty
        difficultyDisplay.updateDifficulty(); // Update the difficulty display
        System.out.println("Difficulty reset to: " + difficultyManager.getDifficultyLevel());
        frameCount = 0; // Reset frame count
        System.out.println("frameCount reset to: " + frameCount);
        healthBar.updateHealth(spaceship.getHealth()); // Reset health bar

        // Hide the game over menu if it exists
        if (gameOverMenu != null) {
            gameOverMenu.hide();
            gameOverMenu = null;
        }
        spaceship.stopMusic();
        // Show the main menu
        mainMenu.openMainMenu();
        mainMenu.openMainMenuListeners(); // Add event listeners for the main menu
        mainMenu.addSoundtrack();
    }

    /**
     * Method to clear all NPCs from the game
     */
    private void clearNpcs() {
        // Iterate through all NPCs and remove their pictures
        for (Npc npc : npcs) {
            npc.deleteNPCPicture();
        }
        // Clear the list of NPCs
        npcs.clear();
    }

    /**
     * Method to quit the game
     */
    public void quitGame() {
        // Exit the game
        System.exit(0);
    }

    private void sendFrameCountCheatCore(){
        cheatCode.setFrameCount(frameCount);
    }

    /**
     * Setter for gameStarted variable
     * @param b Boolean value indicating if the game has started
     */
    public void setGameStarted(boolean b){
        this.isGameStarted = b;
        isGameOver = false; // Set the game over variable to false
    }

    /**
     * Getter for isGameStarted variable
     * @return Boolean value indicating if the game has started
     */
    public boolean getIsGameStarted(){
        return isGameStarted;
    }

    /**
     * Getter for the spaceship instance.
     * @return the spaceship controlled by the player.
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }
}
