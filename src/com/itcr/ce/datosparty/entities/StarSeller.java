package com.itcr.ce.datosparty.entities;

import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class StarSeller extends Entity{

    public StarSeller(float x, float y) {
        super(x, y, 130, 130);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.starSeller,(int) x,(int) y, width, height, null);

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
