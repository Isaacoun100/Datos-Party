package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.random.Dice;

public class Player {

    private String name;
    private int movement;

    public Player(String name) {
        this.name = name;
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
}
