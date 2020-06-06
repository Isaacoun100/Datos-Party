package com.itcr.ce.datosparty.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage blueBox, greenBox, yellowBox, redBox, player1Static, player2Static, player3Static, player4Static, starSeller, mapGuide;
    public static BufferedImage[] settingsButton, playButton, creditsButton,backButton, titleImage,pressEnterImage,
            notOKComputer, notOKComputerIcon, nothingHereMessage, boton1, diceButton, upArrow, downArrow, leftArrow, rightArrow, player1Button, player2Button,
            player3Button, player4Button, returnButton, creditsButtonMenu, volumeUp, volumeDown, gameMusic, soundEffects;

    /**
     * This Method initializes all assets that are used in the game, for them to load properly
     * it uses the crop method to initialize sections of an image as separate objects
     */
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet-Sheet.png"));
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ButtonSprite-Sheet.png"));
        SpriteSheet mapSheet = new SpriteSheet(ImageLoader.loadImage("/textures/MapLayout.png"));

        titleImage = new BufferedImage[2];
        titleImage[0] = sheet.crop(0,0,20,3);
        titleImage[1] = sheet.crop(20,0,20,3);

        pressEnterImage = new BufferedImage[2];
        pressEnterImage[0] = sheet.crop(0,3,7,1);
        pressEnterImage[1] = sheet.crop(0,3,7,1);

        playButton = new BufferedImage[2];
        playButton[0] = sheet.crop(7,3,3,1);
        playButton[1] = sheet.crop(27,3,3,1);

        settingsButton = new BufferedImage[2];
        settingsButton[0] = sheet.crop(10,3,5,1);
        settingsButton[1] = sheet.crop(30,3,5,1);

        creditsButtonMenu = new BufferedImage[2];
        creditsButtonMenu[0] = sheet.crop(15,3,5,1);
        creditsButtonMenu[1] = sheet.crop(35,3,5,1);

        backButton = new BufferedImage[2];
        backButton[0] = sheet.crop(0,4,3,1);
        backButton[1] = sheet.crop(20,4,3,1);

        notOKComputer = new BufferedImage[2];
        notOKComputer[0] = sheet.crop(5,5,9,1);
        notOKComputer[1] = sheet.crop(5,5,9,1);

        notOKComputerIcon = new BufferedImage[2];
        notOKComputerIcon[0] = sheet.crop(3,4,2,2);
        notOKComputerIcon[1] = sheet.crop(23,4,2,2);

        nothingHereMessage = new BufferedImage[2];
        nothingHereMessage[0] = sheet.crop(5,4,9,1);
        nothingHereMessage[1] = sheet.crop(5,4,9,1);

        diceButton = new BufferedImage[2];
        diceButton[0] = buttonSheet.crop(0,0,4,4);
        diceButton[1] = buttonSheet.crop(19,0,4,4);

        upArrow = new BufferedImage[2];
        upArrow[0] = sheet.crop(14,4,1,1);
        upArrow[1] = sheet.crop(15,4,1,1);

        downArrow = new BufferedImage[2];
        downArrow[0] = sheet.crop(16,4,1,1);
        downArrow[1] = sheet.crop(17,4,1,1);

        rightArrow = new BufferedImage[2];
        rightArrow[0] = sheet.crop(14,5,1,1);
        rightArrow[1] = sheet.crop(15,5,1,1);

        leftArrow = new BufferedImage[2];
        leftArrow[0] = sheet.crop(16,5,1,1);
        leftArrow[1] = sheet.crop(17,5,1,1);


        creditsButton= new BufferedImage[2];
        creditsButton[0] = buttonSheet.crop(0,17,7,2);
        creditsButton[1] = buttonSheet.crop(19,17,7,2);

        player4Button= new BufferedImage[2];
        player4Button[0] = buttonSheet.crop(0,14,7,2);
        player4Button[1] = buttonSheet.crop(19,14,7,2);

        player3Button= new BufferedImage[2];
        player3Button[0] = buttonSheet.crop(0,11,7,2);
        player3Button[1] = buttonSheet.crop(19,11,7,2);

        player2Button= new BufferedImage[2];
        player2Button[0] = buttonSheet.crop(0,8,7,2);
        player2Button[1] = buttonSheet.crop(19,8,7,2);

        player1Button= new BufferedImage[2];
        player1Button[0] = buttonSheet.crop(0,5,7,2);
        player1Button[1] = buttonSheet.crop(19,5,7,2);

        volumeUp = new BufferedImage[2];
        volumeUp[0] = buttonSheet.crop(5,0,3,3);
        volumeUp[1] = buttonSheet.crop(24,0,3,3);

        volumeDown = new BufferedImage[2];
        volumeDown[0] = buttonSheet.crop(9,0,3,3);
        volumeDown[1] = buttonSheet.crop(28,0,3,3);

        boton1 = new BufferedImage[2];
        boton1[0] = sheet.crop(14,4,5,1);
        boton1[1] = sheet.crop(34,4,5,1);

        blueBox = sheet.crop(3,6,1,1);
        greenBox = sheet.crop(3,7,1,1);
        yellowBox = sheet.crop(4,6,1,1);
        redBox = sheet.crop(4,7,1,1);

        player1Static = sheet.crop(0,6,1,2);
        player2Static = sheet.crop(7,6,1,2);
        player3Static = sheet.crop(10,6,1,2);
        player4Static = sheet.crop(13,6,1,2);
        starSeller = sheet.crop(16,6,1,2);


        gameMusic= new BufferedImage[1];
        gameMusic[0]= buttonSheet.crop(8,5,8,2);

        soundEffects= new BufferedImage[1];
        soundEffects[0]= buttonSheet.crop(7,8,9,2);

        mapGuide = mapSheet.crop(0,0,100,76);

    }
}
