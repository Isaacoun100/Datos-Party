package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.*;

import java.awt.*;

public class SixthMinigameState extends State {
    private final UIManager uiManger;
    private final Player player1;
    private final Player player2;
    private Player player3;
    private Player player4;
    private long lastTime, timer;
    private final int speed;
    private int timeLeft;
    private final int numPlayers;
    private int score1, score2, score3, score4;
    private int currentPlayer = 1;
    private final Game game;
    private boolean gameStart = false;
    private final Font font;
    private boolean setup = false, gameWon = false;
    private String winner, loser1, loser2, loser3;

    public SixthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManger = new UIManager(handler);
        this.game = game;
        this.numPlayers = numPlayers;

        font = Assets.astalemtim.deriveFont(Font.PLAIN,50);

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        this.speed = 1000;

        Animation dustAnimation = new Animation(200,Assets.dustAnimation);

        uiManger.addObject(new UIBackground(Assets.clickerBG,"backGround"));
        int width = GameLauncher.width / 16;
        int height = GameLauncher.height / 16;
        uiManger.addObject(new UIImage((float) width /2-20,(float) height /2-15,4*10,2*10,Assets.starPurchaseBackDrop[0],"backDrop"));
        uiManger.addObject(new UIImageButton(46,50,8,8,Assets.okBtn, "okBtn",()->{
            gameStart = true;
        }));

        uiManger.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"continueOkBtn", () ->{
            if(timeLeft ==0&&gameStart) {
                timeLeft = 10;
                currentPlayer+=1;
            }
        }));

        uiManger.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"endGameOkBtn", () ->{
            if(gameWon) {
                backToBoard();
            }
        }));
        uiManger.addObject(new UIAnimatedImage((float) width /2-12,(float) height /2-16,3*8,4*8,dustAnimation,"dustAnimation"));
        uiManger.addObject(new UIImageButton((float) width /2-12,(float) height /2-16,3*8,4*8,Assets.stoneButton,"rockBtn",()->{
            if(timeLeft !=0){
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
        uiManger.addObject(new UIImage((float) width /2-48,(float) height /2-32,5*8,2*8,Assets.stoneLogo,"gameTitle"));
        uiManger.addObject(new UIImage((float) width /2-12,(float) height /2-16,3*8,4*8,Assets.stoneStatue,"stoneStatue"));
        uiManger.addObject(new UIImage((float) width /2-36,(float) height /2-16,3*8,4*8,Assets.stoneRubble,"rubble1"));
        uiManger.addObject(new UIImage((float) width /2+12,(float) height /2-16,3*8,4*8,Assets.stoneRubble,"rubble2"));
        uiManger.addObject(new UIImage((float) width /2-12,(float) height /2-44,3*8,4*8,Assets.stoneRubble,"rubble3"));


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
        gameSetup();
        handler.getMouseManager().setUiManager(uiManger);
        uiManger.tick();
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed) {
            if(timeLeft !=0&&gameStart) {
                timeLeft--;
                timer = 0;
            }
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
        uiManger.renderById(g,"backGround");
        g.setFont(font);
        g.setColor(Color.white);
        uiManger.renderById(g,"gameTitle");
        if(!gameStart){
            uiManger.renderById(g,"backDrop");
            g.drawString("click on the rock",600,500);
            g.drawString("as fast as you can",600,550);
            g.drawString("you have 10 seconds!",600,600);
            uiManger.renderById(g,"okBtn");
            g.drawString(player1.getName()+" goes first!",600, 300);
        }

        if(timeLeft !=0&&gameStart&&!gameWon){
            uiManger.renderById(g,"rockBtn");
            uiManger.renderById(g,"dustAnimation");
            g.setColor(Color.red);
            g.drawString("time left: "+ timeLeft,60,900);
            g.setColor(Color.white);


        }

        if(timeLeft ==0){
            uiManger.renderById(g,"continueOkBtn");
        }

        if(gameStart&& timeLeft ==0){
            if(currentPlayer==1){
                g.drawString("your score was : "+score1,1000,600);
                g.drawString(player2.getName()+" is next!",600, 300);

            } else if(currentPlayer==2){
                g.drawString("your score was : "+score2,1000,600);
                if(numPlayers>=3){
                    g.drawString(player3.getName()+" is next!",600, 300);
                }
            } else if(currentPlayer==3&&numPlayers>=3){
                g.drawString("your score was : "+score3, 1000, 600);
                if(numPlayers==4){
                    g.drawString(player4.getName()+" is next!",600, 300);
                }
            } else if(currentPlayer==4&&numPlayers==4){
                g.drawString("your score was : "+score4, 1000, 600);
            }


            if (currentPlayer>=1){
                g.drawString(player1.getName()+": "+score1,1100,100);
            }
            if (currentPlayer>=2){
                g.drawString(player2.getName()+": "+score2,1100,150);
            }
            if (currentPlayer>=3&&numPlayers>=3){
                g.drawString(player3.getName()+": "+score3,1100,200);
            }
            if(currentPlayer>=4){
                g.drawString(player4.getName()+": "+score4, 1100, 250);
            }
        }
        if(gameWon){
            g.setColor(Color.blue);
            uiManger.renderById(g,"stoneStatue");
            g.drawString("Congratulations",600,500);
            g.drawString(winner,600,600);
            g.drawString("you win!",600,700);
            uiManger.renderById(g,"endGameOkBtn");
            uiManger.renderById(g,"rubble1");
            g.drawString(loser1,300,800);

            if(numPlayers >= 3){
                uiManger.renderById(g,"rubble2");
                g.drawString(loser2,1100,800);
            }
            if(numPlayers ==4){
                uiManger.renderById(g,"rubble3");
                g.drawString(loser3,700,300);
            }
        }
    }

    private void checkWinner(int score1, int score2, int score3, int score4){
        switch (checkHighest(score1, score2, score3, score4)) {
            case 1 -> {winGame(player1);
                winner = player1.getName();
                loser1 = player2.getName();
                loser2 = player3.getName();
                loser3 = player4.getName();}
            case 2 -> {winGame(player2);
                winner = player2.getName();
                loser1 = player1.getName();
                loser2 = player3.getName();
                loser3 = player4.getName();}
            case 3 -> {winGame(player3);
                winner = player3.getName();
                loser1 = player1.getName();
                loser2 = player2.getName();
                loser3 = player4.getName();}
            case 4 -> {winGame(player4);
            winner = player4.getName();
            loser1 = player1.getName();
            loser2 = player2.getName();
            loser3 = player3.getName();
            }
        }
    }

    private void checkWinner(int score1, int score2, int score3){
        switch (checkHighest(score1, score2, score3)) {
            case 1 -> {winGame(player1);
                winner = player1.getName();
                loser1 = player2.getName();
                loser2 = player3.getName();}
            case 2 -> {winGame(player2);
                winner = player2.getName();
                loser1 = player1.getName();
                loser2 = player3.getName();}
            case 3 -> {winGame(player3);
                winner = player3.getName();
                loser1 = player1.getName();
                loser2 = player2.getName();}
        }
    }

    private void checkWinner(int score1, int score2){
        switch (checkHighest(score1, score2)) {
            case 1 -> {winGame(player1);
                winner = player1.getName();
                loser1 = player2.getName();}
            case 2 -> {winGame(player2);
                winner = player2.getName();
                loser1 = player1.getName();}
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
        player.addCoins(10);
        gameWon = true;
    }

    private void backToBoard(){
        setup = false;
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

    private void gameSetup(){
        if(!setup){
            gameWon = false;
            setup = true;
            gameStart = false;
            currentPlayer = 1;
            timeLeft = 10;
            score1 = 0;
            score2 = 0;
            score3 = 0;
            score4 = 0;
            winner = "";
            loser1 = "";
            loser2 = "";
            loser3 = "";
        }
    }

}


