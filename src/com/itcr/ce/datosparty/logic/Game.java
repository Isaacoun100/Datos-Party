package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.*;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.StarSeller;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.minigames.MiniGameBuilder;
import com.itcr.ce.datosparty.minigames.miniLogic.Minigame;
import com.itcr.ce.datosparty.states.EndGameState;
import com.itcr.ce.datosparty.states.GameState;

import java.util.Random;

public class Game extends Thread {

    Handler handler;
    int currentRound = 1;

    public int getMaxRound() {
        return maxRound;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    int maxRound;

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active;
    private final SinglyList<Player> playerList;
    public StarSeller starSeller = new StarSeller(-300,-300);

    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public Game(Handler handler, int numRound) {
        active = true;
        this.handler = handler;
        maxRound = numRound;
        Round.setMaxRound(maxRound);
        playerList = Round.getPlayerOrder();
        Turn.setPlayersTurn(playerList.getHead());
        int numberOfPlayers = Round.getPlayerOrder().getLength();
        Player currentPlayer;
        SinglyNode<Box> startBox = handler.getBoard().getMainCircuit().get(0);

        for(int i = 0; i < Round.getPlayerOrder().getLength(); i++){
            currentPlayer = Round.getPlayerOrder().get(i).getData();
            currentPlayer.setPosition(startBox);
        }
        MiniGameBuilder.build(GameLoop.gameDependantStates,numberOfPlayers,handler, this);
        buildGameState(GameLoop.gameDependantStates,handler, this);
        buildEndGameState(GameLoop.gameDependantStates,handler,this);
        System.out.println("number of players: "+Round.getPlayerOrder().getLength());

    }

    @Override
    public void run() {
        while (active){
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
    }

    private void buildGameState(SinglyList<com.itcr.ce.datosparty.states.State> gameState, Handler handler, Game game) {
        gameState.add(new GameState(handler, game));

    }

    private void buildEndGameState(SinglyList<com.itcr.ce.datosparty.states.State> endGameState, Handler handler, Game game) {
        endGameState.add(new EndGameState(handler, game));
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
        Box phaseA = handler.getBoard().getMainCircuit().get(11).getData();
        Box phaseC1 = handler.getBoard().getMainCircuit().get(15).getData();
        Box phaseB = handler.getBoard().getMainCircuit().get(12).getData();
        Box phaseC2 = handler.getBoard().getMainCircuit().get(33).getData();

        if( starBox == phaseA||starBox == phaseB || starBox==phaseC1 || starBox==phaseC2){
            placeStar(list);
        }
        else {
            starSeller.setPosition(starBox.getX()+1, starBox.getY() - 35);
            starBox.setStarBox(true);
        }
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


    public synchronized void pauseGame() throws InterruptedException {
        this.wait();
    }

    public synchronized void resumeGame() {
        this.notify();
    }
}