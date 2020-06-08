package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Turn {

    private static SinglyNode<Player> playersTurn;

    public static void nextPlayer(int movement) {
        if(movement == 0){
            playersTurn = (SinglyNode<Player>) playersTurn.getNext();
        }
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

    public static void playTurn(Game game) throws InterruptedException {
        Player currentPlayer = Turn.getPlayersTurn().getData();
        game.setCurrentEvent(null);
        while (Turn.getPlayersTurn() != null) {
            System.out.println(currentPlayer.getName());
            game.pauseGame();
            try {
                Turn.movePlayer(game);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Turn.nextPlayer(currentPlayer.getMovement());
        }
    }
}
