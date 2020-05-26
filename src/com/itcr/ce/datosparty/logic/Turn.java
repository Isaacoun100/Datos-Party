package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyNode;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void rollDice() {
        playersTurn.getData().setMovement();
        int boxesLeft =  playersTurn.getData().getMovement();
        while (boxesLeft > 0) {
            // Goes to next Node<Box>
            playersTurn.getData().setPosition(playersTurn.getData().getPosition().getNext());
            // Checks if there is a star
            playersTurn.getData().getPosition().getData().buyStar(playersTurn.getData());
            // Subtracts from number given on dice
            boxesLeft--;
        }
    }

    public static void nextPlayer() {
        playersTurn = playersTurn.getNext();
    }

    public static SinglyNode<Player> getPlayersTurn() {
        return playersTurn;
    }

    public static void setPlayersTurn(SinglyNode<Player> playersTurn) {
        Turn.playersTurn = playersTurn;
    }
}
