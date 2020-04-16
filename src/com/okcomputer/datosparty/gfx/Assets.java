package com.okcomputer.datosparty.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage titleImage, pressEnterImage;
    public static BufferedImage[] settingsButton, playButton, creditsButton;

    /**
     * This Method initializes all assets that are used in the game, for them to load properly
     * it uses the crop method to initialize sections of an image as separate objects
     */
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSpriteSheet.png"));

        titleImage = sheet.crop(5,5,636,147);
        pressEnterImage = sheet.crop(118,162,288,47);

        playButton = new BufferedImage[2];
        playButton[0] = sheet.crop(5+23,162,103-(23*2),23);
        playButton[1] = sheet.crop(5,162,103,23);

        settingsButton = new BufferedImage[2];
        settingsButton[0] = sheet.crop(416+23,162,162-(23*2),23);
        settingsButton[1] = sheet.crop(416,162,162,23);

        creditsButton = new BufferedImage[2];
        creditsButton[0] = sheet.crop(416+23,195,128-(23*2),23);
        creditsButton[1] = sheet.crop(416,195,128,23);


        //creditsImage = sheet.crop(392,162,111,31);

    }
}
