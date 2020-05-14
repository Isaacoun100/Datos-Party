package com.okcomputer.datosparty.entities;

import com.okcomputer.datosparty.dataStructures.SinglyList;

public class BlueBox extends Box{

    private SinglyList<PlayerEntity> playerList;

    public BlueBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render() {

    }
}

