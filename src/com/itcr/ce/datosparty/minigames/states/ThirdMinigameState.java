package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class ThirdMinigameState extends State {

    private final UIManager uiManager;
    private final Handler handler;
    private final Game game;
    private SinglyList<Integer> scores;
    private SinglyList<Player> tiedPlayers;
    private SinglyList<Player> players;
    private boolean tie = false;
    private boolean add = true;
    private int aim, turns;
    private Player winner;
    private final int numPlayers;
    private final int targetX = 700;
    private final int targetY = 350;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new UIManager(handler);
        this.handler = handler;
        this.game = game;
        this.numPlayers = numPlayers;
        this.turns = 0;
        tiedPlayers = new SinglyList<>();
        scores = new SinglyList<>();
        players = game.getPlayerList();

        uiManager.addObject(new UIImageButton(43, 40, 7*2, 2*2, Assets.player1Button,"shootButton", new ClickListener() {
            @Override
            public void onClick() {
                shoot();
            }
        }));

        uiManager.addObject(new UIImageButton(43, 45, 7*2, 2*2, Assets.player1Button,"winButton", new ClickListener() {
            @Override
            public void onClick() {
                winGame();
            }
        }));

        uiManager.addObject( new UIImage(0, 0, 16, 16, Assets.blackScope, "blackScope"));
        uiManager.addObject( new UIImage(0, 0, 16, 16, Assets.redScope, "redScope"));

    }

    private void changeDirection() {
        if (aim >= 50) {
            add = false;
        } else if (aim <= -50) {
            add = true;
        }
    }

    private void move() {
        if (add) {
            aim++;
        } else {
            aim--;
        }
    }

    private void shoot() {
        if (turns != numPlayers) {
            scores.add(aim);
            turns++;
        }
    }

    private int compare(int score1, int score2, Player player1, Player player2){
        if (Math.abs(score1) == Math.abs(score2)) {
            tie = true;
            tiedPlayers.add(player1);
            tiedPlayers.add(player2);
            winner = player1;
            return score1;
        } else if (Math.abs(score1) < Math.abs(score2)) {
            winner = player1;
            return score1;
        } else {
            winner = player2;
            return score2;
        }
    }

    private void searchWinner() {
        int bestScore;
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

    private void winGame(){
        if(turns == numPlayers) {
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
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        changeDirection();
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawString("aim: " + aim, 100, 100);
        if (scores.getLength() >= 1) {
            g.drawString(players.get(0).getData().getName() + ": " + scores.get(0).getData(), 150, 100);
        }
        if (scores.getLength() >= 2) {
            g.drawString(players.get(1).getData().getName() + ": " + scores.get(1).getData(), 150, 150);
        }
        if (numPlayers >= 3 && scores.getLength() >= 3) {
            g.drawString(players.get(2).getData().getName() + ": " + scores.get(2).getData(), 150, 200);
        }
        if (numPlayers == 4 && scores.getLength() >= 4) {
            g.drawString(players.get(3).getData().getName() + ": " + scores.get(3).getData(), 150, 250);
        }
        if (turns == numPlayers) {
            if (tie) {
                SinglyNode<Player> currentWinner = tiedPlayers.getHead();
                int playerPosition = 325;
                g.drawString("It's a tie!", 150, 300);
                g.drawString("The winners are:", 175, playerPosition);
                while (currentWinner != null) {
                    playerPosition += 25;
                    String stringWinner = currentWinner.getData().getName();
                    g.drawString(stringWinner, 175, playerPosition);
                    currentWinner = (SinglyNode<Player>) currentWinner.getNext();
                }
            } else {
                searchWinner();
                String stringWinner = winner.getName();
                g.drawString("Winner: " + stringWinner, 150, 300);
            }
            uiManager.renderById(g, "winButton");
        }
        uiManager.changeObjectPos("blackScope", aim * 5 + targetX, targetY);
        uiManager.changeObjectPos("redScope", targetX, targetY);
        uiManager.renderById(g, "shootButton");
        uiManager.renderById(g, "blackScope");
        uiManager.renderById(g, "redScope");
    }
}