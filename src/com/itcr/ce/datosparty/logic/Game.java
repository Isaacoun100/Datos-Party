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

import static com.itcr.ce.datosparty.logic.Event.*;

public class Game extends Thread {

    Handler handler;
    int currentRound = 1;
    private boolean active;
    private final SinglyList<Player> playerList;
    private final int numberOfPlayers;
    public StarSeller starSeller = new StarSeller(-300,-300);
    int maxRound;
    public Stack<Event> eventStack = new Stack<>();
    private Event currentEvent;

    private final CircularList<Box> mainCircuit;
    private final SinglyList<Box> phaseA;
    private final SinglyList<Box> phaseB;
    private final DoublyList<Box> phaseC;
    private final CircularDoublyList<Box> phaseD;


    public Game(Handler handler, int numRound) {
        this.active = true;
        this.handler = handler;
        this.maxRound = numRound;
        Round.setMaxRound(maxRound);
        this.playerList = Round.getPlayerOrder();
        Turn.setPlayersTurn(playerList.getHead());
        this.numberOfPlayers = Round.getPlayerOrder().getLength();
        Player currentPlayer;
        SinglyNode<Box> startBox = handler.getBoard().getMainCircuit().get(0);

        this.mainCircuit =  handler.getBoard().getMainCircuit();
        this.phaseA = handler.getBoard().getPhaseA();
        this.phaseB = handler.getBoard().getPhaseB();
        this.phaseC = handler.getBoard().getPhaseC();
        this.phaseD = handler.getBoard().getPhaseD();

        for(int i = 0; i < Round.getPlayerOrder().getLength(); i++){
            currentPlayer = Round.getPlayerOrder().get(i).getData();
            currentPlayer.setPosition(startBox);
        }
        MiniGameBuilder.build(GameLoop.gameDependantStates,numberOfPlayers,handler, this);
        buildGameState(GameLoop.gameDependantStates,handler, this);
        buildEndGameState(GameLoop.gameDependantStates,handler,this);
        resetEvents();
        System.out.println("number of players: " + Round.getPlayerOrder().getLength());

    }

    @Override
    public void run() {
        while (active){
            Minigame.playMinigame(3);
            while(currentRound != Round.getMaxRound()){
                try {
                    Round.playRound(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Minigame.playMinigame(3);
                try {
                    pauseGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentRound++;
            }
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

    public int getMaxRound() {
        return maxRound;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
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