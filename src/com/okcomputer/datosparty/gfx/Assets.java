package com.okcomputer.datosparty.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage titleImage, pressEnterImage, settingsImage, playImage, creditsImage;

    /**
     * This Method initializes all assets that are used in the game, for them to load properly
     * it uses the crop method to initialize sections of an image as separate objects
     */
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSpriteSheet.png"));

        titleImage = sheet.crop(5,5,636,147);
        pressEnterImage = sheet.crop(94,162,288,47);
        settingsImage = sheet.crop(513,162,127,31);
        playImage = sheet.crop(5,162,79,31);
        creditsImage = sheet.crop(392,162,111,31);

    }
}
