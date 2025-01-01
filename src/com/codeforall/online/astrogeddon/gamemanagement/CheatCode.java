package com.codeforall.online.astrogeddon.gamemanagement;

import com.codeforall.online.astrogeddon.gamemanagement.GameEngine;
import com.codeforall.online.astrogeddon.movables.Spaceship;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CheatCode implements KeyboardHandler {
    private String scoreCode;
    private String insertedCode;
    private Keyboard keyboard;
    private KeyboardEvent keyM;
    private KeyboardEvent keyO;
    private KeyboardEvent keyN;
    private KeyboardEvent keyE;
    private KeyboardEvent keyY;
    private Spaceship spaceship;
    private Score score;
    private Picture scoreCodePicture;
    private boolean isPictureDisplayed;
    private int frameCount;
    int frameCountTimer;



    public CheatCode(Spaceship spaceship, Score score){
        this.scoreCode = "money";
        keyboard = new Keyboard(this);

        this.score = score;
        this.spaceship = spaceship;
        this.insertedCode ="";

        keyM = new KeyboardEvent(); // Create a new KeyboardEvent object
        keyM.setKey(KeyboardEvent.KEY_M); // Set the key to 'M'
        keyM.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(keyM);

        keyO = new KeyboardEvent(); // Create a new KeyboardEvent object
        keyO.setKey(KeyboardEvent.KEY_O); // Set the key to 'M'
        keyO.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(keyO);

        keyN = new KeyboardEvent(); // Create a new KeyboardEvent object
        keyN.setKey(KeyboardEvent.KEY_N); // Set the key to 'M'
        keyN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(keyN);

        keyE = new KeyboardEvent(); // Create a new KeyboardEvent object
        keyE.setKey(KeyboardEvent.KEY_E); // Set the key to 'M'
        keyE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(keyE);

        keyY = new KeyboardEvent(); // Create a new KeyboardEvent object
        keyY.setKey(KeyboardEvent.KEY_Y); // Set the key to 'M'
        keyY.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(keyY);
    }

    private void scoreCheatPictureAdd(){
        scoreCodePicture = new Picture(10,10,Game.PREFIX + "cheat_code_score.png");
        scoreCodePicture.draw();
        isPictureDisplayed=true;
        frameCountTimer = frameCount;
        System.out.println("pictureadd");
    }

    public void scoreCheatPictureRemove(){
        if (frameCount >= frameCountTimer + 20 && isPictureDisplayed){
            scoreCodePicture.delete();
            System.out.println("picture remove");
            isPictureDisplayed = false;
        }
    }

    private void addScoreCheat(){
        spaceship.setScore(100);
        score.updateScore();
        System.out.println("Added 100 score points");
        insertedCode = "";
        scoreCheatPictureAdd();

    }

    private void addScoreCheatChecker(){
        if(insertedCode.length() > 4 && !insertedCode.equals(scoreCode)){
            insertedCode="";
        }else if(insertedCode.equals(scoreCode)){
            addScoreCheat();
        }
    }

    public void setFrameCount(int frameCount){
        this.frameCount=frameCount;
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_M) { // Check if the 'D' key was pressed
            insertedCode += "m";
            addScoreCheatChecker();
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_O){
            insertedCode += "o";
            addScoreCheatChecker();
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_N){
            insertedCode += "n";
            addScoreCheatChecker();
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_E){
            insertedCode += "e";
            addScoreCheatChecker();
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_Y){
            insertedCode += "y";
            addScoreCheatChecker();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
