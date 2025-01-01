package com.codeforall.online.astrogeddon;

import com.codeforall.online.astrogeddon.gamemanagement.Background;
import com.codeforall.online.astrogeddon.gamemanagement.Game;
import com.codeforall.online.astrogeddon.gamemanagement.GameEngine;
import com.codeforall.online.astrogeddon.inputs.MyKeyboard;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * The Main class serves as the entry point for the Astrogeddon game.
 */
public class Main {

    /**
     * The main method initializes the game environment and starts the game.
     */
    public static void main(String[] args) throws InterruptedException {

        // Create and draw the game boundary rectangle
        RectangleSize size = RectangleSize.STANDARDSIZE;
        Rectangle rectangle = new Rectangle(10 ,10,size.getWidth(), size.getHeight());
        rectangle.draw();

        // Initialize the background image
        Background background = new Background(Game.PREFIX + "space_background.png");

        // Initialize the game engine
        GameEngine gameEngine = new GameEngine();

        // Initialize keyboard controls for the spaceship
        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSpaceship(gameEngine.getSpaceship());
        myKeyboard.init();

        // Start the game engine
        gameEngine.start();
    }
}
