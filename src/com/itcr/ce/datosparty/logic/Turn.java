package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void rollDice() {
        playersTurn.getData().setMovement();
    }

    public static void nextPlayer() {
        playersTurn = playersTurn.getNext();
    }

    public static SinglyNode<Player> getPlayersTurn() {
        return playersTurn;
    }

    public static void movePlayer(){
        playersTurn.getData().move();
    }

    public static void setPlayersTurn(SinglyNode<Player> playersTurn) {
        Turn.playersTurn = playersTurn;
    }

}
