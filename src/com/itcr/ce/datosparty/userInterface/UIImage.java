package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject{

    private BufferedImage image;
    private ClickListener clicker;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param image
     */
    public UIImage(float x, float y, int width, int height, BufferedImage image) {
        super(x*16, y*16, width*16, height*16);
        this.image = image;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
    }
}
