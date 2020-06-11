package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
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
    private final int numPlayers;
    private boolean knockOutP1 = false, knockOutP2 = false, knockOutP3 = false, knockOutP4 = false;


    public FifthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.game = game;
        faultyPaintball = new UIManager(handler);

        this.coolDown = 500;
        lastTime = System.currentTimeMillis();

        speed = 25;

        this.numPlayers = numPlayers;
        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        player1HP = 6;
        player2HP = 6;

        faultyPaintball.addObject(new UIImage(1, 1, 8, 8*2, Assets.redSelectorR,"player1BtnRed"));
        faultyPaintball.addObject(new UIImage(1, 1, 8, 8*2, Assets.blueSelectorR,"player1BtnBlue"));
        faultyPaintball.addObject(new UIImage(1, 1, 8, 8*2, Assets.paintgun1,"player1"));

        faultyPaintball.addObject(new UIImage(30, 1, 8, 8*2, Assets.redSelectorL,"player2BtnRed"));
        faultyPaintball.addObject(new UIImage(30, 1, 8, 8*2, Assets.blueSelectorL,"player2BtnBlue"));
        faultyPaintball.addObject(new UIImage(30, 1, 8, 8*2, Assets.paintgun2,"player2"));


        if(numPlayers>=3){
            faultyPaintball.addObject(new UIImage(1, 1, 8, 8*2, Assets.yellowSelectorR,"player1BtnYellow"));
            faultyPaintball.addObject(new UIImage(30, 1, 8, 8*2, Assets.yellowSelectorL,"player2BtnYellow"));

            faultyPaintball.addObject(new UIImage(1, 30, 8, 8*2, Assets.redSelectorR,"player3BtnRed"));
            faultyPaintball.addObject(new UIImage(1, 30, 8, 8*2, Assets.blueSelectorR,"player3BtnBlue"));
            faultyPaintball.addObject(new UIImage(1, 30, 8, 8*2, Assets.yellowSelectorR,"player3BtnYellow"));

            faultyPaintball.addObject(new UIImage(1, 30, 8, 8*2, Assets.paintgun3,"player3"));

            player3 = game.getPlayerList().get(2).getData();
            player3HP = 6;

        }

        if(numPlayers == 4){
            faultyPaintball.addObject(new UIImage(1, 1, 8, 8*2, Assets.greenSelectorR,"player1BtnGreen"));
            faultyPaintball.addObject(new UIImage(30, 1, 8, 8*2, Assets.greenSelectorL,"player2BtnGreen"));
            faultyPaintball.addObject(new UIImage(1, 30, 8, 8*2, Assets.greenSelectorR,"player3BtnGreen"));

            faultyPaintball.addObject(new UIImage(30, 30, 8, 8*2, Assets.redSelectorL,"player4BtnRed"));
            faultyPaintball.addObject(new UIImage(30, 30, 8, 8*2, Assets.blueSelectorL,"player4BtnBlue"));
            faultyPaintball.addObject(new UIImage(30, 30, 8, 8*2, Assets.yellowSelectorL,"player4BtnYellow"));
            faultyPaintball.addObject(new UIImage(30, 30, 8, 8*2, Assets.greenSelectorL,"player4BtnGreen"));
            faultyPaintball.addObject(new UIImage(30, 30, 8, 8*2, Assets.paintgun4,"player4"));

            player4 = game.getPlayerList().get(3).getData();
            player4HP = 6;
        }

    }

    @Override
    public void tick() {
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
 //               speed -= 100;
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
        target = 0;
        knockOutP1 = false;
        knockOutP2 = false;
        knockOutP3 = false;
        knockOutP4 = false;
        player1HP = 6;
        player2HP = 6;
        if(numPlayers>=3){
            player3HP = 6;
        }
        if (numPlayers==4){
            player4HP = 6;
        }
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

}
