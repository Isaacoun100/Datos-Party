package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

/**
 * this class is the mini game used when two players face each other in a duel
 */
public class DuelMiniGame extends State {
    private final UIManager duelUI;
    public static Player leftPlayer, rightPlayer;
    private final Game game;
    private boolean gameSetup = false, gameWon = false, gameStart = false, tie = false;
    private String leftPlayerAnswer, rightPlayerAnswer, winner;
    private int currentPlayer = 0;
    private final Font font;
    private long lastTime, timer;
    private final int coolDown;

    /**
     * Constructor for the clicker artist state
     * @param handler handler obj, that is used to retrieve key listener
     * @param numPlayers number of players used as a conditional in case its less than 4 players
     * @param game the game obj in order to have access to all variables and methods from the game
     */
    public DuelMiniGame(Handler handler, int numPlayers, Game game) {

        super(handler);
        this.game = game;
        duelUI = new UIManager(handler);
        int width = GameLauncher.width / 16;
        int height = GameLauncher.height / 16;
        coolDown = 150;


        font = Assets.bitArtFont.deriveFont(Font.PLAIN, 50);

        duelUI.addObject(new UIImage(30,2,5*8,3*8, Assets.rpcTitle,"gameTitle"));

        duelUI.addObject(new UIImage(10,20,16,8*4, Assets.rock,"rockL"));
        duelUI.addObject(new UIImage(10,20,16,8*4, Assets.paper,"paperL"));
        duelUI.addObject(new UIImage(10,20,16,8*4, Assets.scissors,"scissorsL"));

        duelUI.addObject(new UIImage(30,50,8,4*4, Assets.leftPlayer,"leftPlayer"));
        duelUI.addObject(new UIImage(30,48,8,8, Assets.playerIndicator1,"playerIndicator1L"));
        duelUI.addObject(new UIImage(30,48,8,8, Assets.playerIndicator2,"playerIndicator2L"));
        duelUI.addObject(new UIImage(30,48,8,8, Assets.playerIndicator3,"playerIndicator3L"));
        duelUI.addObject(new UIImage(30,48,8,8, Assets.playerIndicator4,"playerIndicator4L"));

        duelUI.addObject(new UIImage(80,20,16,8*4, Assets.rock,"rockR"));
        duelUI.addObject(new UIImage(80,20,16,8*4, Assets.paper,"paperR"));
        duelUI.addObject(new UIImage(80,20,16,8*4, Assets.scissors,"scissorsR"));

        duelUI.addObject(new UIImage(60,50,8,4*4, Assets.rightPlayer,"rightPlayer"));
        duelUI.addObject(new UIImage(60,48,8,8, Assets.playerIndicator1,"playerIndicator1R"));
        duelUI.addObject(new UIImage(60,48,8,8, Assets.playerIndicator2,"playerIndicator2R"));
        duelUI.addObject(new UIImage(60,48,8,8, Assets.playerIndicator3,"playerIndicator3R"));
        duelUI.addObject(new UIImage(60,48,8,8, Assets.playerIndicator4,"playerIndicator4R"));

        duelUI.addObject(new UIImage(35,25,8,4*4, Assets.rock,"rockI"));
        duelUI.addObject(new UIImage(45,25,8,4*4, Assets.paper,"paperI"));
        duelUI.addObject(new UIImage(55,25,8,4*4, Assets.scissors,"scissorsI"));

        duelUI.addObject(new UIImage(35,38,8,8, Assets.number1,"number1"));
        duelUI.addObject(new UIImage(45,38,8,8, Assets.number2,"number2"));
        duelUI.addObject(new UIImage(55,38,8,8, Assets.number3,"number3"));

        duelUI.addObject(new UIImage((float) width/2-20,(float) height /2-15,4*8,2*10,Assets.starPurchaseBackDrop[0],"backdrop"));

        duelUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"okBtnTie",()->{
                if(tie){
                    tie=false;
                    currentPlayer = 1;
                }}));

        duelUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"okBtnBegin",()->{
            if(!gameStart) {
                gameStart = true;
                currentPlayer = 1;
            }}));

        duelUI.addObject(new UIImageButton(46,50,8,8,Assets.okBtn,"okBtnEnd",()->{
            if(gameWon){
                backToBoard();
            }}));
    }

    /**
     * mini game tick method, this runs the logic of the game
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(duelUI);
        duelUI.tick();
        if(!gameSetup){
            setupGame();
        }
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(gameStart&&!gameWon&&!tie){
            if(currentPlayer == 1) {
                if (timer > coolDown) {
                    if (handler.getKeyManager().numPad1 | handler.getKeyManager().num1) {
                        leftPlayerAnswer = "rock";
                        currentPlayer = 2;
                    } else if (handler.getKeyManager().numPad2 | handler.getKeyManager().num2) {
                        leftPlayerAnswer = "paper";
                        currentPlayer = 2;
                    } else if (handler.getKeyManager().numPad3 | handler.getKeyManager().num3) {
                        leftPlayerAnswer = "scissors";
                        currentPlayer = 2;
                    }
                    timer = 0;
                }
            }
            else if(currentPlayer==2){
                if (timer > coolDown) {
                    if (handler.getKeyManager().numPad1 | handler.getKeyManager().num1) {
                        rightPlayerAnswer = "rock";
                        currentPlayer = 0;
                    } else if (handler.getKeyManager().numPad2 | handler.getKeyManager().num2) {
                        rightPlayerAnswer = "paper";
                        currentPlayer = 0;
                    } else if (handler.getKeyManager().numPad3 | handler.getKeyManager().num3) {
                        rightPlayerAnswer = "scissors";
                        currentPlayer = 0;
                    }
                    timer = 0;
                }
            }
            else if(currentPlayer==0){
                compareAnswers();
            }
        }

    }

    /**
     * Mini game render method, this renders the mini game's graphics
     * @param g graphics parameter passed to game loop
     */
    @Override
    public void render(Graphics g) {
        g.setFont(font);
        duelUI.renderById(g,"gameTitle");
        duelUI.renderById(g,"rockI");
        duelUI.renderById(g,"paperI");
        duelUI.renderById(g,"scissorsI");
        duelUI.renderById(g,"number1");
        duelUI.renderById(g,"number2");
        duelUI.renderById(g,"number3");


        if(!gameStart){
            duelUI.renderById(g,"okBtnBegin");
            duelUI.renderById(g,"backdrop");
            g.drawString("Rock paper scissors",510,450);
            g.drawString("use the number keys",510,500);
            g.drawString("to pick your answers!",510,550);
        }
        if(tie){
            duelUI.renderById(g,"backdrop");
            g.drawString("its a tie!",510,450);
            g.drawString("try again",510,500);
            duelUI.renderById(g,"okBtnTie");
            duelUI.renderById(g,leftPlayerAnswer+"L");
            duelUI.renderById(g,rightPlayerAnswer+"R");
        }
        if(gameWon) {
            g.drawString("congratulations", 600, 750);
            g.drawString(winner, 600, 800);
            g.drawString("you win!", 600, 850);
            duelUI.renderById(g,leftPlayerAnswer+"L");
            duelUI.renderById(g,rightPlayerAnswer+"R");
            duelUI.renderById(g,"okBtnEnd");
        }

        if(currentPlayer==1&&gameStart){
            g.drawString(leftPlayer.getName()+" it's your turn!",200,750);
        }
        else if(currentPlayer==2){
            g.drawString(rightPlayer.getName()+" it's your turn!",800,750);
        }

        duelUI.renderById(g,"leftPlayer");
        duelUI.renderById(g,"rightPlayer");
        renderIndicator(g);

    }

    /**
     * this pulls the data from the game, of who are the players that are playing a duel
     */
    private void updateDuelPlayers(){
        leftPlayer = game.getDuelPlayer1();
        rightPlayer = game.getDuelPlayer2();
    }

    /**
     * this renders the player indicator, corresponding to the players turn
     * @param g graphics parameter passed to game loop
     */
    private void renderIndicator(Graphics g){
        if(leftPlayer==game.getPlayerList().get(0).getData()){
            duelUI.renderById(g, "playerIndicator1L");
        }
        else if(leftPlayer==game.getPlayerList().get(1).getData()){
            duelUI.renderById(g, "playerIndicator2L");
        }
        else if(game.getNumberOfPlayers()>=3 && leftPlayer==game.getPlayerList().get(2).getData()){
            duelUI.renderById(g,"playerIndicator3L");
        }
        else if(game.getNumberOfPlayers()==4&& leftPlayer==game.getPlayerList().get(3).getData()){
            duelUI.renderById(g,"playerIndicator4L");
        }

        if(rightPlayer==game.getPlayerList().get(0).getData()){
            duelUI.renderById(g, "playerIndicator1R");
        }
        else if (rightPlayer==game.getPlayerList().get(1).getData()){
            duelUI.renderById(g, "playerIndicator2R");
        }
        else if(game.getNumberOfPlayers()>=3 && rightPlayer==game.getPlayerList().get(2).getData()){
            duelUI.renderById(g,"playerIndicator3R");
        }
        else if(game.getNumberOfPlayers()==4&& rightPlayer==game.getPlayerList().get(3).getData()){
            duelUI.renderById(g,"playerIndicator4R");
        }
    }

    /**
     * this method is run a single time when the mini game is called, it prepares all variables to their default state
     * like resetting players hp, and change all knockouts to false
     */
    private void setupGame(){
        gameSetup = true;
        gameStart = false;
        gameWon = false;
        winner = "";
        updateDuelPlayers();
        currentPlayer = 1;
    }

    /**
     * sets a player as a winner and gives a prize
     * @param winner player obj of the winner of the game
     * @param loser player obj of the loser of the game
     */
    private void winGame(Player winner, Player loser){
        this.winner = winner.getName();
        if(!gameWon) {
            gameWon = true;
            winner.addCoins(Math.min(loser.getCoins(), 10));
            loser.addCoins(-10);
            winner.setWonDuel(true);
        }
    }

    /**
     * method that returns the game from the board
     */
    private void backToBoard(){
        gameSetup = false;
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

    /**
     * this compares the answers of each player, and determines who won, or if its a tie
     */
    private void compareAnswers(){
        if(leftPlayerAnswer.equals(rightPlayerAnswer)){
            tie = true;
            currentPlayer = 0;
        }
        else if (leftPlayerAnswer.equals("paper")&& rightPlayerAnswer.equals("rock")){
            winGame(leftPlayer, rightPlayer);
        }
        else if(leftPlayerAnswer.equals("scissors")&& rightPlayerAnswer.equals("paper")){
            winGame(leftPlayer, rightPlayer);
        }
        else if(leftPlayerAnswer.equals("rock")&& rightPlayerAnswer.equals("scissors")){
            winGame(leftPlayer, rightPlayer);
        }
        else {
            winGame(rightPlayer, leftPlayer);
        }
    }

}



