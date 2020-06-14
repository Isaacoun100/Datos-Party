package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FourthMinigameState extends State {
    private Handler handler;
    private UIManager uiManager;
    private int numPlayers;
    private int starTimer;
    private SinglyList<Player> players;
    private SinglyList<Integer> scores;
    private SinglyList<Boolean> turns;
    private SinglyList<Player> tiedPlayers;
    private Player winner;
    private int end;
    private int  bestScore = Integer.MAX_VALUE;
    private boolean tie, playing = false;
    private Game game;

    public FourthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        this.handler = handler;
        this.numPlayers = numPlayers;
        uiManager = new UIManager(handler);
        this.game = game;

        players = game.getPlayerList();
        scores = new SinglyList<>();
        turns = new SinglyList<>();
        tiedPlayers = new SinglyList<>();

        uiManager.addObject(new UIImageButton(30, 30, 7*2, 2*2, Assets.player4Button,"endButton",
                this::winGame));

        uiManager.addObject(new UIImageButton(30, 50, 7*2, 2*2, Assets.player3Button,"startButton",
                this::startGame));

        scores.add(-1);
        turns.add(false);

        scores.add(-1);
        turns.add(false);

        if(numPlayers>=3){
            scores.add(-1);
            turns.add(false);
        }

        if(numPlayers>=4){
            scores.add(-1);
            turns.add(false);
        }

    }

    private void startGame() {
        if (!playing) {
            starTimer = Dice.roll(-1000, -100);
            playing = true;
        }
    }

    private void getScore() {
        if (handler.getKeyManager().key_Q && !turns.get(0).getData()) {
            scores.get(0).setData(starTimer);
            turns.get(0).setData(true);
            end++;
        }
        if (handler.getKeyManager().key_P && !turns.get(1).getData()) {
            scores.get(1).setData(starTimer);
            turns.get(1).setData(true);
            end++;
        }
        if (numPlayers >= 3 && handler.getKeyManager().key_Z && !turns.get(2).getData()) {
            scores.get(2).setData(starTimer);
            turns.get(2).setData(true);
            end++;
        }
        if (numPlayers == 4 && handler.getKeyManager().key_M && !turns.get(3).getData()) {
            scores.get(3).setData(starTimer);
            turns.get(3).setData(true);
            end++;
        }
    }

    private int compare(int score1, int score2, Player player1, Player player2){
        if (0 < score1 && 0 < score2) {
            if (score1 == score2 && score1 <= bestScore) {
                tie = true;
                tiedPlayers.add(player1);
                tiedPlayers.add(player2);
                winner = player1;
                bestScore = score2;
                return score1;
            } else if (score1 < score2) {
                winner = player1;
                bestScore = score1;
                return score1;
            } else {
                winner = player2;
                bestScore = score2;
                return score2;
            }
        }else if (score2 < 0) {
            winner = player1;
            bestScore = score1;
            return score1;
        } else {
            winner = player2;
            bestScore = score2;
            return score2;
        }
    }

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

    private void displayEndButton(Graphics g) {
        if (end == numPlayers || starTimer > 150) {
            searchWinner();
            if (0 <= bestScore) {
                g.drawString("Winner: " + winner.getName(), 150, 300);
            } else {
                winner = null;
                g.drawString("No one spot it! Bummer...", 150, 300);
            }
            uiManager.renderById(g, "endButton");
        }
    }

    private void winGame(){
        if (playing) {
            if (winner != null) {
                winner.addCoins(10);
                winner = null;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            } else if (tie) {
                SinglyNode<Player> currentWinner = tiedPlayers.getHead();
                while (currentWinner != null) {
                    currentWinner.getData().addCoins(10);
                    currentWinner = (SinglyNode<Player>) currentWinner.getNext();
                }
            }
            turns.clear();
            scores.clear();
            for (int i = 0; i < numPlayers; i++) {
                turns.add(false);
                scores.add(-1);
            }
            tiedPlayers.clear();
            end = 0;
            playing = false;
            game.resumeGame();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        if (playing) {
            starTimer++;
        }
        getScore();
    }

    @Override
    public void render(Graphics g) {
        if (playing) {
            g.drawString("Timer: " + starTimer, 50, 100);
            g.drawString(players.get(0).getData().getName() + ": " + scores.get(0).getData(), 150, 100);
            g.drawString(players.get(1).getData().getName() + ": " + scores.get(1).getData(), 150, 150);

            if (numPlayers >= 3) {
                g.drawString(players.get(2).getData().getName() + ": " + scores.get(2).getData(), 150, 200);
            }
            if (numPlayers == 4) {
                g.drawString(players.get(3).getData().getName() + ": " + scores.get(3).getData(), 150, 250);
            }
            displayEndButton(g);
        } else {
            uiManager.renderById(g, "startButton");
        }
    }
}
