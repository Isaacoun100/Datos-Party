package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

/**
 * Contains a node that indicates who is playing currently
 */
public class Turn {

    private static SinglyNode<Player> playersTurn;

    /**
     * Sets the turn to the next player
     */
    public static void nextPlayer() {
        playersTurn = (SinglyNode<Player>) playersTurn.getNext();
    }

    /**
     * Gets the player playing currently
     * @return player currently playing
     */
    public static SinglyNode<Player> getPlayersTurn() {
        return playersTurn;
    }

    /**
     * Moves player form box to box
     * @param game game played currently
     */
    public static void movePlayer(Game game) {
        try {
            playersTurn.getData().move(game);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Sets the turn to the player currently playing
     * @param playersTurn player currently playing
     */
    public static void setPlayersTurn(SinglyNode<Player> playersTurn) {
        Turn.playersTurn = playersTurn;
    }

    /**
     * Combines methods to execute the turn correctly
     * @param game game currently playing
     * @param currentPlayer player currently playing
     */
    public static void playTurn(Game game, Player currentPlayer) {
        try {
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
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
