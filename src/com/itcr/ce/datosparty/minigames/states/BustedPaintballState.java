package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIBackground;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

/**
 * this class is the busted paitball mini game that plays during regular gameplay
 */
public class BustedPaintballState extends State {

    UIManager bustedPaintBallUI;
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
    private final int numPlayers;
    private boolean setup = false;
    private boolean knockOutP1 = false, knockOutP2 = false, knockOutP3 = false, knockOutP4 = false;
    private boolean gameStart = false, gameWon = false;
    private String winner;
    private final Font font;
    SinglyList<String> shotColor = new SinglyList<>();

    /**
     * Constructor for the buster paintball mini game
     * @param handler handler obj, that is used to retrieve key listener
     * @param numPlayers number of players used as a conditional in case its less than 4 players
     * @param game the game obj in order to have access to all variables and methods from the game
     */
    public BustedPaintballState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.game = game;
        bustedPaintBallUI = new UIManager(handler);

        font = Assets.fourB30.deriveFont(Font.PLAIN,35);

        this.coolDown = 500;
        lastTime = System.currentTimeMillis();

        int playerWidth = 8;
        int playerHeight = 8*2;

        int player1X = 30;
        int player1Y = 35;

        int player2X = 60;
        int player2Y = 35;

        int player3X = 30;
        int player3Y = 50;

        int player4X = 60;
        int player4Y = 50;

        this.numPlayers = numPlayers;

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();
        int width = GameLauncher.width / 16;
        int height = GameLauncher.height / 16;
        bustedPaintBallUI.addObject(new UIImage(8,1,9*10,2*10,Assets.paintBallLogo,"gameTitle"));
        bustedPaintBallUI.addObject(new UIImage((float) width /2-20,(float) height /2-15,4*10,2*10,Assets.starPurchaseBackDrop[0],"backDrop"));
        bustedPaintBallUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn, "okBtn",()-> gameStart = true));
        bustedPaintBallUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"endGameOkBtn", () ->{
            if(gameWon) {
                backToBoard();
            }
        }));

        bustedPaintBallUI.addObject(new UIBackground(Assets.paintBallBG,"backGround"));
        bustedPaintBallUI.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.redSelectorL,"player1BtnRed"));
        bustedPaintBallUI.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.blueSelectorL,"player1BtnBlue"));
        bustedPaintBallUI.addObject(new UIImage(player1X, player1Y, playerWidth, playerHeight, Assets.paintgun1,"player1"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[0],"player1RedDamage1"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[1],"player1RedDamage2"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[2],"player1RedDamage3"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[3],"player1RedDamage4"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[4],"player1RedDamage5"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageRedL[5],"player1RedDamage6"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[0],"player1BlueDamage1"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[1],"player1BlueDamage2"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[2],"player1BlueDamage3"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[3],"player1BlueDamage4"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[4],"player1BlueDamage5"));
        bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageBlueL[5],"player1BlueDamage6"));


        bustedPaintBallUI.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.redSelectorR,"player2BtnRed"));
        bustedPaintBallUI.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.blueSelectorR,"player2BtnBlue"));
        bustedPaintBallUI.addObject(new UIImage(player2X, player2Y, playerWidth, playerHeight, Assets.paintgun2,"player2"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[0],"player2RedDamage1"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[1],"player2RedDamage2"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[2],"player2RedDamage3"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[3],"player2RedDamage4"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[4],"player2RedDamage5"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageRedR[5],"player2RedDamage6"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[0],"player2BlueDamage1"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[1],"player2BlueDamage2"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[2],"player2BlueDamage3"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[3],"player2BlueDamage4"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[4],"player2BlueDamage5"));
        bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageBlueR[5],"player2BlueDamage6"));


        if(numPlayers>=3){

            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight, Assets.yellowSelectorL,"player1BtnYellow"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[0],"player1YellowDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[1],"player1YellowDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[2],"player1YellowDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[3],"player1YellowDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[4],"player1YellowDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageYellowL[5],"player1YellowDamage6"));

            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight, Assets.yellowSelectorR,"player2BtnYellow"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[0],"player2YellowDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[1],"player2YellowDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[2],"player2YellowDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[3],"player2YellowDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[4],"player2YellowDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageYellowR[5],"player2YellowDamage6"));

            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight, Assets.redSelectorL,"player3BtnRed"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight, Assets.blueSelectorL,"player3BtnBlue"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight, Assets.yellowSelectorL,"player3BtnYellow"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight, Assets.paintgun3,"player3"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[0],"player3RedDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[1],"player3RedDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[2],"player3RedDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[3],"player3RedDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[4],"player3RedDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageRedL[5],"player3RedDamage6"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[0],"player3BlueDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[1],"player3BlueDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[2],"player3BlueDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[3],"player3BlueDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[4],"player3BlueDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageBlueL[5],"player3BlueDamage6"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[0],"player3YellowDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[1],"player3YellowDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[2],"player3YellowDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[3],"player3YellowDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[4],"player3YellowDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageYellowL[5],"player3YellowDamage6"));


            player3 = game.getPlayerList().get(2).getData();

        }

        if(numPlayers == 4){
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight, Assets.greenSelectorL,"player1BtnGreen"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[0],"player1GreenDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[1],"player1GreenDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[2],"player1GreenDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[3],"player1GreenDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[4],"player1GreenDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player1X,player1Y,playerWidth,playerHeight,Assets.damageGreenL[5],"player1GreenDamage6"));

            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight, Assets.greenSelectorR,"player2BtnGreen"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[0],"player2GreenDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[1],"player2GreenDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[2],"player2GreenDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[3],"player2GreenDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[4],"player2GreenDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player2X,player2Y,playerWidth,playerHeight,Assets.damageGreenR[5],"player2GreenDamage6"));

            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight, Assets.greenSelectorL,"player3BtnGreen"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[0],"player3GreenDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[1],"player3GreenDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[2],"player3GreenDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[3],"player3GreenDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[4],"player3GreenDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player3X,player3Y,playerWidth,playerHeight,Assets.damageGreenL[5],"player3GreenDamage6"));

            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight, Assets.redSelectorR,"player4BtnRed"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight, Assets.blueSelectorR,"player4BtnBlue"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight, Assets.yellowSelectorR,"player4BtnYellow"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight, Assets.greenSelectorR,"player4BtnGreen"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight, Assets.paintgun4,"player4"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[0],"player4RedDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[1],"player4RedDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[2],"player4RedDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[3],"player4RedDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[4],"player4RedDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageRedR[5],"player4RedDamage6"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[0],"player4BlueDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[1],"player4BlueDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[2],"player4BlueDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[3],"player4BlueDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[4],"player4BlueDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageBlueR[5],"player4BlueDamage6"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[0],"player4YellowDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[1],"player4YellowDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[2],"player4YellowDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[3],"player4YellowDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[4],"player4YellowDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageYellowR[5],"player4YellowDamage6"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[0],"player4GreenDamage1"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[1],"player4GreenDamage2"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[2],"player4GreenDamage3"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[3],"player4GreenDamage4"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[4],"player4GreenDamage5"));
            bustedPaintBallUI.addObject(new UIImage(player4X,player4Y,playerWidth,playerHeight,Assets.damageGreenR[5],"player4GreenDamage6"));

            player4 = game.getPlayerList().get(3).getData();
        }

    }

    /**
     * mini game tick method, this runs the logic of the game
     */
    @Override
    public void tick() {
        gameSetup();
        handler.getMouseManager().setUiManager(bustedPaintBallUI);
        bustedPaintBallUI.tick();
        if(gameStart&&!gameWon) {
            ticks += 1;
            if (ticks == 200) {
                ticks = 0;
            }
            randomSelector();
            if (handler.getKeyManager().space) {
                timer += System.currentTimeMillis() - lastTime;
                lastTime = System.currentTimeMillis();
                if (timer > coolDown) {
                    shotColor.add(checkShooter());
                    if (currentPlayer == 1) {
                        dealDamage();
                    } else if (currentPlayer == 2) {
                        dealDamage();
                    } else if (currentPlayer == 3) {
                        dealDamage();
                    } else {
                        dealDamage();
                    }
                    nextPlayer();
                }
                checkKnockouts();
            }
            if (numPlayers == 2) {
                chooseWinner(knockOutP1, knockOutP2);
            }
            if (numPlayers == 3) {
                chooseWinner(knockOutP1, knockOutP2, knockOutP3);
            }
            if (numPlayers == 4) {
                chooseWinner(knockOutP1, knockOutP2, knockOutP3, knockOutP4);
            }
        }
    }

    /**
     * Mini game render method, this renders the mini game's graphics
     * @param g graphics parameter passed to game loop
     */
    @Override
    public void render(Graphics g) {
        bustedPaintBallUI.renderById(g,"backGround");
        g.setColor(Color.white);
        g.setFont(font);

        bustedPaintBallUI.renderById(g,"gameTitle");

        g.drawString("HP left: "+player1HP,400,600);
        g.drawString(player1.getName(),200,700);
        bustedPaintBallUI.renderById(g, "player1");

        g.drawString("HP left: "+player2HP,900,600);
        g.drawString(player2.getName(),1200,700);
        bustedPaintBallUI.renderById(g, "player2");

        if (numPlayers >= 3) {
            g.drawString("HP left: "+player3HP,400,850);
            g.drawString(player3.getName(),200,950);
            bustedPaintBallUI.renderById(g, "player3");
        }
        if (numPlayers == 4) {
            g.drawString("HP left: "+player4HP,900,850);
            g.drawString(player4.getName(),1200,950);
            bustedPaintBallUI.renderById(g, "player4");
        }

        if(!gameStart){
            bustedPaintBallUI.renderById(g,"backDrop");
            bustedPaintBallUI.renderById(g,"okBtn");
            g.setColor(Color.black);
            g.drawString("Shoot your opponents",510,450);
            g.drawString("using the space bar",510,500);
            g.setColor(Color.red);
            g.drawString("be careful",510,550);
            g.setColor(Color.black);
            g.drawString("dont shoot yourself!",510,600);
        }
        if(!gameWon) {
            renderTargetColor(g);
        }
        renderDamage(g,player1HP,"player1");
        renderDamage(g,player2HP, "player2");
        if(numPlayers>=3) {
            renderDamage(g, player3HP, "player3");
        }
        if(numPlayers==4) {
            renderDamage(g, player4HP, "player4");
        }

        if(gameWon){
            g.drawString("Congratulations!",600,400);
            g.drawString(winner,600,450);
            g.drawString("you win!",600,500);
            bustedPaintBallUI.renderById(g,"endGameOkBtn");
        }
    }

    /**
     * This is a method that renders how much damage a player has taken in the form of paint painting its character
     * it assembles a string to determine which asset to use
     * @param g graphics parameter passed to game loop
     * @param playerHP int value corresponding to each players hp, this is to render damage to each specific player
     * @param target String value corresponding to the target was shot
     */
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

    /**
     * Method used by render damage, it renders each specific shot
     * @param g graphics parameter passed to game loop
     * @param playerHP int value corresponding to each players hp, this is to render damage to each specific player
     * @param target String value corresponding to the target was shot
     * @param shooter String value corresponding to the shooter who shot the last paint ball
     */
    private void renderShot(Graphics g,int playerHP,String target, String shooter){
        renderDamageColor(g,target,playerHP,shooter);
    }

    /**
     * checks the current player variable and returns a string corresponding on who the last shooter was
     * @return String value to assemble the render id
     */
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

    /**
     * this parameter returns the damage color based on the current player
     * @param g graphics parameter passed to game loop
     */
    private void renderTargetColor(Graphics g){
        switch (currentPlayer){
            case 1 -> renderTarget(g,"Red");
            case 2 -> renderTarget(g,"Blue");
            case 3 -> renderTarget(g,"Yellow");
            case 4 -> renderTarget(g,"Green");
        }
    }

    /**
     *This is a method used to render the target of the player who is currently shooting, it assembles a string based on substrings
     * @param g graphics parameter passed to game loop
     * @param color String parameter passed by another method
     */
    private void renderTarget(Graphics g, String color){
        switch (target){
            case 1 -> bustedPaintBallUI.renderById(g,"player1Btn"+color);
            case 2 -> bustedPaintBallUI.renderById(g,"player2Btn"+color);
            case 3 -> bustedPaintBallUI.renderById(g,"player3Btn"+color);
            case 4 -> bustedPaintBallUI.renderById(g,"player4Btn"+color);
        }
    }

    /**
     * @param g graphics parameter passed to game loop
     * @param target String value of who is getting the damage
     * @param hp int value on the number of shot
     * @param shooter String value of who fired the shot
     */
    private void renderDamageColor(Graphics g, String target, int hp, String shooter) {
        renderDamageStage(g, target +shooter, hp);
    }

    /**
     * This class is the one that finally assembles the entire string and renders the color
     * @param g graphics parameter passed to game loop
     * @param targetAndColor assembled string from previous method
     * @param hp int value on the number of shot
     */
    private void renderDamageStage(Graphics g, String targetAndColor,int hp){
        if (hp==5){
            bustedPaintBallUI.renderById(g, targetAndColor + "Damage1");
        }
        if(hp==4) {
            bustedPaintBallUI.renderById(g, targetAndColor + "Damage2");
        }
        if(hp==3) {
            bustedPaintBallUI.renderById(g,targetAndColor+"Damage3");
        }
        if(hp==2) {
            bustedPaintBallUI.renderById(g,targetAndColor+"Damage4");
        }
        if(hp==1) {
            bustedPaintBallUI.renderById(g, targetAndColor + "Damage5");
        }
        if(hp==0) {
            bustedPaintBallUI.renderById(g, targetAndColor+"Damage6");
        }
    }

    /**
     * this method picks a random target every 25 ticks
     */
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

    /**
     * this method sets the players to knockOut if their hp reaches 0
     */
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

    /**
     * this method cycles between players, it also increased the speed of the target
     * selector every time someone is shot by using another method
     */
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

    /**
     * this method simply increases the speed
     */
    private void increaseSpeed(){
        if(speed>10){
            speed-=5;
        }
    }

    /**
     * this method recognises who was shot, and reduces their remaining hp
     */
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

    /**
     * this method is in charge of reducing hp in general, but never bellow 0
     * @param playerHP of the player who is shot
     * @return a number 1 less than the number that get it, but never bellow 0
     */
    private int loseHP(int playerHP){
        if(playerHP!=0){
            playerHP--;
        }
        return playerHP;
    }

    /**
     * This method checks if only 1 remaining player is not knockout, this is the method for a 4 player game
     * @param knockOutP1 boolean of the first player
     * @param knockOutP2 boolean of the second player
     * @param knockOutP3 boolean of the third player
     * @param knockOutP4 boolean of the fourth player
     */
    private void chooseWinner(boolean knockOutP1, boolean knockOutP2, boolean knockOutP3, boolean knockOutP4){
        if(knockOutP1&&knockOutP2&&knockOutP3&&!knockOutP4){
            winGame(player4);
            winner = player4.getName();
        } else if(knockOutP1&&knockOutP2&&!knockOutP3&&knockOutP4){
            winGame(player3);
            winner = player3.getName();
        } else if(knockOutP1&&!knockOutP2&&knockOutP3&&knockOutP4){
            winGame(player2);
            winner = player2.getName();
        } else if(!knockOutP1&&knockOutP2&&knockOutP3&&knockOutP4){
            winGame(player1);
            winner = player1.getName();
        }
    }

    /**
     * This method checks if only 1 remaining player is not knockout, this is the method for a 3 player game
     * @param knockOutP1 boolean of the first player
     * @param knockOutP2 boolean of the second player
     * @param knockOutP3 boolean of the third player
     */
    private void chooseWinner(boolean knockOutP1, boolean knockOutP2, boolean knockOutP3){
        if(knockOutP1&&knockOutP2&&!knockOutP3){
            winGame(player3);
            winner = player3.getName();
        } else if(knockOutP1&&!knockOutP2&&knockOutP3){
            winGame(player2);
            winner = player2.getName();
        } else if(!knockOutP1&&knockOutP2&&knockOutP3){
            winGame(player1);
            winner = player1.getName();
        }
    }

    /**
     * This method checks if only 1 remaining player is not knockout, this is the method for a 3 player game
     * @param knockOutP1 boolean of the first player
     * @param knockOutP2 boolean of the second player
     */
    private void chooseWinner(boolean knockOutP1, boolean knockOutP2){
        if(knockOutP1&&!knockOutP2){
            winGame(player2);
            winner = player2.getName();
        } else if(!knockOutP1&&knockOutP2){
            winGame(player1);
            winner = player1.getName();
        }
    }

    /**
     * this method declares a winner, and adds coins to the corresponding winner
     * @param player the player who won the game
     */
    private void winGame(Player player) {
        if(!gameWon) {
            player.addCoins(10);
            gameWon = true;
        }
    }

    /**
     * this method returns to the main board game
     */
    private void backToBoard() {
        setup = false;
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

    /**
     * this method is run a single time when the mini game is called, it prepares all variables to their default state
     * like resetting players hp, and change all knockouts to false
     */
    private void gameSetup(){
        if(!setup){
            setup = true;
            gameWon = false;
            gameStart = false;
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
