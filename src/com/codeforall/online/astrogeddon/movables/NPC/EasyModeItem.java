package com.codeforall.online.astrogeddon.movables.NPC;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Class representing an Easy Mode Item NPC.
 * Extends the Npc class and inherits its functionality.
 */
public class EasyModeItem extends Npc {

    /**
     * Constructor to initialize an Easy Mode Item with a given picture.
     * @param picture The Picture object representing the Easy Mode Item.
     */
    public EasyModeItem(Picture picture) {
        super(picture); // Call the constructor of the superclass (Npc)
        picture.draw(); // Draw the picture
    }
}
