package com.codeforall.online.astrogeddon.gamemanagement;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private Picture background;

    public Background(String imagePath) {
        this.background = new Picture(10, 10, imagePath);
        this.background.draw();
    }
}