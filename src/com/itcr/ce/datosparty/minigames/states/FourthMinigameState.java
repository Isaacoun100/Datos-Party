package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIBackground;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FourthMinigameState extends State {
    private Handler handler;
    private UIManager uiManager;
    private final Font normalFont, titleFont;
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
    private SinglyList<String> doneMessage;

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
        doneMessage = new SinglyList<>();

        normalFont = Assets.acientModernTales.deriveFont(Font.PLAIN, 50);
        titleFont = Assets.acientModernTales.deriveFont(Font.PLAIN, 200);
        Animation starAnimation = new Animation(100, Assets.shootingStar);

        uiManager.addObject(new UIBackground(Assets.clearSkyBG,"background"));
        uiManager.addObject(new UIAnimatedImage(55, 5, 8,8,
                starAnimation,"shootingStar"));


        uiManager.addObject(new UIImageButton(45, 50, 8, 8, Assets.okBtn,"endButton",
                this::winGame));

        uiManager.addObject(new UIImageButton(45, 50, 8, 8, Assets.okBtn,"startButton",
                this::startGame));

        scores.add(-1);
        turns.add(false);
        doneMessage.add("");

        scores.add(-1);
        turns.add(false);
        doneMessage.add("");

        if(numPlayers>=3){
            scores.add(-1);
            turns.add(false);
            doneMessage.add("");
        }

        if(numPlayers>=4){
            scores.add(-1);
            turns.add(false);
            doneMessage.add("");
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
            if (scores.get(0).getData() < 0) {
                doneMessage.get(0).setData("Too soon!");
            } else {
                doneMessage.get(0).setData("Ready!");
            }
            end++;
        }
        if (handler.getKeyManager().key_P && !turns.get(1).getData()) {
            scores.get(1).setData(starTimer);
            turns.get(1).setData(true);
            if (scores.get(1).getData() < 0) {
                doneMessage.get(1).setData("Too soon!");
            } else {
                doneMessage.get(1).setData("Ready!");
            }
            end++;
        }
        if (numPlayers >= 3 && handler.getKeyManager().key_C && !turns.get(2).getData()) {
            scores.get(2).setData(starTimer);
            turns.get(2).setData(true);
            if (scores.get(2).getData() < 0) {
                doneMessage.get(2).setData("Too soon!");
            } else {
                doneMessage.get(2).setData("Ready!");
            }
            end++;
        }
        if (numPlayers == 4 && handler.getKeyManager().key_M && !turns.get(3).getData()) {
            scores.get(3).setData(starTimer);
            turns.get(3).setData(true);
            if (scores.get(3).getData() < 0) {
                doneMessage.get(3).setData("Too soon!");
            } else {
                doneMessage.get(3).setData("Ready!");
            }
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
        uiManager.renderById(g, "background");
        g.setColor(Color.white);
        if (playing) {
            g.setFont(normalFont);
            if (starTimer >= 0) {
                uiManager.renderById(g, "shootingStar");
            }
            g.drawString(doneMessage.get(0).getData(), 50, 900);
            g.drawString(doneMessage.get(1).getData(), 350, 900);

            if (numPlayers >= 3) {
                g.drawString(doneMessage.get(2).getData(), 1050, 900);
            }
            if (numPlayers == 4) {
                g.drawString(doneMessage.get(3).getData(), 1350, 900);
            }
            displayEndButton(g);
        } else {
            g.setFont(titleFont);
            g.drawString("Spot the", 250, 200);
            g.drawString("Shooting star!", 550, 350);
            g.setFont(normalFont);
            g.drawString("When you see it...", 50, 450);
            g.drawString(players.get(0).getData().getName() + ": press Q", 50, 900);
            g.drawString(players.get(1).getData().getName() + ": press P", 400, 900);
            if (numPlayers >= 3) {
                g.drawString(players.get(2).getData().getName() + ": press C", 900, 900);
            }
            if (numPlayers >= 4) {
                g.drawString(players.get(3).getData().getName() + ": press M", 1250, 900);
            }
            uiManager.renderById(g, "startButton");
        }
    }
}
