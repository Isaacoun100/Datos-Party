package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyNode;
import com.okcomputer.datosparty.entities.Player;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void nextPlayer() {
        if (Round.getNumRound() == 0) {
            playersTurn = Round.getPlayerOrder().getHead();
            Round.setNumRound(1);
        } else if (playersTurn.getNext() == null) {
            //Minigame state
            //Minigame ends
            playersTurn = Round.getPlayerOrder().getHead();
            Round.setNumRound(Round.getNumRound() + 1);
        } else {
            playersTurn = playersTurn.getNext();
        }
    }
}
