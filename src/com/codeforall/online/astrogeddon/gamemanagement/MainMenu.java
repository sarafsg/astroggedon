package com.codeforall.online.astrogeddon.gamemanagement;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.File;

public class MainMenu implements KeyboardHandler {

    // Image displayed when the main menu is open
    private Picture mainMenuPicture;
    //Image for main menu Start button
    private Picture mainMenuStart;
    //Image for main menu Shop button
    private Picture mainMenuShop;
    //Image for main menu Help button
    private Picture mainMenuHelp;
    //Image for Shop Bullets button
    private Picture missileBullets;
    //Image for Shop Rockets button
    private Picture missileRockets;

    // Keyboard object to listen for key presses
    private Keyboard keyboard;
    // Reference to the GameEngine to control game flow
    private GameEngine gameEngine;
    // Int to operate a switch to select an action in Main Menu and/or Shop
    private int selectedOption;
    //Keyboard behaviours
    private KeyboardEvent leftMenu;
    private KeyboardEvent rightMenu;
    private KeyboardEvent downMenu;
    private KeyboardEvent upMenu;
    private KeyboardEvent returnOption;
    private KeyboardEvent selectOption;
    //Main menu music player
    private MusicPlayer mainMenuSoundtrack;

    public MainMenu (GameEngine gameEngine){
        //Assign the Game Engine
        this.gameEngine = gameEngine;

        // Load and display the game over image
        openMainMenu();


        //Sets selected option as 1 for start button
        selectedOption = 1;

        // Initialize keyboard controls
        keyboard = new Keyboard(this); // Create a new Keyboard object and set this as the handler

        // Create and configure the keyboard event for the 'A' key
        leftMenu = new KeyboardEvent(); // Create a new KeyboardEvent object
        leftMenu.setKey(KeyboardEvent.KEY_A); // Set the key to 'A'
        leftMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(leftMenu); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'D' key
        rightMenu = new KeyboardEvent(); // Create a new KeyboardEvent object
        rightMenu.setKey(KeyboardEvent.KEY_D); // Set the key to 'D'
        rightMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(rightMenu); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'S' key
        downMenu = new KeyboardEvent(); // Create a new KeyboardEvent object
        downMenu.setKey(KeyboardEvent.KEY_S); // Set the key to 'S'
        downMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(downMenu); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'W' key
        upMenu = new KeyboardEvent(); // Create a new KeyboardEvent object
        upMenu.setKey(KeyboardEvent.KEY_W); // Set the key to 'W'
        upMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(upMenu); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'SPACE' key
        selectOption = new KeyboardEvent(); // Create a new KeyboardEvent object
        selectOption.setKey(KeyboardEvent.KEY_SPACE); // Set the key to 'SPACE'
        selectOption.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(selectOption); // Add this event listener to the keyboard

        // Create and configure the keyboard event for the 'R' key
        returnOption = new KeyboardEvent(); // Create a new KeyboardEvent object
        returnOption.setKey(KeyboardEvent.KEY_R); // Set the key to 'R'
        returnOption.setKeyboardEventType(KeyboardEventType.KEY_PRESSED); // Set the event type to key pressed
        keyboard.addEventListener(returnOption); // Add this event listener to the keyboard

        mainMenuSoundtrack = new MusicPlayer();
        addSoundtrack();

    }

    public void hide() {
        mainMenuPicture.delete(); // Remove the main menu picture from the screen
        mainMenuShop.delete();
        mainMenuStart.delete();
        mainMenuHelp.delete();

        keyboard.removeEventListener(leftMenu);
        keyboard.removeEventListener(rightMenu);
        keyboard.removeEventListener(downMenu);
        keyboard.removeEventListener(upMenu);
        keyboard.removeEventListener(selectOption);
        keyboard.removeEventListener(returnOption);

    }

    public void startToShop(){
        mainMenuStart.delete();
        mainMenuStart = new Picture(279, 315, Game.PREFIX + "start.png");
        mainMenuStart.draw();
        mainMenuShop.delete();
        mainMenuShop = new Picture(591, 300, Game.PREFIX + "shop_selected.png");
        mainMenuShop.draw();
        selectedOption ++;

    }

    public void shopToHelp(){
        mainMenuShop.delete();
        mainMenuShop = new Picture(591, 315, Game.PREFIX + "shop.png");
        mainMenuShop.draw();
        mainMenuHelp.delete();
        mainMenuHelp = new Picture(903, 300, Game.PREFIX + "help_selected.png");
        mainMenuHelp.draw();
        selectedOption ++;
    }

    public void helpToShop(){
        mainMenuShop.delete();
        mainMenuShop = new Picture(591, 300, Game.PREFIX + "shop_selected.png");
        mainMenuShop.draw();
        mainMenuHelp.delete();
        mainMenuHelp = new Picture(903, 315, Game.PREFIX + "help.png");
        mainMenuHelp.draw();
        selectedOption --;
    }

    public void shopToStart(){
        mainMenuStart.delete();
        mainMenuStart = new Picture(279, 300, Game.PREFIX + "start_selected.png");
        mainMenuStart.draw();
        mainMenuShop.delete();
        mainMenuShop = new Picture(591, 315, Game.PREFIX + "shop.png");
        mainMenuShop.draw();
        selectedOption --;
    }

    public void openMainMenu(){

        if(mainMenuPicture != null){
            hide();
        }
        mainMenuPicture = new Picture(10, 10, Game.PREFIX + "main_menu.png"); // Create a new Picture object
        mainMenuPicture.draw(); // Draw the game over picture on the screen
        mainMenuStart = new Picture(279, 300, Game.PREFIX + "start_selected.png");
        mainMenuStart.draw();
        mainMenuShop = new Picture(591, 315, Game.PREFIX + "shop.png");
        mainMenuShop.draw();
        mainMenuHelp = new Picture(903, 315, Game.PREFIX + "help.png");
        mainMenuHelp.draw();
    }
    public void openMainMenuListeners(){
        keyboard.addEventListener(leftMenu); // Add this event listener to the keyboard
        keyboard.addEventListener(rightMenu); // Add this event listener to the keyboard
        keyboard.addEventListener(selectOption); // Add this event listener to the keyboard
        keyboard.addEventListener(returnOption); // Add this event listener to the keyboard
    }
    public void openShopMenuListeners(){
        keyboard.addEventListener(downMenu);
        keyboard.addEventListener(upMenu);
        keyboard.addEventListener(returnOption);
        keyboard.addEventListener(selectOption);
    }

    public void openShopMenu (){
        hide();
        mainMenuPicture = new Picture(10, 10, Game.PREFIX + "shop_menu.png"); // Create a new Picture object
        mainMenuPicture.draw(); // Draw the game over picture on the screen

        missileBullets = new Picture(125, 250, Game.PREFIX + "shop_bullets_selected.png");
        missileBullets.draw();
        missileRockets = new Picture(125, 400, Game.PREFIX + "shop_rockets.png");
        missileRockets.draw();

        selectedOption = 11;
    }

    public void closeShopOptions(){
        missileBullets.delete();
        missileRockets.delete();
    }

    public void bulletsToRockets(){

        missileBullets.delete();
        missileBullets = new Picture(125, 250, Game.PREFIX + "shop_bullets.png");
        missileBullets.draw();
        missileRockets.delete();
        missileRockets = new Picture(125, 400, Game.PREFIX + "shop_rockets_selected.png");
        missileRockets.draw();
        selectedOption ++;
    }

    public void rocketsToBullets(){
        missileBullets.delete();
        missileBullets = new Picture(125, 250, Game.PREFIX + "shop_bullets_selected.png");
        missileBullets.draw();
        missileRockets.delete();
        missileRockets = new Picture(125, 400, Game.PREFIX + "shop_rockets.png");
        missileRockets.draw();
        selectedOption --;
    }

    public void openHelpMenu(){
        hide();
        mainMenuPicture = new Picture(10, 10, Game.PREFIX + "help_menu.png"); // Create a new Picture object
        mainMenuPicture.draw(); // Draw the game over picture on the screen

    }

    public void addSoundtrack(){
        File musicFile = new File(Game.PREFIX + "astrogeddon_main_menu.wav");
        if (musicFile.exists()) {
            mainMenuSoundtrack.playMusic(Game.PREFIX + "astrogeddon_main_menu.wav");
        } else {
            System.out.println("Arquivo de áudio não encontrado: " + Game.PREFIX + "astrogeddon_main_menu.wav");
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_D) { // Check if the 'D' key was pressed
            switch (selectedOption){
                case 1:
                    startToShop();
                    break;
                case 2:
                    shopToHelp();
                    break;
            }
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_A) { // Check if the 'A' key was pressed
            switch (selectedOption){
                case 2:
                    shopToStart();
                    break;
                case 3:
                    helpToShop();
                    break;
            }
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) { // Check if the "SPACE" key was pressed
            switch (selectedOption){
                case 1: //starts the game
                    gameEngine.setGameStarted(true);
                    hide();
                    mainMenuSoundtrack.stopMusic();
                    closeShopOptions();
                    break;
                case 2: //open shop menu
                    openShopMenu();
                    //keyboardlisteners for shop
                    openShopMenuListeners();
                    break;
                case 3: //open help menu
                    openHelpMenu();
                    keyboard.addEventListener(returnOption); // Add this event listener to the keyboard
                    break;
            }
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            openMainMenu();
            selectedOption=1;
            openMainMenuListeners();
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            switch (selectedOption){
                case 11:
                    bulletsToRockets();
                    System.out.println("ROCKETS TO BULLEts");
                    break;
                // case 2:
                //     rocketsToBullets();
                //     break;
            }
        }else if (keyboardEvent.getKey() == KeyboardEvent.KEY_W){
            switch (selectedOption){
                //case 1:
                //    bulletsToRockets();
                //    break;
                case 12:
                    rocketsToBullets();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
