package com.okcomputer.datosparty.entities;

import com.okcomputer.datosparty.GameLoop;

public class PlayerEntity extends Entity{

    protected float xMove, yMove;

    public PlayerEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {

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
