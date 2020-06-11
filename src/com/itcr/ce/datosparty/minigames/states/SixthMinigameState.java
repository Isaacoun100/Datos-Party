package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class SixthMinigameState extends State {
    private final UIManager uiManger;
    private final Player player1;
    private final Player player2;
    private Player player3;
    private Player player4;
    private long lastTime, timer;
    private int speed,ticks,timeleft;
    private int numPlayers;
    private int score1, score2, score3, score4;
    private int currentPlayer = 1;
    private final Game game;
    private boolean gameStart = false;
    int metter = 0;

    public SixthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManger = new UIManager(handler);
        this.game = game;
        this.numPlayers = numPlayers;

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        score1 = 0;
        score2 = 0;

        this.speed = 1000;
        timeleft = 10;

        uiManger.addObject(new UIImageButton(30,30,7*2,2*2,Assets.player3Button, "player3Btn",()->{
            gameStart = true;
        }));

        uiManger.addObject(new UIImageButton(50,50,7*2,2*2,Assets.player1Button,"player1Btn", () ->{
            if(timeleft==0&&gameStart) {
                timeleft = 10;
                currentPlayer+=1;
            }
        }));

        uiManger.addObject(new UIImageButton(40,40,7*2,2*2,Assets.player2Button,"player2Btn",()->{
            if(timeleft!=0){
                if(currentPlayer==1) {
                    score1 += 1;
                }
                if(currentPlayer==2){
                    score2 += 1;
                }
                if (currentPlayer==3){
                    score3 += 1;
                }
                if(currentPlayer == 4){
                    score4 +=1;
                }
            }
        }));

        if(numPlayers>=3){
            player3 = game.getPlayerList().get(2).getData();
            score3 = 0;
        }

        if(numPlayers == 4){
            player4 = game.getPlayerList().get(3).getData();
            score4 = 0;
        }

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManger);
        uiManger.tick();
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed) {
            if(timeleft!=0&&gameStart) {
                timeleft--;
                timer = 0;
            }
        }
        if(timeleft==0){
            System.out.println("Puntaje del primer jugador: "+score1);
            System.out.println("puntaje del segundo jugador: "+score2);
            System.out.println("puntaje del tercer jugador: "+score3);
            System.out.println("puntaje del cuarto jugador: "+score4);
        }
        if(currentPlayer>numPlayers){
            if(numPlayers==4){
                checkWinner(score1,score2,score3,score4);
            }
            if(numPlayers==3){
                checkWinner(score1,score2,score3);
            }
            if(numPlayers==2){
                checkWinner(score1,score2);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawString("time left: "+timeleft,60,60);

        if(!gameStart){
            uiManger.renderById(g,"player3Btn");
        }

        if(timeleft!=0&&gameStart){
            uiManger.renderById(g,"player2Btn");
        }

        if(timeleft==0){
            uiManger.renderById(g,"player1Btn");
        }

        if(gameStart&&timeleft==0){
            if(currentPlayer==1){
                g.drawString("your score was : "+score1,1000,500);
            } else if(currentPlayer==2){
                g.drawString("your score was : "+score2,1000,500);
            } else if(currentPlayer==3&&numPlayers>=3){
                g.drawString("your score was : "+score3, 1000, 500);
            } else if(currentPlayer==4&&numPlayers==4){
                g.drawString("your score was : "+score4, 1000, 500);
            }

            if (currentPlayer>=1){
                g.drawString("Player 1: "+score1,1000,60);
            }
            if (currentPlayer>=2){
                g.drawString("Player 2: "+score2,1000,90);
            }
            if (currentPlayer>=3&&numPlayers>=3){
                g.drawString("Player 3: "+score3,1000,120);
            }
            if(currentPlayer==4){
                g.drawString("Player 4: "+score4, 1000, 150);
            }
        }

    }

    private void checkWinner(int score1, int score2, int score3, int score4){
        switch (checkHighest(score1, score2, score3, score4)) {
            case 1 -> winGame(player1);
            case 2 -> winGame(player2);
            case 3 -> winGame(player3);
            case 4 -> winGame(player4);
        }
    }

    private void checkWinner(int score1, int score2, int score3){
        switch (checkHighest(score1, score2, score3)) {
            case 1 -> winGame(player1);
            case 2 -> winGame(player2);
            case 3 -> winGame(player3);
        }
    }

    private void checkWinner(int score1, int score2){
        switch (checkHighest(score1, score2)) {
            case 1 -> winGame(player1);
            case 2 -> winGame(player2);
        }
    }

    private int checkHighest(int a, int b, int c, int d){
         int max = Math.max(Math.max(a,b),Math.max(c,d));
        if(max == a){
            return 1;
        }else if(max == b){
            return 2;
        }else if(max == c){
            return 3;
        }else {
            return 4;
        }
    }

    private int checkHighest(int a, int b, int c){
        int tempMax = Math.max(Math.max(a,b),Math.max(a,c));
        int max = Math.max(Math.max(b,c),tempMax);
        if(max == a){
            return 1;
        }else if(max == b){
            return 2;
        }else{
            return 3;
        }
    }

    private int checkHighest(int a, int b){
        int max = Math.max(a,b);
        if(max == a){
            return 1;
        } else {
            return 2;
        }
    }

    private void winGame(Player player){
        score1 = 0;
        score2 = 0;
        score3 = 0;
        score4 = 0;
        currentPlayer = 1;
        gameStart = false;
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());

    }

}


