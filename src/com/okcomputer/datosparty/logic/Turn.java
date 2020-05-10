package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void nextPlayer() {
        if (Round.getNumRound() == 0) {
            playersTurn = Round.getPlayerOrder().getHead();
        } else if (playersTurn.getNext() == null) {
            //Minigame state
            playersTurn = Round.getPlayerOrder().getHead();
            Round.setNumRound(Round.getNumRound() + 1);
        }
    }

}
