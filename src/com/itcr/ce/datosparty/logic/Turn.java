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

    public static void playTurn(Game game, Player currentPlayer) throws InterruptedException {
        currentPlayer.setCurrentTurn(true);
        currentPlayer.setBoxAction(true);
        currentPlayer.setThrowDice(true);
        while (Turn.getPlayersTurn() != null) {
            boolean activeTurn = currentPlayer.getCurrentTurn();
            while (activeTurn) {
                System.out.println(currentPlayer.getName());
                Turn.movePlayer(game);
                game.pauseGame();
                activeTurn = currentPlayer.getCurrentTurn();
                System.out.println(activeTurn);
            }
            nextPlayer();
        }
    }
}
