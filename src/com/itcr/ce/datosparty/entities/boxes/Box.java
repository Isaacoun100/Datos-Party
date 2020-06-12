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
     * @param player that stands on the box at the end of its Turn
     * @param game played currently
     */
    public abstract void boxAction(Player player, Game game);

    public boolean isStarBox() {
        return starBox;
    }

    public String getBoxID() {
        return boxID;
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }

    public void setStarBox(boolean starBox) {
        this.starBox = starBox;
    }

    public boolean isCrossRoads() {
        return crossRoads;
    }

    public void setCrossRoads(boolean crossRoads) {
        this.crossRoads = crossRoads;
    }

}
