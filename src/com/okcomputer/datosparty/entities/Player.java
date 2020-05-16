package com.okcomputer.datosparty.entities;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.dataStructures.SinglyNode;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.logic.Box;

import java.awt.*;

public class Player extends Entity{

    protected float xMove, yMove;
    private int coins;
    private int stars;
    private int movement;
    private final Handler handler;

    public Player(Handler handler) {
        super(0, 0, 100, 200);
        this.handler = handler;
    }

    @Override
    public void tick() {
        //x = handler.getGameLoop().board.getPhaseA().getHead().getData().y
        //y = handler.getGameLoop().board.getPhaseA().getHead().getData().y;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.playerStatic,(int) x,(int) y, width, height, null);
    }

    public void move(int movement) {
//        while(movement > 0){
//            handler.getGameLoop().board.getPhaseA().
//        }
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
