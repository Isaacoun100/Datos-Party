package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is the responsible of loading the backgrounds
 */
public class UIBackground extends UIObject{
    private BufferedImage image;

    /**
     * Recieves a BufferedImage, and the name, the it stretches it to the maximum possible area and sets it to x=0 , y=0
     * @param image
     * @param id
     */
    public UIBackground(BufferedImage image, String id) {
        super(0, 0, 800*2, 608*2, id);
        this.image = image;
    }

    /**
     * The tick initializes once the state is called, the main difference with the constructor is that the constructor
     * runs when the program itself starts but the tick each time the state is called
     */
    @Override
    public void tick() {
    }

    /**
     * Render method runs constantly in a loop once the game is started and finishes the moment the game also finishes,
     * this method also is the method in charge of rendering the graphics
     * @param g graphics parameter passed to GameLoop
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, 800*2, 608*2, null);
    }

    /**
     * Is the mouse manager that is included in the case that the BufferedImage is going to be affected by the mouse.
     */
    @Override
    public void onClick() {
    }
}
