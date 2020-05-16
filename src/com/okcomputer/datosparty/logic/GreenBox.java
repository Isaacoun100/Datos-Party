package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.gfx.Assets;

import java.awt.*;

public class GreenBox extends Box {
    public GreenBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.greenBox,(int) x,(int) y, width, height, null);

    }
}
