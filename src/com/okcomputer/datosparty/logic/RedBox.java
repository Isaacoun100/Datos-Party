package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.logic.Box;

import java.awt.*;

public class RedBox extends Box {
    public RedBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.redBox,(int) x,(int) y, width, height, null);

    }

}
