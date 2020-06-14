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

    private Player duelPlayer1, duelPlayer2;

    /**
     * Constructor for the game, it takes the handler and then passes it to others,
     * it also takes the number of rounds that the game will be played for
     * @param handler handler obj, its used to retrieve the mouse manager and key manager, and also the different sections of the board
     * @param numRound int of the number of rounds the game will be played
     */
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

    /**
     * run method for the game, when called it will execute the different parts of the game in a logical order
     */
    @Override
    public void run() {
        while (active){
            while(currentRound != Round.getMaxRound()+1){
                try {
                    Round.playRound(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Minigame.playMiniGame(5);
                try {
                    pauseGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentRound++;
            }
        }
    }

    /**
     * This method builds the gameState with parameters from the game
     * @param gameState a list where the state will be placed into
     * @param handler the handler obj so that it can pull resources
     * @param game the game obj, so that it can have access to its parameters
     */
    private void buildGameState(SinglyList<com.itcr.ce.datosparty.states.State> gameState, Handler handler, Game game) {
        gameState.add(new GameState(handler, game));
    }

    /**
     * This method builds the endGameState with parameters from the game
     * @param endGameState a list where the state will be placed into
     * @param handler the handler obj so that it can pull resources
     * @param game the game obj, so that it can have access to its parameters
     */
    private void buildEndGameState(SinglyList<com.itcr.ce.datosparty.states.State> endGameState, Handler handler, Game game) {
        endGameState.add(new EndGameState(handler, game));
    }

    /**
     * This method is used in the move method by the player,
     * it checks if a box is currently a "star box" meaning that a star can be purchased there
     * @param player player obj, currently moving trough the boxes
     * @throws InterruptedException exception needed since the player both stops this game thread,
     * and uses the sleep method to slow down the stepping animation
     */
    public void checkStar(Player player) throws InterruptedException {
        Box playerBox = player.getPosition().getData();
        if (playerBox.isStarBox()) {
            System.out.println("Star Box!!!");
            this.pauseGame();
            //buyStar(player);
        }
    }

    /**
     * this method sets the star in a random phase, excepting D
     */
    public void setStar(){
        switch (Dice.roll(1, 4)) {
            case 1 -> placeStar(mainCircuit);
            case 2 -> placeStar(phaseA);
            case 3 -> placeStar(phaseB);
            case 4 -> placeStar(phaseC);
        }
    }

    /**
     * This method places a star in a random box on the list that its currently in, using the list's length
     * it excludes connector boxes, since movement trough both boxes will stop the game, and to prevent crashes
     * @param list the list where the star will be placed.
     */
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

    /**
     * Method used by the player to buy a star if the player is on the corresponding box
     * @param player the player that will be able to purchase a star
     */
    public void buyStar(Player player) {
        if (player.getCoins() >= 10) {
            player.addCoins(-10);
            player.addStars(1);
            player.getPosition().getData().setStarBox(false);
            setStar();
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
        while (this.eventStack.peek() != null) {
            this.eventStack.pop();
        }
        while (tempList.getHead() != null) {
            randomIndex = Dice.roll(0, tempList.getLength());
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

    /**
     * getter for the current round
     * @return int value of the current round
     */
    public int getCurrentRound() {
    return currentRound;
    }

    /**
     * method used to pause the thread the game is being run in
     * @throws InterruptedException since it works stopping threads
     */
    public synchronized void pauseGame() throws InterruptedException {
        this.wait();
    }

    /**
     * method used to notify this thread and resume it
     */
    public synchronized void resumeGame() {
        this.notify();
    }

    /**
     * getter for tha max number of rounds for the current game
     * @return int corresponding to the max number of rounds
     */
    public int getMaxRound() {
        return maxRound;
    }

    /**
     * this passes the handler obj to other classes that need it
     * @return handler obj
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * The active boolean is related to the thread, once its set to false,
     * the thread will stop, this method is only used in the end game stage, to stop the game thread, so that a new one can begin
     * @param active boolean value
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the active list of players in order
     * @return list of players
     */
    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * getter for the number of players, returns an int corresponding to the number of players a game
     * @return int corresponding to players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Returns the current event
     * @return Event Obj
     */
    public Event getCurrentEvent() {
        return currentEvent;
    }

    /**
     * sets the current event
     * @param currentEvent returns an event obj from the event stack
     */
    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    /**
     * getter for the main circuit
     * @return returns the main circuit list
     */
    public CircularList<Box> getMainCircuit() {
        return mainCircuit;
    }

    /**
     * getter for the phase a
     * @return returns the phase a list
     */
    public SinglyList<Box> getPhaseA() {
        return phaseA;
    }


    /**
     * getter for the phase b
     * @return returns the phase b list
     */
    public SinglyList<Box> getPhaseB() {
        return phaseB;
    }

    /**
     * getter for the phase c
     * @return returns the phase c list
     */
    public DoublyList<Box> getPhaseC() {
        return phaseC;
    }

    /**
     * getter for the phase d
     * @return returns the phase d list
     */
    public CircularDoublyList<Box> getPhaseD() {
        return phaseD;
    }

    /**
     * This parameter is used by the duel event to set the data of the dueling players
     * @param player1 sets the first player of the duel
     * @param player2 sets the second player of the duel
     */
    public void updateDuelPlayers(Player player1, Player player2){
        this.duelPlayer1 = player1;
        this.duelPlayer2 = player2;
    }

    /**
     * this returns the first duel player
     * @return Player obj
     */
    public Player getDuelPlayer1() {
        return duelPlayer1;
    }

    /**
     * this returns the second duel player
     * @return Player obj
     */
    public Player getDuelPlayer2() {
        return duelPlayer2;
    }

}