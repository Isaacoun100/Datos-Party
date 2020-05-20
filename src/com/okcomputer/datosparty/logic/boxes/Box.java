package com.okcomputer.datosparty.logic.boxes;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.entities.Entity;
import com.okcomputer.datosparty.entities.Player;

public abstract class Box extends Entity {

    private SinglyList<Player> playerList = new SinglyList<>();


    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(SinglyList<Player> playerList) {
        this.playerList = playerList;
    }
}
