package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void nextPlayer() {
        playersTurn = (SinglyNode<Player>) playersTurn.getNext();
    }

    public static SinglyNode<Player> getPlayersTurn() {
        return playersTurn;
    }

    public static void movePlayer(Game game) throws InterruptedException {
        playersTurn.getData().move(game);
    }

    public static void setPlayersTurn(SinglyNode<Player> playersTurn) {
        Turn.playersTurn = playersTurn;
    }

}
