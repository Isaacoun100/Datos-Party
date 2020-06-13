package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.entities.Entity;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

/**
 * Board tiles connected with various LinkedLists
 */
public abstract class Box extends Entity {

    private boolean starBox = false;
    private boolean crossRoads = false;
    private String boxID = "";

    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Action that each of the boxes wil perform
     * @param player Current Player standing on box when its turn ends
     * @param game Object with all the data contained on current game
     */
    public abstract void boxAction(Player player, Game game);

    /**
     * Returns a boolean depending on whether or not the star is placed o this specific box
     * @return boolean, normally false
     */
    public boolean isStarBox() {
        return starBox;
    }

    /**
     * returns the boxID, this id is used to determine which boxes are "connectors"
     * @return String that marks the box as a connector
     */
    public String getBoxID() {
        return boxID;
    }

    /**
     * This method is only called once when the board is built, it marks specific boxes as one of the 4 available connectors
     * @param boxID a String one of 4 different ones
     */
    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }

    /**
     * method used to mark a box as a star box
     * @param starBox sets the boolean to true
     */
    public void setStarBox(boolean starBox) {
        this.starBox = starBox;
    }

    /**
     * method used for all boxes to determine if they are a connector of any kind, this triggers another
     * method that checks which kind of connector they are
     * @return a boolean for each box
     */
    public boolean isCrossRoads() {
        return crossRoads;
    }


    /**
     * method used to determine if a specific box is a crossRoads box
     * @param crossRoads boolean that is set up after the board is built, but before the game is played
     */
    public void setCrossRoads(boolean crossRoads) {
        this.crossRoads = crossRoads;
    }

}
