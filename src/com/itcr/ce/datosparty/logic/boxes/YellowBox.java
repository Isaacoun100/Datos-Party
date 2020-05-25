package com.itcr.ce.datosparty.logic.boxes;

import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class YellowBox extends Box {
    public YellowBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.yellowBox,(int) x,(int) y, width, height, null);
    }
}