package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.entities.PlayerEntity;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.logic.Box;

import java.awt.*;

public class BlueBox extends Box {

    private SinglyList<PlayerEntity> playerList = new SinglyList<>();

    public BlueBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blueBox,(int) x,(int) y, width, height, null);
    }
}

