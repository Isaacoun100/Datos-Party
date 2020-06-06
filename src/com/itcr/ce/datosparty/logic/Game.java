package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.*;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.StarSeller;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.minigames.MiniGameBuilder;
import com.itcr.ce.datosparty.minigames.minilogic.Minigame;
import com.itcr.ce.datosparty.minigames.states.FirstMinigameState;
import com.itcr.ce.datosparty.states.GameState;
import com.itcr.ce.datosparty.states.State;

import java.util.Random;

public class Game extends Thread {

    Handler handler;
    int currentRound = 1;
    private final SinglyList<Player> playerList;
    public StarSeller starSeller = new StarSeller(-300,-300);

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
        buildGameState(GameLoop.gameState,handler, this);
        System.out.println("number of players: "+Round.getPlayerOrder().getLength());

    }

    private void buildGameState(SinglyList<com.itcr.ce.datosparty.states.State> gameState, Handler handler, Game game) {
        gameState.add(new GameState(handler, game));

    }

    @Override
    public void run() {
        while(currentRound != Round.getMaxRound()){
            try {
                Round.playRound(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Minigame.playMinigame(1);
            try {
                pauseGame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentRound++;
        }
        System.out.println("C'est fini");
        Leaderboard.getLeaderboard();
    }

    public void checkStar(Player player) throws InterruptedException {
        Box playerBox = player.getPosition().getData();
        if (playerBox.isStarBox()) {
            System.out.println("Star Box!!!");
            this.pauseGame();
            //buyStar(player);
        }
    }

    public void setStar(){
        CircularList<Box> mainCircuit =  handler.getBoard().getMainCircuit();
        SinglyList<Box> phaseA = handler.getBoard().getPhaseA();
        SinglyList<Box> phaseB = handler.getBoard().getPhaseB();
        DoublyList<Box> phaseC = handler.getBoard().getPhaseC();
        switch (Dice.roll(4, 1)) {
            case 1 -> placeStar(mainCircuit);
            case 2 -> placeStar(phaseA);
            case 3 -> placeStar(phaseB);
            case 4 -> placeStar(phaseC);
        }
    }

    private void placeStar(LinkedList<Box> list){
        Random numRandom = new Random();
        int starIndex = numRandom.nextInt(list.getLength());
        Box starBox = list.get(starIndex).getData();
        starSeller.setPosition(starBox.getX()+3,starBox.getY()- 45);
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