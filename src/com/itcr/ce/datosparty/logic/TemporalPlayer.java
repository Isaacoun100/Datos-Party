package com.itcr.ce.datosparty.logic;

public class TemporalPlayer {

    private final String id;
    private final int diceValue;

    public int getDiceValue() {
        return diceValue;
    }

    public String getId() {
        return id;
    }

    public TemporalPlayer(String id, int diceValue) {
        this.id = id;
        this.diceValue = diceValue;
    }
}
