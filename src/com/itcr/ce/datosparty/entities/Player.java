package com.itcr.ce.datosparty.entities;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.logic.Connector;
import com.itcr.ce.datosparty.logic.EventLogic;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static com.itcr.ce.datosparty.logic.Connector.*;

/**
 * Playable character that moves on board and stores coins and stars
 */
public class Player extends Entity {

    private final String name;

    private int movement = 0,
                coins = 0,
                stars = 0;

    private float x;
    private float y;
    BufferedImage image;
    private Boolean changeDirection;
    private Node<Box> position;
    private Boolean reversed = false;
    private Boolean currentTurn = false;
    private Boolean wonDuel = false;


    /**
     * Sets the variable won duel, to either true or false
     * @param wonDuel boolean that determines if a player should be swaped or not
     */
    public void setWonDuel(Boolean wonDuel) {
        this.wonDuel = wonDuel;
    }

    /**
     *
     * @param name The name of the player, this is used to render the current coins,
     *             and at the end of the game to the rankings
     * @param x position of the player on the board
     * @param y position of the player on the board
     * @param image this define which image the player uses, depending on who goes first.
     */
    public Player(String name, float x, float y, BufferedImage image) {
        super(x, y, 80, 120);
        this.x = x;
        this.y = y;
        this.image = image;
        this.name = name;
    }

    /**
     * sets a boolean that determines if the player is actively playing a turn, if its true box effects can trigger
     * @param currentTurn boolean that sets the current turn for the current player to active or false depending on what is desired
     */
    public void setCurrentTurn(Boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * @return the boolean of the player, to determine if this player has played his turn yet, its mainly used by the dice and boxes
     */
    public boolean getCurrentTurn(){
        return currentTurn;
    }

    /**
     * @return a boolean value used to determine if the player is going backwards on a list, currently is only used in the phase C of the board
     */
    public Boolean getReversed() {
        return reversed;
    }

    /**
     * sets the player to walk in reverse in the phase C
     * @param reversed boolean value
     */
    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * this is triggered when the player steps on one of the nodes that allow it to change to a different phase
     * @param changeDirection if true the player changes  to a different state, if false it stays on the main circuit
     */
    public void setDirection(Boolean changeDirection) {
    this.changeDirection = changeDirection;
    }

    /**
     * this method moves the player to a destination based on the players movement, the movement is defined by a dice roll
     * @param game the Main game class, is needed to interact with other objects that exist in the game
     * @throws InterruptedException an exception to wait 500mill every time the player steps on a box, to better render movement
     */
    public void move(Game game) throws InterruptedException {
        int boxesLeft =  getMovement();
        while (boxesLeft > 0) {
            // Goes to next Node<Box>
            if(getPosition().getData().isCrossRoads()){
                game.pauseGame();
            }
            else {
                setDirection(false);
            }
            Node<Box> nextNode = pickPath(game);
          //  Node<Box> nextNode = getPosition().getNext();
            if(boxesLeft==1){
                checkForPlayers(this,nextNode,game);
            }
            if(getMovement()!=0) {
                setPosition(nextNode);
            }
            float  newX = position.getData().getX();
            float  newY = position.getData().getY();
            setRenderPos(newX,newY);
            Thread.sleep(500);
            // Checks if there is a star
            game.checkStar(this);
            // Subtracts from number given on dice
            setMovement(--boxesLeft);
        }
        update(this.getPosition().getData(), game);
    }

    /**
     *  method used to pick which path the player moves to, it uses another method, to check the connector enum of each node marked as a connector
     * @param game it takes the main game, which has access to the handler, and then passes the handler to its helper method.
     * @return returns a specific node, to move the player to, it also sets the direction to reversed if the player is walking backwards in phase c
     */
    private Node<Box> pickPath(Game game) {
        Node <Box> nextNode;
        Handler handler = game.getHandler();
        Node <Box> phaseAFirst = handler.getBoard().getPhaseA().getHead();
        Node <Box> phaseAExit = handler.getBoard().getMainCircuit().get(44);
        Node <Box> phaseBFirst = handler.getBoard().getPhaseB().getHead();
        Node <Box> phaseBExit = handler.getBoard().getMainCircuit().get(24);
        Node <Box> phaseC1First = handler.getBoard().getPhaseC().getHead();
        Node <Box> phaseC1Exit = handler.getBoard().getMainCircuit().get(33);
        Node <Box> phaseC2First = handler.getBoard().getPhaseC().getLast();
        Node <Box> phaseC2Exit = handler.getBoard().getMainCircuit().get(12);

        switch (getConnector(getPosition(),handler, changeDirection)) {

            case PHASE_A_FIRST -> {
                nextNode = phaseAFirst;
                setDirection(false);
            }
            case PHASE_A_LAST -> {
                nextNode = phaseAExit;
                setDirection(false);
            }
            case PHASE_B_FIRST -> {
                nextNode = phaseBFirst;
                setDirection(false);
            }
            case PHASE_B_LAST -> {
                nextNode = phaseBExit;
                setDirection(false);
            }
            case PHASE_C_FIRST -> {
                nextNode = phaseC1First;
                setDirection(false);
            }
            case PHASE_C_LAST -> {
                nextNode = phaseC1Exit;
                setDirection(false);
            }
            case PHASE_C_FIRST_REVERSED -> {
                nextNode = phaseC2First;
                setDirection(false);
                setReversed(true);
            }
            case PHASE_C_LAST_REVERSED -> {
                nextNode = phaseC2Exit;
                setDirection(false);
                setReversed(false);
            }
            case REVERSED -> nextNode = getPosition().getPrevious();

            default -> {
                nextNode = getPosition().getNext();
                setDirection(false);
                setReversed(false);
            }
        }
        return nextNode;
    }

    /**
     * this method is used by pick path, when called, it checks the following
     * @param current the current node on which the player is
     * @param handler it uses the handler, to get the list,
     * @param changeDirection a boolean corresponding to a button on the board, if pressed, it changes to true
     * @return an enum used by the pickPath() method.
     */
    private Connector getConnector(Node<Box> current, Handler handler,boolean changeDirection) {

        Node<Box> phaseAConnector = handler.getBoard().getMainCircuit().get(11);
        Node<Box> phaseALast = handler.getBoard().getPhaseA().getLast();
        Node<Box> phaseBConnector = handler.getBoard().getMainCircuit().get(15);
        Node<Box> phaseBLast = handler.getBoard().getPhaseB().getLast();
        Node<Box> phaseCConnector = handler.getBoard().getMainCircuit().get(12);
        Node<Box> phaseCFirst = handler.getBoard().getPhaseC().getHead();
        Node<Box> phaseCLast = handler.getBoard().getPhaseC().getLast();
        Node<Box> phaseCConnectorReversed = handler.getBoard().getMainCircuit().get(33);
        Node<Box> phaseDPivot = handler.getBoard().getPhaseD().get(1);


        if(current == phaseAConnector){
            if(changeDirection){
                return PHASE_A_FIRST;
            }
            return NONE;
        }
        else if(current == phaseALast){
                return PHASE_A_LAST;
        }
        else if(current == phaseBConnector){
            if(changeDirection) {
                return PHASE_B_FIRST;
            }
            return NONE;
        }
        else if(current == phaseBLast){
            return PHASE_B_LAST;
        }
        else if(current == phaseCConnector){
            if(changeDirection) {
                return PHASE_C_FIRST;
            }
            return NONE;
        }
        else if(current == phaseCFirst && reversed){
            return PHASE_C_LAST_REVERSED;
        }
        else if(current == phaseCFirst){
            return NONE;
        }
        else if(current == phaseCLast && reversed) {
            return REVERSED;
        }
        else if(current == phaseCLast){
            return PHASE_C_LAST;
        }
        else if(current == phaseCConnectorReversed){
            if(changeDirection) {
                return PHASE_C_FIRST_REVERSED;
            }
            return NONE;
        }
        else if(getReversed()){
           return REVERSED;
        }
        else if(current == phaseDPivot){
            if(changeDirection){
                setReversed(true);
                return REVERSED;
            }
            else {
                setReversed(false);
                return NONE;
            }
        }
        else return NONE;
    }

    /**
     * getter for the players name
     * @return a String corresponding to the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the players remaining movement
     * @return an int corresponding to the movement of the player
     */
    public int getMovement() {
        return movement;
    }

    /**
     * sets the current movement of the player, its mainly used by the dice, and also the connectors, for the player to resume movement
     * @param movement int value of how many spaces the player will move
     */
    public void setMovement(int movement) {
        wonDuel = false;
        this.movement = movement;
    }

    /**
     * getter for the players current coins
     * @return an int value corresponding to this players current coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * a method that adds coins to a player
     * @param coins an int value that is added to the player's current coin value
     */
    public void addCoins(int coins) {
        this.coins += coins;
        if(this.coins < 0){
            this.coins = 0;
        }
    }

    /**
     * getter for the players current stars
     * @return an int value corresponding to the player's stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * a method that adds stars to a player
     * @param stars an int value that is added to the player's current stars
     */
    public void addStars(int stars) {
        this.stars += stars;
        if(this.stars < 0){
            this.stars = 0;
        }
    }

    /**
     * getter for the players position
     * @return a node of the box that the player is currently storing as its position
     */
    public Node<Box> getPosition() {
        return position;
    }

    /**
     * Changes the player's position to a new one
     * @param position a node, that will replace the players current position
     */
    public void setPosition(Node<Box> position) {
        this.position = position;
    }

    /**
     * this changes the rendered position of the player, by changing its x and y values
     * @param x an int corresponding to the x position on the screen
     * @param y an int corresponding to the y position on the screen
     */
    public void setRenderPos(float x, float y){
        this.x = x;
        this.y = y - 45;
    }

    /**
     * a method that executes the action of the boxes
     * @param currentBox this checks the box the player is currently in and triggers the box action, either add coins,
     *                   subtract coins, or trigger an event
     * @param game the current game the player is in, is passed to the box, so it can have access to variables and objects in the game
     */
    public void update(Box currentBox, Game game) {
        currentBox.boxAction(this, game);
    }

    /**
     * The render method is similar to that of other entities, with the exception that this method has a variable image,
     * this is in order to have different images every time a player is initialized
     * @param g from java awt, is a object utilized to render graphics
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image,(int) x,(int) y, width, height, null);
    }

    /**
     * This method identifies which player the player is, by comparing to other player objs
     * @param player the current player
     * @param game the current game
     * @return an int related to the player order
     */
    private int identifyPlayer(Player player, Game game){
        if(game.getNumberOfPlayers()==4){
            if(player== game.getPlayerList().get(3).getData()){
                return 4;
            }
        }
        if(game.getNumberOfPlayers()>=3) {
            if (player == game.getPlayerList().get(2).getData()) {
                return 3;
            }
        }
        if(player== game.getPlayerList().get(1).getData()){
            return 2;
        }
        else {
            return 1;
        }
    }

    /**
     * This method checks other players, and checks if they have that position associated with it
     * it uses a switch depending on which player is playing, to check the spaces the character will move next to.
     * @param player the current player
     * @param nextNode the players next node, this method is called when the player has 1 movement left
     * @param game the current game
     * @throws InterruptedException this is in order to wait the game thread before and after triggering the mini game
     */
    private void checkForPlayers(Player player, Node<Box> nextNode, Game game) throws InterruptedException {
        Player player1 = game.getPlayerList().get(0).getData();
        Player player2 = game.getPlayerList().get(1).getData();
        Player player3 = null, player4 = null;
        if(game.getNumberOfPlayers()>=3) {
            player3 = game.getPlayerList().get(2).getData();
        }
        if(game.getNumberOfPlayers()==4) {
            player4 = game.getPlayerList().get(3).getData();
        }

        switch (identifyPlayer(player,game)){
            case 1-> {
                if(nextNode == player2.getPosition()){
                    boxDuel(player, player2, game);
                }
                else if(game.getNumberOfPlayers()>=3){
                    assert player3 != null;
                    if(nextNode == player3.getPosition()) {
                        boxDuel(player, player3, game);
                    }
                }
                else {
                    if(game.getNumberOfPlayers() == 4) {
                        assert player4 != null;
                        if (nextNode == player4.getPosition()) {
                            boxDuel(player, player4, game);
                        }
                    }
                }
            }
            case 2-> {
                if(nextNode == player1.getPosition()){
                    boxDuel(player, player1, game);
                }
                else if(game.getNumberOfPlayers()>=3 && nextNode == Objects.requireNonNull(player3).getPosition()){
                    boxDuel(player, player3, game);
                }
                else {
                    if(game.getNumberOfPlayers() == 4) {
                        assert player4 != null;
                        if (nextNode == player4.getPosition()) {
                            boxDuel(player, player4, game);
                        }
                    }
                }
            }
            case 3->{
                if(nextNode == player1.getPosition()){
                    boxDuel(player, player1, game);
                }
                else if(nextNode == player2.getPosition()){
                    boxDuel(player, player2, game);
                }
                else {
                    if(game.getNumberOfPlayers() == 4) {
                        assert player4 != null;
                        if (nextNode == player4.getPosition()) {
                            boxDuel(player, player4, game);
                        }
                    }
                }
            }
            case 4->{
                if(nextNode == player1.getPosition()){
                    boxDuel(player, player1, game);
                }
                else if(nextNode == player2.getPosition()){
                    boxDuel(player, player2, game);
                }
                else {
                    assert player3 != null;
                    if(nextNode == player3.getPosition()){
                        boxDuel(player,player3,game);
                    }
                }
            }
        }
    }

    /**
     * This method throws  duel, this is triggered by check for players, if they find a match
     * @param movingPlayer the player who threw the dice, and is currently moving
     * @param targetPlayer the player in the box were the player might land
     * @param game the current game
     * @throws InterruptedException exception to wait the game until the game is finished
     */
    public void boxDuel(Player movingPlayer, Player targetPlayer, Game game) throws InterruptedException {
        movingPlayer.setMovement(0);
        game.updateDuelPlayers(movingPlayer,targetPlayer);
        GameLoop.setState(GameLoop.gameDependantStates.get(0).getData());
        game.pauseGame();
        checkWinner(movingPlayer,targetPlayer);
    }

    /**
     * This is a method that is run after the duel, if the duel was won it will swap the players positions
     * @param movingPlayer the player that is currently playing its turn
     * @param targetPlayer the player that is occupying the box
     */
    public void checkWinner(Player movingPlayer, Player targetPlayer){
        if(wonDuel) {
            this.wonDuel = false;
            swapPlayers(movingPlayer,targetPlayer);
        }
        movingPlayer.setMovement(0);
    }

    /**
     * This method is similar to the one the events, it swaps players, except it takes two arguments, since its two specific players
     * @param movingPlayer the player that is currently playing its turn
     * @param targetPlayer the player that is occupying the box
     */
    private void swapPlayers(Player movingPlayer, Player targetPlayer) {
        Node<Box> targetPosition = targetPlayer.getPosition();
        Node<Box> playerPosition = movingPlayer.getPosition();
        movingPlayer.setRenderPos(targetPosition.getData().getX(),targetPosition.getData().getY());
        targetPlayer.setPosition(playerPosition);
        targetPlayer.setRenderPos(playerPosition.getData().getX(),playerPosition.getData().getY());
        movingPlayer.setPosition(targetPosition);
    }
}
