package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.entities.Entity;
import com.itcr.ce.datosparty.entities.Player;

public abstract class Box extends Entity {

    private boolean starBox = false;

    public boolean isCrossRoads() {
        return crossRoads;
    }

    public void setCrossRoads(boolean crossRoads) {
        this.crossRoads = crossRoads;
    }

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

    public void setStarBox(boolean starBox) {
        this.starBox = starBox;
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }


    public abstract void boxAction(Player player);

}
