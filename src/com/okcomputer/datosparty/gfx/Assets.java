package com.okcomputer.datosparty.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] settingsButton, playButton, creditsButton,backButton, titleImage,pressEnterImage, notOKComputer, notOKComputerIcon, nothingHereMessage, boton1;

    /**
     * This Method initializes all assets that are used in the game, for them to load properly
     * it uses the crop method to initialize sections of an image as separate objects
     */
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet-Sheet.png"));


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

        creditsButton = new BufferedImage[2];
        creditsButton[0] = sheet.crop(15,3,5,1);
        creditsButton[1] = sheet.crop(35,3,5,1);

        backButton = new BufferedImage[2];
        backButton[0] = sheet.crop(0,4,3,1);
        backButton[1] = sheet.crop(20,4,3,1);

        notOKComputer = new BufferedImage[2];
        notOKComputer[0] = sheet.crop(5,5,9,1);
        notOKComputer[1] = sheet.crop(5,5,9,1);

        notOKComputerIcon = new BufferedImage[2];
        notOKComputerIcon[0] = sheet.crop(3,4,2,2);
        notOKComputerIcon[1] = sheet.crop(3,4,2,2);

        nothingHereMessage = new BufferedImage[2];
        nothingHereMessage[0] = sheet.crop(5,4,9,1);
        nothingHereMessage[1] = sheet.crop(5,4,9,1);

        boton1 = new BufferedImage[2];
        boton1[0] = sheet.crop(14,4,5,1);
        boton1[1] = sheet.crop(34,4,5,1);











    }
}
