package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.*;

public class Player {

    public int movement = 3;
    private SinglyLinkedListNode<Box> position;

    public Player(SinglyLinkedListNode<Box> position) {
        this.position = position;
    }

    public void move() {

        while (movement > 0) {
            this.position.getData().setPlayerNull();
            this.position.getNext().getData().setOnBoard(this);
            this.position = this.position.getNext();
            movement--;
        }

        movement = 3;

    }

}
