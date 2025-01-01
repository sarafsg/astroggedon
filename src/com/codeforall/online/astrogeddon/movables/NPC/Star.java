package com.codeforall.online.astrogeddon.movables.NPC;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Class representing a Star NPC.
 * Extends the Npc class and inherits its functionality.
 */
public class Star extends Npc {

    /**
     * Constructor to initialize a Star with a given picture.
     * @param picture The Picture object representing the Star.
     */
    public Star(Picture picture) {
        super(picture); // Call the constructor of the superclass (Npc)
        picture.draw(); // Draw the picture
    }
}
