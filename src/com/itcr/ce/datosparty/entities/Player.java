package com.itcr.ce.datosparty.entities;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.logic.Connector;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.itcr.ce.datosparty.logic.Connector.*;

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


    public void setCurrentTurn(Boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean getCurrentTurn(){
        return currentTurn;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

        public void setDirection(Boolean changeDirection) {
        this.changeDirection = changeDirection;
    }

    public Player(String name, float x, float y, BufferedImage image) {
        super(x, y, 80, 120);
        this.x = x;
        this.y = y;
        this.image = image;
        this.name = name;
    }

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
            setPosition(nextNode);
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

            case PHASE_A_FIRST -> {nextNode = phaseAFirst;
                setDirection(false);
            }
            case PHASE_A_LAST -> {nextNode = phaseAExit;
                setDirection(false);
            }
            case PHASE_B_FIRST -> {nextNode = phaseBFirst;
                setDirection(false);
            }
            case PHASE_B_LAST -> {nextNode = phaseBExit;
                setDirection(false);
            }
            case PHASE_C_FIRST -> {nextNode = phaseC1First;
                setDirection(false);
            }
            case PHASE_C_LAST -> {nextNode = phaseC1Exit;
                setDirection(false);
            }
            case PHASE_C_FIRST_REVERSED -> {nextNode = phaseC2First;
                setDirection(false);
                setReversed(true);
            }
            case PHASE_C_LAST_REVERSED -> {nextNode = phaseC2Exit;
                setDirection(false);
                setReversed(false);
            }
            case REVERSED -> {
                nextNode = getPosition().getPrevious();
                setDirection(false);
            }
            default -> {
                nextNode = getPosition().getNext();
                setDirection(false);
                setReversed(false);
            }
        }
        return nextNode;
    }

    private Connector getConnector(Node<Box> current, Handler handler,boolean changeDirection) {

        Node<Box> phaseAConnector = handler.getBoard().getMainCircuit().get(11);
        Node<Box> phaseALast = handler.getBoard().getPhaseA().getLast();
        Node<Box> phaseBConnector = handler.getBoard().getMainCircuit().get(15);
        Node<Box> phaseBLast = handler.getBoard().getPhaseB().getLast();
        Node<Box> phaseCConnector = handler.getBoard().getMainCircuit().get(12);
        Node<Box> phaseCFirst = handler.getBoard().getPhaseC().getHead();
        Node<Box> phaseCLast = handler.getBoard().getPhaseC().getLast();
        Node<Box> phaseCConnectorReversed = handler.getBoard().getMainCircuit().get(33);


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
        else return NONE;
    }

    public String getName() {
        return name;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
        if(this.coins < 0){
            this.coins = 0;
        }
    }

    public int getStars() {
        return stars;
    }

    public void addStars(int stars) {
        this.stars += stars;
        if(this.stars < 0){
            this.stars = 0;
        }
    }

    public Node<Box> getPosition() {
        return position;
    }

    public void setPosition(Node<Box> position) {
        this.position = position;
    }
    public void setRenderPos(float x, float y){
        this.x = x;
        this.y = y-45;
    }

    public void update(Box currentBox, Game game) {
        currentBox.boxAction(this, game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image,(int) x,(int) y, width, height, null);
    }
}
