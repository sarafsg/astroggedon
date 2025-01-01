package com.codeforall.online.astrogeddon.movables;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Abstract class for game objects that can move on the screen.
 * Provides basic methods to move the objects and determine object's boundaries.
 */
public abstract class AbstractMovable implements Movable {

    public Picture picture; // The visual representation of the movable object

    /**
     * Initializes a movable object with a given picture.
     * @param picture The picture that represents the game object
     */
    public AbstractMovable(Picture picture) {
        this.picture = picture; // Set the object's picture
    }

    /**
     * Gets the top edge's Y coordinate of the object.
     * @return The Y coordinate of the top edge
     */
    public int getTop(){
        return picture.getY();
    }

    /**
     * Gets the left edge's X coordinate of the object.
     * @return The X coordinate of the left edge
     */
    public int getLeft(){
        return picture.getX();
    }

    /**
     * Gets the right edge's X coordinate of the object.
     * @return The X coordinate of the right edge
     */
    public int getRight(){
        return picture.getX() + picture.getWidth();
    }

    /**
     * Gets the bottom edge's Y coordinate of the object.
     * @return The Y coordinate of the bottom edge
     */
    public int getBottom(){
        return picture.getY() + picture.getHeight();
    }

    /**
     * Moves the object to the left by 10 pixels.
     */
    public void moveLeft(){
        picture.translate(-10, 0);
    }

    /**
     * Moves the object to the right by 10 pixels.
     */
    public void moveRight(){
        picture.translate(10, 0);
    }

    /**
     * Moves the object up by 10 pixels.
     */
    public void moveUp(){
        picture.translate(0, -10);
    }

    /**
     * Moves the object down by 10 pixels.
     */
    public void moveDown(){
        picture.translate(0, 10);
    }
}
