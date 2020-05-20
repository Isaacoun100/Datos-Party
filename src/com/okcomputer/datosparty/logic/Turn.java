package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyNode;

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
        if (playersTurn.getNext() == null) {
            // Minigame
            playersTurn =  Round.getPlayerOrder().getHead();
        } else {
            playersTurn = playersTurn.getNext();
        }
    }
}
