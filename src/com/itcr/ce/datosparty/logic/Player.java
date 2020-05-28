package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.entities.boxes.Box;

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
        this.movement = Dice.roll(6,1);
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
