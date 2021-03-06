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

/**
 * this class is the clicker artist mini game, that plays during regular gameplay
 */
public class ClickerArtistState extends State {
    private final UIManager clickerArtistUI;
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
    private final Color springGreen;

    /**
     * Constructor for the clicker artist state
     * @param handler handler obj, that is used to retrieve key listener
     * @param numPlayers number of players used as a conditional in case its less than 4 players
     * @param game the game obj in order to have access to all variables and methods from the game
     */
    public ClickerArtistState(Handler handler, int numPlayers, Game game) {
        super(handler);
        clickerArtistUI = new UIManager(handler);
        this.game = game;
        this.numPlayers = numPlayers;

        springGreen = new Color(33,250,144);

        font = Assets.astalemtim.deriveFont(Font.BOLD,50);

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        this.speed = 1000;

        Animation dustAnimation = new Animation(200,Assets.dustAnimation);

        clickerArtistUI.addObject(new UIBackground(Assets.clickerBG,"backGround"));
        int width = GameLauncher.width / 16;
        int height = GameLauncher.height / 16;
        clickerArtistUI.addObject(new UIImage((float) width /2-20,(float) height /2-15,4*10,2*10,Assets.starPurchaseBackDrop[0],"backDrop"));
        clickerArtistUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn, "okBtn",()-> gameStart = true));

        clickerArtistUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"continueOkBtn", () ->{
            if(timeLeft ==0&&gameStart) {
                timeLeft = 10;
                currentPlayer+=1;
            }
        }));

        clickerArtistUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"endGameOkBtn", () ->{
            if(gameWon) {
                backToBoard();
            }
        }));
        clickerArtistUI.addObject(new UIAnimatedImage((float) width /2-12,(float) height /2-16,3*8,4*8,dustAnimation,"dustAnimation"));
        clickerArtistUI.addObject(new UIImageButton((float) width /2-12,(float) height /2-16,3*8,4*8,Assets.stoneButton,"rockBtn",()->{
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
        clickerArtistUI.addObject(new UIImage((float) width /2-48,(float) height /2-32,5*8,2*8,Assets.stoneLogo,"gameTitle"));
        clickerArtistUI.addObject(new UIImage((float) width /2-12,(float) height /2-16,3*8,4*8,Assets.stoneStatue,"stoneStatue"));
        clickerArtistUI.addObject(new UIImage((float) width /2-36,(float) height /2-16,3*8,4*8,Assets.stoneRubble,"rubble1"));
        clickerArtistUI.addObject(new UIImage((float) width /2+12,(float) height /2-16,3*8,4*8,Assets.stoneRubble,"rubble2"));
        clickerArtistUI.addObject(new UIImage((float) width /2-12,(float) height /2-44,3*8,4*8,Assets.stoneRubble,"rubble3"));


        if(numPlayers>=3){
            player3 = game.getPlayerList().get(2).getData();
            score3 = 0;
        }

        if(numPlayers == 4){
            player4 = game.getPlayerList().get(3).getData();
            score4 = 0;
        }

    }

    /**
     * mini game tick method, this runs the logic of the game
     */
    @Override
    public void tick() {
        gameSetup();
        handler.getMouseManager().setUiManager(clickerArtistUI);
        clickerArtistUI.tick();
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

    /**
     * Mini game render method, this renders the mini game's graphics
     * @param g graphics parameter passed to game loop
     */
    @Override
    public void render(Graphics g) {
        clickerArtistUI.renderById(g,"backGround");
        g.setFont(font);
        g.setColor(Color.white);
        clickerArtistUI.renderById(g,"gameTitle");
        if(!gameStart){
            clickerArtistUI.renderById(g,"backDrop");
            g.drawString("click on the rock",600,500);
            g.drawString("as fast as you can",600,550);
            g.drawString("you have 10 seconds!",600,600);
            clickerArtistUI.renderById(g,"okBtn");
            g.drawString(player1.getName()+" goes first!",600, 300);
        }

        if(timeLeft !=0&&gameStart&&!gameWon){
            clickerArtistUI.renderById(g,"rockBtn");
            clickerArtistUI.renderById(g,"dustAnimation");
            g.setColor(Color.red);
            g.drawString("time left: "+ timeLeft,60,900);
            g.setColor(Color.white);


        }

        if(timeLeft ==0){
            clickerArtistUI.renderById(g,"continueOkBtn");
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
            g.setColor(springGreen);
            clickerArtistUI.renderById(g,"stoneStatue");
            g.drawString("Congratulations",600,500);
            g.drawString(winner,600,600);
            g.drawString("you win!",600,700);
            clickerArtistUI.renderById(g,"endGameOkBtn");
            clickerArtistUI.renderById(g,"rubble1");
            g.drawString(loser1,300,800);

            if(numPlayers >= 3){
                clickerArtistUI.renderById(g,"rubble2");
                g.drawString(loser2,1100,800);
            }
            if(numPlayers ==4){
                clickerArtistUI.renderById(g,"rubble3");
                g.drawString(loser3,700,300);
            }
        }
    }

    /**
     * This method compares scores for 4 players and declares the highest as winner
     * @param score1 int value of the first player's score
     * @param score2 int value of the second player's score
     * @param score3 int value of the first player's score
     * @param score4 int value of the first player's score
     */
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

    /**
     * This method compares scores for 4 players and declares the highest as winner
     * @param score1 int value of the first player's score
     * @param score2 int value of the second player's score
     * @param score3 int value of the first player's score
     */
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

    /**
     * This method compares scores for 4 players and declares the highest as winner
     * @param score1 int value of the first player's score
     * @param score2 int value of the second player's score
     */
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

    /**
     * This method compares 4 numbers and returns the highest
     * @param a first int value
     * @param b second int value
     * @param c third int value
     * @param d fourth int value
     * @return a number corresponding to which was higher
     */
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

    /**
     * This method compares 3 numbers and returns the highest
     * @param a first int value
     * @param b second int value
     * @param c third int value
     * @return a number corresponding to which was higher
     */
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

    /**
     * This method compares 2 numbers and returns the highest
     * @param a first int value
     * @param b second int value
     * @return a number corresponding to which was higher
     */
    private int checkHighest(int a, int b){
        int max = Math.max(a,b);
        if(max == a){
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * sets a player as a winner and gives a prize
     * @param player player obj of the player who won
     */
    private void winGame(Player player){
        if(!gameWon) {
            player.addCoins(10);
            gameWon = true;
        }
    }

    /**
     * method that returns the game from the board
     */
    private void backToBoard(){
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


