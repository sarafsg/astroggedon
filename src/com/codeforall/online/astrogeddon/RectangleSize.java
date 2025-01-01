package com.codeforall.online.astrogeddon;

/**
 * Enum representing the size of the screen used in the game.
 */
public enum RectangleSize {

    /**
     * Standard size for the game boundary rectangle.
     */
    STANDARDSIZE(1300,600);

    // The width of the rectangle
    private final int width;
    // The height of the rectangle
    private final int height;

    /**
     * Constructor to initialize the width and height of the rectangle.
     * @param width The width of the rectangle
     * @param height the height of the rectangle
     */
    RectangleSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Method to get the width of the rectangle.
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method to get the height of the rectangle.
     * @return the height of the rectangle
     */
    public int getHeight() {
        return height;
    }
}
