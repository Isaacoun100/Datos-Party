package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyLinkedListNode;

public class Box {

    private Player onBoard;
    private SinglyLinkedListNode<Box> location;

    public void boxNode(SinglyLinkedListNode<Box> location) {
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
