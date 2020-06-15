package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * User Interface Object, this is used to create dynamic objects that are easy to render a declare
 * other objects extends this class and its methods
 */
public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected String id;
    protected Rectangle bounds;
    protected boolean hovering = false;

    /**
     * Constructor for the object
     * @param x int position for the x Axis
     * @param y int position for the y Axis
     * @param width int related to the images width
     * @param height int related to the images height
     * @param id String used to render specific objects by calling their id
     */
    public UIObject(float x, float y, int width, int height, String id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        bounds = new Rectangle((int) x, (int) y, width, height);

    }

    /**
     * tick method for all objects
     */
    public abstract void tick();

    /**
     * render method for all objects
     * @param g java.awt graphics object
     */
    public abstract void render(Graphics g);

    /**
     * abstract method to be defined by classes that extended, if specific interaction is desired when clicked
     * @throws InterruptedException this is needed since some objects call a wait or sleep method
     */
    public abstract void onClick() throws InterruptedException;

    /**
     * method that checks the position of the mouse, used to determine weather or not the pointer is hovering on the class
     * @param e Mouse event
     */
    public void onMouseMove(MouseEvent e){
        hovering = bounds.contains(e.getX(), e.getY());
    }

    /**
     * method that checks that the mouse is clicked only once
     * @param e Mouse event
     * @throws InterruptedException this is needed because some objects call a wait or sleep method
     */
    public void onMouseRelease(MouseEvent e) throws InterruptedException {
        if(hovering)
            onClick();
    }

    /**
     * getter for the ID String
     */
    public String getId() {
        return id;
    }

    /**
     * getter for the X value
     * @return int corresponding the the x axis of the object
     */
    public float getX() {
        return x;
    }

    /**
     * setter for the X value
     * @param x int corresponding the to x axis of the object
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * getter for the Y value
     * @return int corresponding the the y axis of the object
     */
    public float getY() {
        return y;
    }

    /**
     * setter for the Y value
     * @param y int corresponding the to y axis of the object
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * getter for the width of the image
     * @return int value corresponding to the width of the image in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * setter for the width of the image
     * @param width int value corresponding to the width of the image in pixels
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getter for the height of the image
     * @return int value corresponding to the height of the image in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * setter for the height of the image
     * @param height int value corresponding to the height of the image in pixels
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
