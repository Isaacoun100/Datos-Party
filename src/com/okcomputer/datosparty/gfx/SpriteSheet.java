package com.okcomputer.datosparty.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage crop(int gridX, int gridY, int width, int height){
        return sheet.getSubimage(gridX*16, gridY*16, width*16, height*16);
    }
}
