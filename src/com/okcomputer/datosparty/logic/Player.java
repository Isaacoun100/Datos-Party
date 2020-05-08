package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.*;
import com.okcomputer.datosparty.random.Dice;

public class Player {

    private SinglyNode<Box> position;
    private String name;
    private int turn;

    public Player(SinglyNode<Box> position, String name) {
        this.position = position;
        this.name = name;
    }

    public void move() {
        int movement = Dice.NumberSpace();
        while (movement > 0) {
            this.position.getData().setPlayerNull();
            this.position.getNext().getData().setOnBoard(this);
            this.position = (SinglyNode<Box>) this.position.getNext();
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
        this.turn = Dice.NumberSpace();
    }
}
