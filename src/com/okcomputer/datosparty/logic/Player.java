package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class Player{

    private final String name;
    private int movement;
    private int coins;
    private int stars;

    public Player(String name) {
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
}
