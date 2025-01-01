package com.codeforall.online.astrogeddon.inputs;

import com.codeforall.online.astrogeddon.movables.Spaceship;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Handles keyboard input for controlling the spaceship.
 */
public class MyKeyboard implements KeyboardHandler {

    private Keyboard keyboard; // The keyboard object to listen for key events
    private Spaceship spaceship; // The spaceship object to control

    /**
     * Sets the spaceship instance for this keyboard handler.
     * @param spaceship The spaceship to be controlled
     */
    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }

    /**
     * Initializes keyboard events and sets up listeners.
     */
    public void init() {
        // Create a new Keyboard instance
        keyboard = new Keyboard(this);

        // Define keyboard events for movement keys

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D); // Key for moving right
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Trigger when the key is pressed

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A); // Key for moving left
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Trigger when the key is pressed

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W); // Key for moving up
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Trigger when the key is pressed

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S); // Key for moving down
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Trigger when the key is pressed

        // Add the defined keyboard events to the keyboard listener
        keyboard.addEventListener(right);
        keyboard.addEventListener(left);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
    }

    /**
     * Handles key press events to control the spaceship.
     * @param keyboardEvent The keyboard event that occurred
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_D) {
            spaceship.moveRight(); // Move the spaceship right
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_A) {
            spaceship.moveLeft(); // Move the spaceship left
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            spaceship.moveDown(); // Move the spaceship down
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_W) {
            spaceship.moveUp(); // Move the spaceship up
        }
    }

    /**
     * Handles key release events (not used in this implementation).
     * @param keyboardEvent The keyboard event that occurred
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        // No action needed
    }
}
