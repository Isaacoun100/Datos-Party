package com.itcr.ce.datosparty.logic;

/**
 * Temporal player is the class that will act as a node to the DefineOrder class with the temporalPlayerList,
 * this class has only two variables, the name, and the diceValue, this is a more primitive Player class.
 */
public class TemporalPlayer {

    private final String id;
    private int diceValue;

    /**
     * This method returns the dice value of an especific temporal player
     * @return the dice value of that player
     */
    public int getDiceValue() {
        return diceValue;
    }

    /**
     * This method returns the ID of an especific temporal player
     * @return the ID of that player
     */
    public String getId() {
        return id;
    }

    /**
     * Constructor for the class, this recieves the necessary to work: the id and the dice value
     * @param id The identifier of the player
     * @param diceValue The value that is going to be used to sort the temporal players
     */
    public TemporalPlayer(String id, int diceValue) {
        this.id = id;
        this.diceValue = diceValue;
    }

}
