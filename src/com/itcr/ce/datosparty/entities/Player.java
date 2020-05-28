package com.itcr.ce.datosparty.entities;

import com.itcr.ce.datosparty.dataStructures.DoublyNode;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.entities.boxes.Box;

import java.awt.*;

public class Player extends Entity {

    private final String name;

    private int movement,
                coins = 0,
                stars = 0;

    private float x;
    private float y;

    private DoublyNode<Box> position;

    public Player(String name, float x, float y) {
        super(x, y, 8, 8);
        this.name = name;
    }

    public void move() {
        int boxesLeft =  getMovement();
        while (boxesLeft > 0) {
            // Goes to next Node<Box>
            setPosition(getPosition().getNext());
            // Checks if there is a star
            getPosition().getData().checkStar(this);
            // Subtracts from number given on dice
            boxesLeft--;
            x = position.getData().getX();
            y = position.getData().getY();
            System.out.println("Position: " + (int) x + ", "+ (int) y );
        }
        update(this.getPosition().getData());
    }



    public String getName() {
        return name;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement() {
        this.movement = Dice.roll(6,1);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins += coins;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars += stars;
    }

    public DoublyNode<Box> getPosition() {
        return position;
    }

    public void setPosition(DoublyNode<Box> position) {
        this.position = position;
    }

    public void update(Box currentBox) {
        currentBox.boxAction(this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.playerStatic,(int) x,(int) y, width, height, null);
    }
}
