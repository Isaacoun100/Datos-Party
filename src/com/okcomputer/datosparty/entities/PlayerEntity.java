package com.okcomputer.datosparty.entities;

import com.okcomputer.datosparty.GameLoop;

import java.awt.*;

public class PlayerEntity extends Entity{

    protected float xMove, yMove;
    private int coins;
    private int stars;

    public PlayerEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public void move(){
        x += xMove;
        y += yMove;
    }

}
