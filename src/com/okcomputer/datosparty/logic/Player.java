package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.*;
import com.okcomputer.datosparty.random.Dice;

public class Player {

    private SinglyLinkedListNode<Box> position;

    public Player(SinglyLinkedListNode<Box> position) {
        this.position = position;
    }

    public void move() {
        int movement = Dice.NumberSpace();
        while (movement > 0) {
            this.position.getData().setPlayerNull();
            this.position.getNext().getData().setOnBoard(this);
            this.position = this.position.getNext();
            movement--;
        }
    }

}
