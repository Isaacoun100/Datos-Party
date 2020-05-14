package com.okcomputer.datosparty.entities;

import com.okcomputer.datosparty.dataStructures.SinglyNode;
import com.okcomputer.datosparty.logic.Box;
import com.okcomputer.datosparty.logic.Dice;

public class Player extends Entity{

    private final String name;
    private int movement;
    private int coins;
    private int stars;

    public Player(String name, float x,float y,int width,int height) {
        super(x, y, width, height);
        this.name = name;
    }

    public void move (SinglyNode<Box> box) {
        //To move on board
    }

    public String getName() {
        return name;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement() {
        this.movement = Dice.roll();
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {

    }
}