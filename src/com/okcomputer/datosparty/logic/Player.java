package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.*;
import com.okcomputer.datosparty.random.Dice;

public class Player {

    private SinglyLinkedListNode<Box> position;
    private String name;
    private int turn;

    public Player(SinglyLinkedListNode<Box> position, String name) {
        this.position = position;
        this.name = name;
    }

    public void move() {
        int movement = Dice.roll();
        while (movement > 0) {
            this.position.getData().setPlayerNull();
            this.position.getNext().getData().setOnBoard(this);
            this.position = this.position.getNext();
            movement--;
        }
    }

    public String getName() {
        return name;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn() {
        this.turn = Dice.roll();
    }
}
