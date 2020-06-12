package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FifthMinigameState extends State {

    UIManager faultyPaintball;
    private final Player player1;
    private final Player player2;
    private Player player3;
    private Player player4;
    private  int player1HP;
    private  int player2HP;
    private int player3HP;
    private int player4HP;
    private final Game game;
    private long lastTime, timer;
    private final int coolDown;
    private int speed;
    private int ticks;
    private int currentPlayer = 1;
    private int target = 0;
    private int colorIndex = -1;
    private final int numPlayers;
    private boolean setup = false;
    private boolean knockOutP1 = false, knockOutP2 = false, knockOutP3 = false, knockOutP4 = false;
    SinglyList<String> shotColor = new SinglyList<>();


    public FifthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.game = game;
        faultyPaintball = new UIManager(handler);

        this.coolDown = 500;
        lastTime = System.currentTimeMillis();

        int playerWidth = 8;
        int playerHeight = 8*2;

        int player1X = 1;
        int player1Y = 1;

        int player2X = 30;
        int player2Y = 1;

        int player3X = 1;
        int player3Y = 30;

        int player4X = 30;
        int player4Y = 30;

        this.numPlayers = numPlayers;

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        faultyPaintball.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.redSelectorL,"player1BtnRed"));
        faultyPaintball.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.blueSelectorL,"player1BtnBlue"));
        faultyPaintball.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.paintgun1,"player1"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[0],"player1RedDamage1"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[1],"player1RedDamage2"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[2],"player1RedDamage3"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[3],"player1RedDamage4"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[4],"player1RedDamage5"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[5],"player1RedDamage6"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[0],"player1BlueDamage1"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[1],"player1BlueDamage2"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[2],"player1BlueDamage3"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[3],"player1BlueDamage4"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[4],"player1BlueDamage5"));
        faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[5],"player1BlueDamage6"));


        faultyPaintball.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.redSelectorR,"player2BtnRed"));
        faultyPaintball.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.blueSelectorR,"player2BtnBlue"));
        faultyPaintball.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.paintgun2,"player2"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[0],"player2RedDamage1"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[1],"player2RedDamage2"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[2],"player2RedDamage3"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[3],"player2RedDamage4"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[4],"player2RedDamage5"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[5],"player2RedDamage6"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[0],"player2BlueDamage1"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[1],"player2BlueDamage2"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[2],"player2BlueDamage3"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[3],"player2BlueDamage4"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[4],"player2BlueDamage5"));
        faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[5],"player2BlueDamage6"));


        if(numPlayers>=3){

            faultyPaintball.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.yellowSelectorL,"player1BtnYellow"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[0],"player1YellowDamage1"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[1],"player1YellowDamage2"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[2],"player1YellowDamage3"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[3],"player1YellowDamage4"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[4],"player1YellowDamage5"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[5],"player1YellowDamage6"));

            faultyPaintball.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.yellowSelectorR,"player2BtnYellow"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[0],"player2YellowDamage1"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[1],"player2YellowDamage2"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[2],"player2YellowDamage3"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[3],"player2YellowDamage4"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[4],"player2YellowDamage5"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[5],"player2YellowDamage6"));

            faultyPaintball.addObject(new UIImage(player3X, player3Y, playerWidth, playerHeight, Assets.redSelectorL,"player3BtnRed"));
            faultyPaintball.addObject(new UIImage(player3X, player3Y, playerWidth, playerHeight, Assets.blueSelectorL,"player3BtnBlue"));
            faultyPaintball.addObject(new UIImage(player3X, player3Y, playerWidth, playerHeight, Assets.yellowSelectorL,"player3BtnYellow"));
            faultyPaintball.addObject(new UIImage(player3X, player3Y, playerWidth, playerHeight, Assets.paintgun3,"player3"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[0],"player3RedDamage1"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[1],"player3RedDamage2"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[2],"player3RedDamage3"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[3],"player3RedDamage4"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[4],"player3RedDamage5"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[5],"player3RedDamage6"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[0],"player3BlueDamage1"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[1],"player3BlueDamage2"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[2],"player3BlueDamage3"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[3],"player3BlueDamage4"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[4],"player3BlueDamage5"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[5],"player3BlueDamage6"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[0],"player3YellowDamage1"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[1],"player3YellowDamage2"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[2],"player3YellowDamage3"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[3],"player3YellowDamage4"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[4],"player3YellowDamage5"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[5],"player3YellowDamage6"));


            player3 = game.getPlayerList().get(2).getData();

        }

        if(numPlayers == 4){
            faultyPaintball.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.greenSelectorL,"player1BtnGreen"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[0],"player1GreenDamage1"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[1],"player1GreenDamage2"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[2],"player1GreenDamage3"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[3],"player1GreenDamage4"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[4],"player1GreenDamage5"));
            faultyPaintball.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[5],"player1GreenDamage6"));

            faultyPaintball.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.greenSelectorR,"player2BtnGreen"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[0],"player2GreenDamage1"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[1],"player2GreenDamage2"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[2],"player2GreenDamage3"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[3],"player2GreenDamage4"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[4],"player2GreenDamage5"));
            faultyPaintball.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[5],"player2GreenDamage6"));

            faultyPaintball.addObject(new UIImage(player3X, player3Y, playerWidth, playerHeight, Assets.greenSelectorL,"player3BtnGreen"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[0],"player3GreenDamage1"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[1],"player3GreenDamage2"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[2],"player3GreenDamage3"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[3],"player3GreenDamage4"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[4],"player3GreenDamage5"));
            faultyPaintball.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[5],"player3GreenDamage6"));

            faultyPaintball.addObject(new UIImage(player4X, player4Y, playerWidth, playerHeight, Assets.redSelectorR,"player4BtnRed"));
            faultyPaintball.addObject(new UIImage(player4X, player4Y, playerWidth, playerHeight, Assets.blueSelectorR,"player4BtnBlue"));
            faultyPaintball.addObject(new UIImage(player4X, player4Y, playerWidth, playerHeight, Assets.yellowSelectorR,"player4BtnYellow"));
            faultyPaintball.addObject(new UIImage(player4X, player4Y, playerWidth, playerHeight, Assets.greenSelectorR,"player4BtnGreen"));
            faultyPaintball.addObject(new UIImage(player4X, player4Y, playerWidth, playerHeight, Assets.paintgun4,"player4"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[0],"player4RedDamage1"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[1],"player4RedDamage2"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[2],"player4RedDamage3"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[3],"player4RedDamage4"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[4],"player4RedDamage5"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[5],"player4RedDamage6"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[0],"player4BlueDamage1"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[1],"player4BlueDamage2"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[2],"player4BlueDamage3"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[3],"player4BlueDamage4"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[4],"player4BlueDamage5"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[5],"player4BlueDamage6"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[0],"player4YellowDamage1"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[1],"player4YellowDamage2"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[2],"player4YellowDamage3"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[3],"player4YellowDamage4"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[4],"player4YellowDamage5"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[5],"player4YellowDamage6"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[0],"player4GreenDamage1"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[1],"player4GreenDamage2"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[2],"player4GreenDamage3"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[3],"player4GreenDamage4"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[4],"player4GreenDamage5"));
            faultyPaintball.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[5],"player4GreenDamage6"));

            player4 = game.getPlayerList().get(3).getData();
        }

    }

    @Override
    public void tick() {
        gameSetup();
        ticks +=1;
        if(ticks==200){
            ticks = 0;
        }
        randomSelector();
        handler.getMouseManager().setUiManager(faultyPaintball);
        faultyPaintball.tick();
        if(handler.getKeyManager().space){
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            if(timer > coolDown) {
                shotColor.add(checkShooter());
                colorIndex++;
                if(currentPlayer==1) {
                    dealDamage();
                } else if( currentPlayer ==2){
                    dealDamage();
                } else if( currentPlayer == 3){
                    dealDamage();
                } else {
                    dealDamage();
                }
                nextPlayer();
            }
            checkKnockouts();
        }
        if(numPlayers == 2){
            chooseWinner(knockOutP1,knockOutP2);
        }
        if(numPlayers == 3){
            chooseWinner(knockOutP1,knockOutP2,knockOutP3);
        }
        if(numPlayers == 4){
            chooseWinner(knockOutP1,knockOutP2,knockOutP3,knockOutP4);
        }
    }

    @Override
    public void render(Graphics g) {
        faultyPaintball.renderById(g,"player1");
        faultyPaintball.renderById(g,"player2");
        if(numPlayers>=3){
            faultyPaintball.renderById(g,"player3");
        }
        if(numPlayers==4) {
            faultyPaintball.renderById(g, "player4");
        }

        renderTargetColor(g);
        renderDamage(g,player1HP,"player1");
        renderDamage(g,player2HP, "player2");
        renderDamage(g,player3HP,"player3");
        renderDamage(g,player4HP,"player4");

    }

    private void renderDamage(Graphics g, int playerHP, String target){

        if(playerHP <= 5){
            renderShot(g, playerHP,target, checkShooter());
        }
        if (playerHP <= 4){
            renderShot(g, playerHP,target, checkShooter());
        }
        if (playerHP <= 3){
            renderShot(g, playerHP, target, checkShooter());
        }
        if (playerHP <= 2){
            renderShot(g, playerHP,target, checkShooter());
        }
        if (playerHP <= 1){
            renderShot(g, playerHP,target, checkShooter());
        }
        if (playerHP == 0){
            renderShot(g, playerHP,target, checkShooter());
        }
    }

    private void renderShot(Graphics g,int playerHP,String target, String shooter){
        renderDamageColor(g,target,playerHP,shooter);
    }

    private String checkShooter(){
        switch (currentPlayer) {
            case 1 -> {
                return "Red";
            }
            case 2 -> {
                return "Blue";
            }
            case 3 -> {
                return "Yellow";
            }
            default -> {
                return "Green";
            }
        }
    }

    private void renderTargetColor(Graphics g){
        switch (currentPlayer){
            case 1 -> renderTarget(g,"Red");
            case 2 -> renderTarget(g,"Blue");
            case 3 -> renderTarget(g,"Yellow");
            case 4 -> renderTarget(g,"Green");
        }
    }

    private void renderTarget(Graphics g, String color){
        switch (target){
            case 1 -> faultyPaintball.renderById(g,"player1Btn"+color);
            case 2 -> faultyPaintball.renderById(g,"player2Btn"+color);
            case 3 -> faultyPaintball.renderById(g,"player3Btn"+color);
            case 4 -> faultyPaintball.renderById(g,"player4Btn"+color);
        }
    }

    private void renderDamageColor(Graphics g, String target, int hp, String shooter) {
        renderDamageStage(g, target +shooter, hp);
    }

    private void renderDamageStage(Graphics g, String targetAndColor,int hp){
        if (hp==5){
            faultyPaintball.renderById(g, targetAndColor + "Damage1");
        }
        if(hp==4) {
            faultyPaintball.renderById(g, targetAndColor + "Damage2");
        }
        if(hp==3) {
            faultyPaintball.renderById(g,targetAndColor+"Damage3");
        }
        if(hp==2) {
            faultyPaintball.renderById(g,targetAndColor+"Damage4");
        }
        if(hp==1) {
            faultyPaintball.renderById(g, targetAndColor + "Damage5");
        }
        if(hp==0) {
            faultyPaintball.renderById(g, targetAndColor+"Damage6");
        }
    }

    private void randomSelector(){
        if(ticks%speed==0) {
            if (numPlayers == 4) {
                target = Dice.roll(1, 4);
            }
            if (numPlayers == 3) {
                target = Dice.roll(1, 3);
            }
            if(numPlayers==2){
                target = Dice.roll(1, 2);
            }
        }
    }

    private void checkKnockouts(){
        if(player1HP == 0){
            knockOutP1 = true;
        }
        if(player2HP == 0){
            knockOutP2 = true;
        }
        if(player3HP == 0){
            knockOutP3 = true;
        }
        if(player4HP == 0){
            knockOutP4 = true;
        }
    }

    private void nextPlayer(){
        if(numPlayers==2){
            if (currentPlayer != 2) {
                currentPlayer += 1;
            } else {
                currentPlayer = 1;
                increaseSpeed();
            }
            timer = 0;
        }
        if(numPlayers==3){
            if (currentPlayer != 3) {
                currentPlayer += 1;
            } else {
                currentPlayer = 1;
                increaseSpeed();
            }
            timer = 0;
        }
        if(numPlayers==4) {
            if (currentPlayer != 4) {
                currentPlayer += 1;
            } else {
                currentPlayer = 1;
                increaseSpeed();
            }
            timer = 0;
        }
    }

    private void increaseSpeed(){
        if(speed>10){
            speed-=5;
        }
    }

    private void dealDamage() {
        if (target==1) {
            player1HP = loseHP(player1HP);
        }
        if (target==2) {
            player2HP = loseHP(player2HP);
        }
        if (target==3) {
            player3HP = loseHP(player3HP);
        }
        if (target==4) {
            player4HP = loseHP(player4HP);
        }
    }

    private int loseHP(int playerHP){
        if(playerHP!=0){
            playerHP--;
        }
        return playerHP;
    }

    private void chooseWinner(boolean knockOutP1, boolean knockOutP2, boolean knockOutP3, boolean knockOutP4){
        if(knockOutP1&&knockOutP2&&knockOutP3&&!knockOutP4){
            winGame(player4);
        } else if(knockOutP1&&knockOutP2&&!knockOutP3&&knockOutP4){
            winGame(player3);
        } else if(knockOutP1&&!knockOutP2&&knockOutP3&&knockOutP4){
            winGame(player2);
        } else if(!knockOutP1&&knockOutP2&&knockOutP3&&knockOutP4){
            winGame(player1);
        }
    }
    private void chooseWinner(boolean knockOutP1, boolean knockOutP2, boolean knockOutP3){
        if(knockOutP1&&knockOutP2&&!knockOutP3){
            winGame(player3);
        } else if(knockOutP1&&!knockOutP2&&knockOutP3){
            winGame(player2);
        } else if(!knockOutP1&&knockOutP2&&knockOutP3){
            winGame(player1);
        }
    }
    private void chooseWinner(boolean knockOutP1, boolean knockOutP2){
        if(knockOutP1&&!knockOutP2){
            winGame(player2);
        } else if(!knockOutP1&&knockOutP2){
            winGame(player1);
        }
    }

    private void winGame(Player player) {
        setup = false;
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

    private void gameSetup(){
        if(!setup){
            setup = true;
            target = 0;
            speed = 25;
            knockOutP1 = false;
            knockOutP2 = false;
            knockOutP3 = false;
            knockOutP4 = false;
            shotColor.clear();
            player1HP = 6;
            player2HP = 6;
            if(numPlayers>=3){
                player3HP = 6;
            }
            if (numPlayers==4){
                player4HP = 6;
            }
        }
    }

}
