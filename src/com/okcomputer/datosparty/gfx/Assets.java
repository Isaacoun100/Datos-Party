package com.okcomputer.datosparty.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage titleImage, pressEnterImage, settingsImage, playImage, creditsImage;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSpriteSheet.png"));

        titleImage = sheet.crop(5,5,636,147);
        pressEnterImage = sheet.crop(94,162,288,47);
        settingsImage = sheet.crop(5,162,79,31);
        playImage = sheet.crop(5,162,79,31);
        creditsImage = sheet.crop(392,162,111,31);

    }
}
