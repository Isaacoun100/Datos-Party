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

    private int movement,
                coins = 0,
                stars = 0;

    private float x;
    private float y;
    BufferedImage image;
    private Boolean changeDirection;
    private Node<Box> position;

    public Boolean getChangeDirection() {
        return changeDirection;
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

    public void move(Game game, Handler handler) throws InterruptedException {
        int boxesLeft =  getMovement();
        Node <Box> nextNode;
        Node <Box> phaseAFirst = handler.getBoard().getPhaseA().getHead();
        Node <Box> phaseALast = handler.getBoard().getMainCircuit().get(44);
        Node <Box> phaseBFirst = handler.getBoard().getPhaseB().getHead();
        Node <Box> phaseBLast = handler.getBoard().getMainCircuit().get(24);
        Node <Box> phaseCFirst = handler.getBoard().getPhaseC().getHead();
        Node <Box> phaseCLast = handler.getBoard().getMainCircuit().get(33);
        while (boxesLeft > 0) {
            // Goes to next Node<Box>
            if(getPosition().getData().isCrossRoads()){
                game.pauseGame();
            }
            else {
                setDirection(false);
            }
            switch (getConnector(getPosition(),handler, changeDirection)) {
                case PHASE_A_FIRST -> {
                    nextNode = phaseAFirst;
                    setDirection(false);
                }
                case PHASE_A_LAST -> {
                    nextNode = phaseALast;
                    setDirection(false);
                }
                case PHASE_B_FIRST -> {
                    nextNode = phaseBFirst;
                    setDirection(false);
                }
                case PHASE_B_LAST -> {
                    nextNode = phaseBLast;
                    setDirection(false);
                }
                case PHASE_C_FIRST -> {
                    nextNode = phaseCFirst;
                    setDirection(false);
                }
                case PHASE_C_LAST -> {
                    nextNode = phaseCLast;
                    setDirection(false);
                }
                default -> {
                    nextNode = getPosition().getNext();
                    setDirection(false);
                }
            }
          //  Node<Box> nextNode = getPosition().getNext();
            setPosition(nextNode);
            game.sleep(500);
            // Checks if there is a star
            game.checkStar(this);
            // Subtracts from number given on dice
            boxesLeft--;
        }
        update(this.getPosition().getData(), game);
        System.out.println("Coins: " + this.coins);
        System.out.println("Stars: " + this.stars);
    }

    private Connector getConnector(Node<Box> current, Handler handler,boolean changeDirection) {

        Node<Box> phaseAConnector = handler.getBoard().getMainCircuit().get(11);
        Node<Box> phaseALast = handler.getBoard().getPhaseA().getLast();
        Node<Box> phaseBConnector = handler.getBoard().getMainCircuit().get(15);
        Node<Box> phaseBLast = handler.getBoard().getPhaseB().getLast();
        Node<Box> phaseCConnector = handler.getBoard().getMainCircuit().get(12);
        Node<Box> phaseCLast = handler.getBoard().getPhaseC().getLast();
        Node<Box> phaseCConnectorReversed = handler.getBoard().getMainCircuit().get(33);
        Node<Box> phaseCLastReversed = handler.getBoard().getPhaseC().getHead();

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
        else if(current == phaseCLast){
            return PHASE_C_LAST;
        }
        else if(current == phaseCConnectorReversed){
            if(changeDirection) {
                return PHASE_C_FIRST_REVERSED;
            }
            return NONE;
        }
        else if(current == phaseCLastReversed){
            return PHASE_C_LAST_REVERSED;
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
        this.x = position.getData().getX();
        this.y = position.getData().getY() - 45;
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
