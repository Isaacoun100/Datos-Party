package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Round {

    private static SinglyList<Player> playerOrder;
    private static int maxRound;

    public static void initRound() {
        playerOrder = new SinglyList<>();
    }

    public static void translate(SinglyList<TemporalPlayer> sortedList){
        SinglyNode<TemporalPlayer> temp = sortedList.getHead();

        while(temp!=null){
            addPlayer(temp.getData().getId());
            temp=temp.getNext();
        }

        showList();

    }

    public static void showList(){

        SinglyNode<Player> showPlayer = playerOrder.getHead();

        while (showPlayer!=null){
            System.out.println(showPlayer.getData().getName());
            showPlayer=showPlayer.getNext();
        }

    }

    public static void addPlayer(String name){
        playerOrder.add(new Player(name,0,0));
    }

    public static SinglyList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public static int getMaxRound() {
        return maxRound;
    }

    public static void setMaxRound(int maxRound) {
        Round.maxRound = maxRound;
    }

    public static void playRound(Game game) {
        Turn.setPlayersTurn(Round.getPlayerOrder().getHead());
        Player currentPlayer;
        if (game.getCurrentRound() == 2) {
            System.out.println("Estrella");
            game.setStar();
        }
        while (Turn.getPlayersTurn() != null) {
            //Player
            currentPlayer = Turn.getPlayersTurn().getData();
            System.out.println(currentPlayer.getName());
            Turn.rollDice();
            try {
                Turn.movePlayer(game);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                game.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.checkStar(currentPlayer);
            System.out.println("Dice: " + currentPlayer.getMovement());
            System.out.print("\n");
            Turn.nextPlayer();
        }
    }
}
