package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected String id;
    protected Rectangle bounds;
    protected boolean hovering = false;

    public UIObject(float x, float y, int width, int height, String id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        bounds = new Rectangle((int) x, (int) y, width, height);

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick() throws InterruptedException;

    public void onMouseMove(MouseEvent e){

        hovering = bounds.contains(e.getX(), e.getY());

    }

    public void onMouseRelease(MouseEvent e) throws InterruptedException {
        if(hovering)
            onClick();
    }

    /**
     * Getters and setters
     */

    public String getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
