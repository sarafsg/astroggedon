package com.codeforall.online.astrogeddon.movables.NPC;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Class representing a Special Item NPC.
 * Extends the Npc class and inherits its functionality.
 */
public class SpecialItem extends Npc {

    /**
     * Constructor to initialize a Special Item with a given picture.
     * @param picture The Picture object representing the Special Item.
     */
    public SpecialItem(Picture picture) {
        super(picture); // Call the constructor of the superclass (Npc)
        picture.draw(); // Draw the picture
    }
}
