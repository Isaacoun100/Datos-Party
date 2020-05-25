package com.okcomputer.datosparty.logic;

public class TemporalPlayer {

    private final int id;
    private final int diceValue;

    public int getDiceValue() {
        return diceValue;
    }

    public int getId() {
        return id;
    }

    public TemporalPlayer(int id, int diceValue) {
        this.id = id;
        this.diceValue = diceValue;
    }
}
