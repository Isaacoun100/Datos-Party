package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.Node;
import com.okcomputer.datosparty.entities.Player;

public class Box {

    private Player onBoard;
    private Node<Box> location;

    public void boxNode(Node<Box> location) {
        this.location = location;
    }

    public void setPlayerNull(){
        this.onBoard = null;
    }

    public Player getOnBoard() {
        return onBoard;
    }

    public void setOnBoard(Player onBoard) {
        this.onBoard = onBoard;
    }

}
