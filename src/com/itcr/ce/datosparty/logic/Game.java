package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.*;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.StarSeller;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.minigames.MiniGameBuilder;
import com.itcr.ce.datosparty.minigames.minilogic.Minigame;
import com.itcr.ce.datosparty.states.GameState;

import java.util.Random;

import static com.itcr.ce.datosparty.logic.Event.*;

public class Game extends Thread {

    Handler handler;
    int currentRound = 0;
    private final SinglyList<Player> playerList;
    public StarSeller starSeller = new StarSeller(-300,-300);
    public Stack<Event> eventStack = new Stack<>();

    private final CircularList<Box> mainCircuit;
    private final SinglyList<Box> phaseA;
    private final SinglyList<Box> phaseB;
    private final DoublyList<Box> phaseC;
    private final CircularDoublyList<Box> phaseD;


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
        this.mainCircuit =  handler.getBoard().getMainCircuit();
        this.phaseA = handler.getBoard().getPhaseA();
        this.phaseB = handler.getBoard().getPhaseB();
        this.phaseC = handler.getBoard().getPhaseC();
        this.phaseD = handler.getBoard().getPhaseD();
        resetEvents();
        System.out.println("number of players: " + Round.getPlayerOrder().getLength());

    }

    private void buildGameState(SinglyList<com.itcr.ce.datosparty.states.State> gameState, Handler handler, Game game) {
        gameState.add(new GameState(handler, game));

    }

    @Override
    public void run() {
        while(currentRound != Round.getMaxRound()){
            currentRound++;
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
        switch (Dice.roll(1, 4)) {
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
        starSeller.setPosition(starBox.getX() + 3, starBox.getY() - 45);
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
            player.addStars(1);
            player.getPosition().getData().setStarBox(false);
            setStar();
        } else {
            System.out.println("You don't have enough money, though...");
        }
    }

    public void resetEvents() {
        SinglyList<Event> tempList = new SinglyList<>();
        SinglyNode<Event> randomNode;
        int randomIndex;
        eventCloner(tempList, DUEL, 10);
        eventCloner(tempList, STEAL_COINS, 10);
        tempList.add(STEAL_COINS);
        tempList.add(GIFT_COINS);
        tempList.add(LOSE_STAR);
        eventCloner(tempList, WIN_2_STARS, 3);
        tempList.add(WIN_5_STARS);
        eventCloner(tempList, STEAL_STAR, 3);
        eventCloner(tempList, TELEPORT, 10);
        eventCloner(tempList, SWAP_PLAYERS, 5);
        if (this.eventStack.peek() != null) {
            this.eventStack.pop();
        }
        while (tempList.getHead() != null) {
            randomIndex = Dice.roll(0, tempList.getLength() - 1);
            randomNode = tempList.get(randomIndex);
            this.eventStack.push(randomNode.getData());
            tempList.remove(randomIndex);
        }
    }

    private void eventCloner(SinglyList<Event> tempList, Event event, int times) {
        while (times > 0) {
            tempList.add(event);
            times--;
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

    public CircularList<Box> getMainCircuit() {
        return mainCircuit;
    }

    public SinglyList<Box> getPhaseA() {
        return phaseA;
    }

    public SinglyList<Box> getPhaseB() {
        return phaseB;
    }

    public DoublyList<Box> getPhaseC() {
        return phaseC;
    }

    public CircularDoublyList<Box> getPhaseD() {
        return phaseD;
    }
}