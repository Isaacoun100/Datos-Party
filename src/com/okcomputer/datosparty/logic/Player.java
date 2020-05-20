package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.DoublyNode;
import com.okcomputer.datosparty.dataStructures.SinglyNode;
import com.okcomputer.datosparty.logic.boxes.Box;

public class Player{

    private final String name;
    private int movement;
    private int coins;
    private int stars;
    private DoublyNode<Box> position;

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

    public DoublyNode<Box> getPosition() {
        return position;
    }

    public void setPosition(DoublyNode<Box> position) {
        this.position = position;
    }
}
