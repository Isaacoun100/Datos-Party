package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.entities.Entity;
import com.itcr.ce.datosparty.entities.Player;

public abstract class Box extends Entity {

    private SinglyList<Player> playerList = new SinglyList<>();
    private boolean starBox = false;

    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public boolean isStarBox() {
        return starBox;
    }

    public void setStarBox(boolean starBox) {
        this.starBox = starBox;
    }

    public abstract void boxAction(Player player);

}
