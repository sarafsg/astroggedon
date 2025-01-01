package com.codeforall.online.astrogeddon.movables.NPC;

import com.codeforall.online.astrogeddon.movables.AbstractMovable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Abstract class for Non-Playable Characters (NPCs) that move left across the screen.
 * This class is used as a base for specific types of NPCs (Asteroids, Stars, and Special Items).
 */
public abstract class Npc extends AbstractMovable {

    /**
     * Constructor to initialize an NPC with a given picture.
     * @param picture The Picture object representing the NPC.
     */
    public Npc(Picture picture) {
        // Call the constructor of the superclass (AbstractMovable)
        super(picture);
        picture.draw(); // Draw the picture
    }

    /**
     * Method to update the state of the NPC.
     * Moves the NPC to the left and deletes its picture if it moves off-screen.
     */
    public void updateNPC() {
        // Move the NPC to the left
        moveLeft();
        // Check if the NPC has moved off the left side of the screen
        if (getLeft() <= 0) {
            deleteNPCPicture(); // Delete the NPC's picture
        }
    }

    /**
     * Method to delete the NPC picture.
     * This method is responsible for removing the visual representation of the NPC.
     */
    public void deleteNPCPicture() {
        picture.delete(); // Remove the picture from the screen
    }
}
