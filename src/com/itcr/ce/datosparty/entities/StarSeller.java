package com.itcr.ce.datosparty.entities;

import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class StarSeller extends Entity{

    /**
     * Constructor for the Star seller entity, this entity is simply a graphical representation
     * from the box from where the star can be purchased
     * @param x float value for X position
     * @param y float value for Y position
     */
    public StarSeller(float x, float y) {
        super(x, y, 130, 130);
    }

    /**
     * Render method for the seller, the assets are already defined
     * @param g java.awt Graphics obj
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.starSeller,(int) x,(int) y, width, height, null);

    }

    /**
     * Changes the position of the star seller
     * @param x float value for X position
     * @param y float value for Y position
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
