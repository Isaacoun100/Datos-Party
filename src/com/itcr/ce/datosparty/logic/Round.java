package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.image.BufferedImage;

public class Round {

    private static SinglyList<Player> playerOrder;
    private static int maxRound;

    public static void initRound() {
        playerOrder = new SinglyList<>();
    }

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

        showList();

    }

    public static void showList(){

        SinglyNode<Player> showPlayer = playerOrder.getHead();

        while (showPlayer!=null){
            System.out.println(showPlayer.getData().getName());
            showPlayer = (SinglyNode<Player>) showPlayer.getNext();
        }

    }

    public static void addPlayer(String name, float x, float y, BufferedImage image){
        playerOrder.add(new Player(name,x,y,image));
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

    public static void playRound(Game game) throws InterruptedException {
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
            game.pauseGame();
            try {
                Turn.movePlayer(game);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //game.checkStar(currentPlayer);
            System.out.println("Dice: " + currentPlayer.getMovement());
            System.out.print("\n");
            Turn.nextPlayer();
        }
    }
}
