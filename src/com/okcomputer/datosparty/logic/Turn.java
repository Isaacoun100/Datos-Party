package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void nextPlayer() {
        if (playersTurn.getNext() == null) {
            // Minigame
            playersTurn =  Round.getPlayerOrder().getHead();
        } else {
            playersTurn = playersTurn.getNext();
        }
    }

    public static void rollDice() {
        playersTurn.getData().setMovement();
        int boxesLeft =  playersTurn.getData().getMovement();
        while (boxesLeft > 0) {
            playersTurn.getData().setPosition(playersTurn.getData().getPosition().getNext());
            //Check star
            boxesLeft--;
        }
    }


}
