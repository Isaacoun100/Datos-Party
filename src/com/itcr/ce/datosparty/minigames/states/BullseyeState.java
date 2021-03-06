package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIBackground;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

/**
 * Minigame state where each player has to shoot a target. The one who es closest to the target wins
 */
public class BullseyeState extends State {

    private final UIManager uiManager;
    private final Handler handler;
    private final Game game;
    private final Font titleFont, normalFont;
    private SinglyList<Integer> scores;
    private SinglyList<Player> tiedPlayers;
    private SinglyList<Player> players;
    private boolean tie = false;
    private boolean add = true;
    private int aim, turns;
    private int bestScore = Integer.MAX_VALUE;
    private Player winner;
    private final int numPlayers;
    private final int targetX = 725;
    private final int targetY = 350;

    /**
     * Sets up the contents that the minigame needs to work
     * @param handler contains the UIManager to draw and animate images and buttons onscreen
     * @param numPlayers number of players in Game
     * @param game contains elements of the game currently played, like the players
     */
    public BullseyeState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new UIManager(handler);
        this.handler = handler;
        this.game = game;
        this.numPlayers = numPlayers;
        this.turns = 0;
        tiedPlayers = new SinglyList<>();
        scores = new SinglyList<>();
        players = game.getPlayerList();

        uiManager.addObject(new UIBackground(Assets.openFieldBG,"background"));
        titleFont = Assets.swyrtd.deriveFont(Font.PLAIN, 100);
        normalFont = Assets.swyrtd.deriveFont(Font.PLAIN, 25);

        uiManager.addObject(new UIImageButton(46, 38, 8, 8, Assets.redScope,
                "shootButton", this::shoot));

        uiManager.addObject(new UIImageButton(45, 45, 8, 8, Assets.okBtn,"winButton",
                this::winGame));

        uiManager.addObject(new UIImage(0, 0, 24, 24, Assets.target, "target"));
        uiManager.addObject( new UIImage(0, 0, 8, 8, Assets.blackScope, "blackScope"));

    }

    /**
     * Determines if the scope goes left or right
     */
    private void changeDirection() {
        if (aim >= 50) {
            add = false;
        } else if (aim <= -50) {
            add = true;
        }
    }

    /**
     * Sets the speed of the scope
     */
    private void move() {
        if (add) {
            aim += 3;
        } else {
            aim -= 3;
        }
    }

    /**
     * Sets the points that the player gets when it shoots
     */
    private void shoot() {
        if (turns != numPlayers) {
            scores.add(aim);
            turns++;
        }
    }

    /**
     * compares the scores to see who won by setting them both on Math.abs() and seeing which is lesser than the other
     * @param score1 first score, in some cases it is the score that is currently winning
     * @param score2 second score
     * @param player1 player which made score1, perhaps the one currently wining
     * @param player2 player which made score2
     * @return score that is currently winning th minigame
     */
    private int compare(int score1, int score2, Player player1, Player player2){
        if (Math.abs(score1) == Math.abs(score2) && Math.abs(score1) <= Math.abs(bestScore)) {
            tie = true;
            tiedPlayers.add(player1);
            tiedPlayers.add(player2);
            winner = player1;
            bestScore = score2;
            return score1;
        } else if (Math.abs(score1) < Math.abs(score2)) {
            winner = player1;
            bestScore = score1;
            return score1;
        } else {
            winner = player2;
            bestScore = score2;
            return score2;
        }
    }

    /**
     * Compares scores and players untill it finds the winner
     */
    private void searchWinner() {
        Player player1 = players.getHead().getData();
        Player player2 = players.getHead().getNext().getData();
        bestScore = compare(scores.get(0).getData(), scores.get(1).getData(), player1, player2);
        if (numPlayers >= 3) {
            Player player3 = players.get(2).getData();
            bestScore = compare(bestScore, scores.get(2).getData(), winner, player3);
        }
        if (numPlayers == 4) {
            Player player4 = players.get(3).getData();
            compare(bestScore, scores.get(3).getData(), winner, player4);
        }
    }

    /**
     * Adds coins to the winner and resumes game. If there is a tie, it give coins to both players
     */
    private void winGame(){
        if (tie) {
            SinglyNode<Player> currentWinner = tiedPlayers.getHead();
            while (currentWinner != null) {
                currentWinner.getData().addCoins(10);
                currentWinner = (SinglyNode<Player>) currentWinner.getNext();
            }
        } else {
            winner.addCoins(10);
        }
        turns = 0;
        scores.clear();
        tiedPlayers.clear();
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

    /**
     * Checks events over and over
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        changeDirection();
        move();
    }

    /**
     * Draws images onscreen
     * @param g graphics parameter passed to gameloop
     */
    @Override
    public void render(Graphics g) {
        uiManager.renderById(g, "background");
        g.setFont(titleFont);
        g.drawString("Bullseye!", 300, 1000);
        g.setFont(normalFont);
        if (scores.getLength() >= 1) {
            g.drawString(players.get(0).getData().getName() + "'s turn is done", 950, 400);
        }
        if (scores.getLength() >= 2) {
            g.drawString(players.get(1).getData().getName() + "'s turn is done", 950, 450);
        }
        if (numPlayers >= 3 && scores.getLength() >= 3) {
            g.drawString(players.get(2).getData().getName() + "'s turn is done", 950, 500);
        }
        if (numPlayers == 4 && scores.getLength() >= 4) {
            g.drawString(players.get(3).getData().getName() + "'s turn is done", 950, 550);
        }
        if (turns == numPlayers) {
            if (tie) {
                SinglyNode<Player> currentWinner = tiedPlayers.getHead();
                int playerPosition = 450;
                g.drawString("It's a tie!", 150, 400);
                g.drawString("The winners are:", 175, playerPosition);
                while (currentWinner != null) {
                    playerPosition += 50;
                    String stringWinner = currentWinner.getData().getName();
                    g.drawString(stringWinner, 175, playerPosition);
                    currentWinner = (SinglyNode<Player>) currentWinner.getNext();
                }
            } else {
                searchWinner();
                String stringWinner = winner.getName();
                g.drawString("Winner: " + stringWinner, 150, 400);
            }
            uiManager.renderById(g, "winButton");
        }
        uiManager.renderById(g, "target");
        uiManager.changeObjectPos("target", targetX - 120 - 8, targetY - 120 - 8);
        uiManager.changeObjectPos("blackScope", aim * 5 + targetX, targetY);
        uiManager.renderById(g, "shootButton");
        uiManager.renderById(g, "blackScope");
    }
}