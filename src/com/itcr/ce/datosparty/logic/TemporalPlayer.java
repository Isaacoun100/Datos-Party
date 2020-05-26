package com.itcr.ce.datosparty.logic;

public class TemporalPlayer {

    private final String id;
    private int diceValue;

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

    public void setDiceValue(int diceValue){
        this.diceValue = diceValue;
    }

}
