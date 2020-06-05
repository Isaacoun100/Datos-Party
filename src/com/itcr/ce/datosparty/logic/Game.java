package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.CircularDoublyList;
import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.minigames.MiniGameBuilder;
import com.itcr.ce.datosparty.minigames.minilogic.Minigame;

import java.util.Random;

public class Game extends Thread {

    Handler handler;
    int currentRound = 1;
    private final SinglyList<Player> playerList;

    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public Game(Handler handler, int numRound) {
        this.handler = handler;
        Round.setMaxRound(numRound);
        playerList = Round.getPlayerOrder();
        Turn.setPlayersTurn(playerList.getHead());
        int numberOfPlayers = Round.getPlayerOrder().getLength();
        Player currentPlayer;
        SinglyNode<Box> startBox = handler.getBoard().getMainCircuit().get(0);
        for(int i = 0; i < Round.getPlayerOrder().getLength(); i++){

            currentPlayer = Round.getPlayerOrder().get(i).getData();
            currentPlayer.setPosition(startBox);

        }
        MiniGameBuilder.build(GameLoop.miniGameStates,numberOfPlayers,handler, this);
        System.out.println("number of players: "+Round.getPlayerOrder().getLength());

    }

    @Override
    public void run() {
        while(currentRound != Round.getMaxRound()){
            Round.playRound(this);
            Minigame.playMinigame(1);
            try {
                pauseGame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentRound++;
        }
    }

    void checkStar(Player player) {
        Box playerBox = player.getPosition().getData();
        if (playerBox.isStarBox()) {
            buyStar(player);
        }
    }

    public void setStar(){
        Random numRandom = new Random();
        CircularList<Box> mainCircuit =  handler.getBoard().getMainCircuit();
        int starIndex = numRandom.nextInt(mainCircuit.getLength());
        Box starBox = mainCircuit.get(starIndex).getData();
        starBox.setStarBox(true);
        
    }

    public void buyStar(Player player) {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to buy a star?");
        // Choose yes/no
        System.out.println("Are you sure? It's 10 coins.");
        //Choose yes/no
        if (player.getCoins() >= 10) {
            player.addCoins(-10);
            player.setStars(1);
            player.getPosition().getData().setStarBox(false);
            setStar();
        } else {
            System.out.println("You don't have enough money, though...");
        }
    }

    public int getCurrentRound() {
    return currentRound;
    }

    public synchronized void pauseGame() throws InterruptedException {
        this.wait();
    }

    public synchronized void resumeGame() {
        this.notify();
    }
}