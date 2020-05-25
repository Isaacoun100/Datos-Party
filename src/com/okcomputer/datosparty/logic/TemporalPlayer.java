package com.okcomputer.datosparty.logic;

public class TemporalPlayer {

    private final int id;
    private int diceValue;

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public TemporalPlayer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
