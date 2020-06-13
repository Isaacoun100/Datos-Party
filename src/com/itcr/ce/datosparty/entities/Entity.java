package com.itcr.ce.datosparty.entities;

import java.awt.*;

/**
 * The abstract entity class defines objects that have a position on board, and that have an interaction of some sort
 * we use this for players and boxes because of this
 */
public abstract class Entity {
    protected float x,y;
    protected int width, height;

    /**
     *
     * @param x int position on screen
     * @param y int position on screen
     * @param width of the object
     * @param height of the object
     */
    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * a Method that uses the java.awt Graphics class, to render a visual representation of objects, defined in each object
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     * getter for all entities
     * @return int position of the x value
     */
    public float getX() {
        return x;
    }

    /**
     * setter for all entities
     * @param x position of the entities on the screen
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     *  getter for all entities
     * @return int value of the y position
     */
    public float getY() {
        return y;
    }

    /**
     * setter for all entities
     * @param y position of the entities on the screen
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * getter for all entities
     * @return int value of the width of a specific entity
     */
    public int getWidth() {
        return width;
    }

    /**
     * setter for all entities
     * @param width int value that determines the width of a specific entity
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getter for all entities
     * @return int value of the height of a specific entity
     */
    public int getHeight() {
        return height;
    }

    /**
     * setter for all entities
     * @param height int value that determines the height of a specific entity
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
