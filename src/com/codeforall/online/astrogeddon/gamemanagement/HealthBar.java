package com.codeforall.online.astrogeddon.gamemanagement;

import com.codeforall.online.astrogeddon.RectangleSize;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.ArrayList;
import java.util.List;

//Manages the health bar
public class HealthBar {

    //Health representation
    private static final int FULL_HEART = 20; // Represents 20 health points per full heart
    private static final int HALF_HEART = 10; // Represents 10 health points per half heart
    private static final int HEART_SPACING = 2; // Spacing between hearts

    // List to store heart pictures
    private List<Picture> hearts;

    // Images
    private String emptyHeartImage = Game.PREFIX + "heart_empty.png";
    private String halfHeartImage = Game.PREFIX + "heart_half.png";
    private String fullHeartImage = Game.PREFIX + "heart_full.png";

    // Coordinates for drawing hearts
    private int startX;
    private int startY;

    //Initialize health bar
    public HealthBar(int screenWidth, int screenHeight) {
        hearts = new ArrayList<>();
        Picture tempHeart = new Picture(0, 0, fullHeartImage); // Temporary heart to get width
        int heartWidth = tempHeart.getWidth();
        int heartHeight = tempHeart.getHeight();

        // Calculate starting X position for right alignment
        //Size of the rectangle - the size of 5 hearts - spacing between hearts
        this.startX = RectangleSize.STANDARDSIZE.getWidth() -(heartWidth*5) - (HEART_SPACING*4);
        this.startY = 20; //Distance from the top

        initHealthBar(heartWidth, heartHeight);
    }

    //Initializes the health bar with full hearts
    private void initHealthBar(int heartWidth, int heartHeight) {
        int xOffset = startX;

        for (int i = 0; i < 5; i++) {
            Picture heart = new Picture(xOffset, startY, fullHeartImage);
            hearts.add(heart);
            heart.draw();
            xOffset += heartWidth + HEART_SPACING; // Update position for the next heart
        }
    }

    //Updates the health bar based on the ship's HP
    public void updateHealth(int health) {
        // Ensure that the health is within the bounds of 0 to 100
        health = Math.max(0, Math.min(100, health));

        // Update hearts based on the current health
        for (int i = 0; i < hearts.size(); i++) {
            Picture heart = hearts.get(i);
            if (health >= FULL_HEART) {
                heart.load(fullHeartImage);
                health -= FULL_HEART;
            } else if (health >= HALF_HEART) {
                heart.load(halfHeartImage);
                health -= HALF_HEART;
            } else {
                heart.load(emptyHeartImage);
            }
            heart.draw();
        }
    }
}
