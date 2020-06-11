package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FifthMinigameState extends State {

    UIManager miniRPGUI;
    private Player player1;
    private  Player player2;
    private Player player3;
    private Player player4;
    private  int player1HP;
    private  int player2HP;
    private int player3HP;
    private int player4HP;
    private Game game;
    private long lastTime, timer;
    private int speed,ticks, currentPlayer, playerQty = 2;
    private boolean knockOutP1 = false, knockOutP2 = false, knockOutP3 = false, knockOutP4 = false;


    public FifthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        currentPlayer = 1;
        this.game = game;
        miniRPGUI = new UIManager(handler);

        this.speed = 1000;
        lastTime = System.currentTimeMillis();


        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        player1HP = 6;
        player2HP = 6;

        miniRPGUI.addObject(new UIImage(1, 1, 8, 8*2, Assets.redSelectorR,"player1BtnRed"));
        miniRPGUI.addObject(new UIImage(1, 1, 8, 8*2, Assets.blueSelectorR,"player1BtnBlue"));
        miniRPGUI.addObject(new UIImage(1, 1, 8, 8*2, Assets.paintgun1,"player1"));

        miniRPGUI.addObject(new UIImage(30, 1, 8, 8*2, Assets.redSelectorL,"player2BtnRed"));
        miniRPGUI.addObject(new UIImage(30, 1, 8, 8*2, Assets.blueSelectorL,"player2BtnBlue"));
        miniRPGUI.addObject(new UIImage(30, 1, 8, 8*2, Assets.paintgun2,"player2"));


        if(numPlayers>=3){
            playerQty = 3;
            miniRPGUI.addObject(new UIImage(1, 1, 8, 8*2, Assets.yellowSelectorR,"player1BtnYellow"));
            miniRPGUI.addObject(new UIImage(30, 1, 8, 8*2, Assets.yellowSelectorL,"player2BtnYellow"));


            miniRPGUI.addObject(new UIImage(1, 30, 8, 8*2, Assets.redSelectorR,"player3BtnRed"));
            miniRPGUI.addObject(new UIImage(1, 30, 8, 8*2, Assets.blueSelectorR,"player3BtnBlue"));
            miniRPGUI.addObject(new UIImage(1, 30, 8, 8*2, Assets.yellowSelectorR,"player3BtnYellow"));


            miniRPGUI.addObject(new UIImage(1, 30, 8, 8*2, Assets.paintgun3,"player3"));

            player3 = game.getPlayerList().get(2).getData();
            player3HP = 6;

        }

        if(numPlayers == 4){
            playerQty = 4;
            miniRPGUI.addObject(new UIImage(1, 1, 8, 8*2, Assets.greenSelectorR,"player1BtnGreen"));
            miniRPGUI.addObject(new UIImage(30, 1, 8, 8*2, Assets.greenSelectorL,"player2BtnGreen"));
            miniRPGUI.addObject(new UIImage(1, 30, 8, 8*2, Assets.greenSelectorR,"player3BtnGreen"));



            miniRPGUI.addObject(new UIImage(30, 30, 8, 8*2, Assets.redSelectorL,"player4BtnRed"));
            miniRPGUI.addObject(new UIImage(30, 30, 8, 8*2, Assets.blueSelectorL,"player4BtnBlue"));
            miniRPGUI.addObject(new UIImage(30, 30, 8, 8*2, Assets.yellowSelectorL,"player4BtnYellow"));
            miniRPGUI.addObject(new UIImage(30, 30, 8, 8*2, Assets.greenSelectorL,"player4BtGreen"));
            miniRPGUI.addObject(new UIImage(30, 30, 8, 8*2, Assets.paintgun4,"player4"));

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
        //System.out.println(ticks);
        //System.out.println("im here");
        handler.getMouseManager().setUiManager(miniRPGUI);
        miniRPGUI.tick();
        System.out.println("Vida jugador 1: "+player1HP);
        System.out.println("Vida jugador 2: "+player2HP);
        System.out.println("Vida jugador 3: "+player3HP);
        System.out.println("Vida jugador 4: "+player4HP);


        if(handler.getKeyManager().space){
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            if(timer > speed) {
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

                if(currentPlayer!=4) {
                    currentPlayer += 1;
                }else{
                    currentPlayer = 1;
                }
                timer = 0;
            }
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
        if(playerQty == 2){
            chooseWinner(knockOutP1,knockOutP2);
        }
        if(playerQty == 3){
            chooseWinner(knockOutP1,knockOutP2,knockOutP3);
        }
        if(playerQty == 4){
            chooseWinner(knockOutP1,knockOutP2,knockOutP3,knockOutP4);
        }
    }

    private void dealDamage() {
        if (ticks <= 50) {
            player1HP = loseHP(player1HP);
        }
        if (50 < ticks && ticks <= 100) {
            player2HP = loseHP(player2HP);
        }
        if (100 < ticks && ticks <= 150) {
            player3HP = loseHP(player3HP);
        }
        if (150 < ticks && ticks <= 200) {
            player4HP = loseHP(player4HP);
        }
    }

    @Override
    public void render(Graphics g) {

        miniRPGUI.renderById(g,"player1");
        miniRPGUI.renderById(g,"player2");
        miniRPGUI.renderById(g,"player3");
        miniRPGUI.renderById(g,"player4");



        if(currentPlayer==1) {
            if (ticks <= 50) {
                miniRPGUI.renderById(g, "player1BtnRed");
            }
            if (50 < ticks && ticks <= 100) {
                miniRPGUI.renderById(g, "player2BtnRed");
            }
            if (100 < ticks && ticks <= 150) {
                miniRPGUI.renderById(g, "player3BtnRed");
            }
            if (150 < ticks && ticks <= 200) {
                miniRPGUI.renderById(g, "player4BtnRed");
            }
        } else if( currentPlayer ==2){
            if (ticks <= 50) {
                miniRPGUI.renderById(g, "player1BtnBlue");
            }
            if (50 < ticks && ticks <= 100) {
                miniRPGUI.renderById(g, "player2BtnBlue");
            }
            if (100 < ticks && ticks <= 150) {
                miniRPGUI.renderById(g, "player3BtnBlue");
            }
            if (150 < ticks && ticks <= 200) {
                miniRPGUI.renderById(g, "player4BtnBlue");
            }
        } else if( currentPlayer == 3){
            if (ticks <= 50) {
                miniRPGUI.renderById(g, "player1BtnYellow");
            }
            if (50 < ticks && ticks <= 100) {
                miniRPGUI.renderById(g, "player2BtnYellow");
            }
            if (100 < ticks && ticks <= 150) {
                miniRPGUI.renderById(g, "player3BtnYellow");
            }
            if (150 < ticks && ticks <= 200) {
                miniRPGUI.renderById(g, "player4BtnYellow");
            }
        } else {
            if (ticks <= 50) {
                miniRPGUI.renderById(g, "player1BtnGreen");
            }
            if (50 < ticks && ticks <= 100) {
                miniRPGUI.renderById(g, "player2BtnGreen");
            }
            if (100 < ticks && ticks <= 150) {
                miniRPGUI.renderById(g, "player3BtnGreen");
            }
            if (150 < ticks && ticks <= 200) {
                miniRPGUI.renderById(g, "player4BtGreen");
            }
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
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

}
