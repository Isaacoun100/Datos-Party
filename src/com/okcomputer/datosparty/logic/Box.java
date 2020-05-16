package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.entities.Entity;
import com.okcomputer.datosparty.entities.Player;

public abstract class Box extends Entity {

    public SinglyList<Player> playerList = new SinglyList<>();

    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


}
