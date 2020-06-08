package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.entities.Entity;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

public abstract class Box extends Entity {

    private boolean starBox = false;
    private boolean crossRoads = false;
    private String boxID = "";

    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

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

    public abstract void boxAction(Player player, Game game);

}
