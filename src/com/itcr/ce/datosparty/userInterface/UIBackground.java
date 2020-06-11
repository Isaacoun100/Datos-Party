package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIBackground extends UIObject{
    private BufferedImage image;

    public UIBackground(BufferedImage image, String id) {
        super(0, 0, 800*2, 608*2, id);
        this.image = image;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, 800*2, 608*2, null);
    }

    @Override
    public void onClick() {
    }
}
