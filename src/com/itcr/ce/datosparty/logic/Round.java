package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Contains a list with players ordered according to their turns
 */
public class Round {

    private static SinglyList<Player> playerOrder;
    private static int maxRound;

    /**
     * Initializes the playerOrder list
     */
    public static void initRound() {
        playerOrder = new SinglyList<>();
    }

    /**
     * Grabs a list of primitive players and gives the a name and an order
     * @param sortedList list of players without names or order
     */
    public static void translate(SinglyList<TemporalPlayer> sortedList){
        SinglyNode<TemporalPlayer> temp = sortedList.getHead();
        SinglyList<BufferedImage> imageList = new SinglyList<>();

        imageList.add(Assets.player1Static);
        imageList.add(Assets.player2Static);
        imageList.add(Assets.player3Static);
        imageList.add(Assets.player4Static);

        Node<BufferedImage> currentImage = imageList.getHead();
        float x = 110;
        float y = 570;
        while(temp!=null){
            addPlayer(temp.getData().getId(), x, y,currentImage.getData());
            temp = (SinglyNode<TemporalPlayer>) temp.getNext();
            x -=40;
            currentImage = currentImage.getNext();
        }
    }

    /**
     * Adds a player to the list in its respective order
     * @param name player's name
     * @param x x parameter of player
     * @param y y parameter of player
     * @param image image assigned to player
     */
    public static void addPlayer(String name, float x, float y, BufferedImage image){
        playerOrder.add(new Player(name,x,y,image));
    }

    /**
     * return the list with players ordered correctly
     * @return list of players
     */
    public static SinglyList<Player> getPlayerOrder() {
        return playerOrder;
    }

    /**
     * Return the maximum quantity of rounds that will be played on the game
     * @return max rounds on game
     */
    public static int getMaxRound() {
        return maxRound;
    }

    /**
     * Sets the maximum quantity of rounds that will be played on the game
     * @param maxRound max rounds on game
     */
    public static void setMaxRound(int maxRound) {
        Round.maxRound = maxRound;
    }

    /**
     * Plays the entire round, checking which player turn is next when the other ends. It also sets a star seller on
     * round 2
     * @param game game played currently
     */
    public static void playRound(Game game) {
        SinglyNode<Player> currentTurn = Round.getPlayerOrder().getHead();
        Player player1 = game.getPlayerList().get(0).getData();
        Player player2 = game.getPlayerList().get(1).getData();

        int numberOfPlayers = game.getNumberOfPlayers();
        int currentRound = game.getCurrentRound();
        if (currentRound == 2) {
            game.setStar();
        }

        try {
            Turn.setPlayersTurn(currentTurn);
            game.pauseGame();
            Turn.playTurn(game, player1);


            currentTurn = (SinglyNode<Player>) currentTurn.getNext();
            Turn.setPlayersTurn(currentTurn);
            game.pauseGame();
            Turn.playTurn(game, player2);

            if(numberOfPlayers >= 3){
                Player player3 = game.getPlayerList().get(2).getData();
                currentTurn = (SinglyNode<Player>) currentTurn.getNext();
                Turn.setPlayersTurn(currentTurn);
                game.pauseGame();
                Turn.playTurn(game, player3);
            }
            if(numberOfPlayers == 4){
                Player player4 = game.getPlayerList().get(3).getData();
                currentTurn = (SinglyNode<Player>) currentTurn.getNext();
                Turn.setPlayersTurn(currentTurn);
                game.pauseGame();
                Turn.playTurn(game, player4);
        }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
