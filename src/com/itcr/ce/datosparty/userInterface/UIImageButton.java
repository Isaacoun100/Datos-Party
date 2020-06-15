package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class is an interactive UI object, that can behave in different ways when clicked
 */
public class UIImageButton extends UIObject{

    private final BufferedImage[] images;
    private final ClickListener clicker;

    /**
     *
     * @param x position x on the screen multiplied by 16
     * @param y position y on the screen multiplied by 16
     * @param width of the image multiplied by bitmap
     * @param height of the image multiplied by bitmap
     * @param images this uses an Array List from java, remember to change it for one of the data structures Gabo created
     * @param clicker clicker interface to define what the button does when clicked
     */
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, String id, ClickListener clicker) {
        super(x*16, y*16, width*16, height*16, id);
        this.images = images;
        this.clicker = clicker;
    }

    /**
     * unUsed extended class from UI object
     */
    @Override
    public void tick() {
    }

    /**
     * this render method shows the button on screen
     * @param g java.awt graphics object
     */
    @Override
    public void render(Graphics g) {
        if(hovering)
            g.drawImage(images[1], (int)x, (int)y, width, height, null);
        else
            g.drawImage(images[0], (int)x, (int)y, width, height, null);
    }

    /**
     * this method returns the value specified using the interface, which can be defined using a lambda or initializing a new clicker object
     * @throws InterruptedException
     */
    @Override
    public void onClick() throws InterruptedException {
        clicker.onClick();
    }
}
