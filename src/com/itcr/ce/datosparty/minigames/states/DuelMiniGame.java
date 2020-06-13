package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class DuelMiniGame extends State {
    private final UIManager duelUI;
    public static Player player1, player2;
    private final Game game;
    private boolean gameSetup = false, gameWon = false;

    public DuelMiniGame(Handler handler, int numPlayers, Game game) {
        super(handler);
        this.game = game;
        duelUI = new UIManager(handler);

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(duelUI);
        duelUI.tick();
        if(!gameSetup){
            setupGame();
        }
        chooseWinner();
    }

    @Override
    public void render(Graphics g) {
        duelUI.renderAll(g);
    }

    public void updateDuelPlayers(){
        player1 = game.getDuelist1();
        player2 = game.getDuelist2();
    }

    public void setupGame(){
        gameSetup = true;
        gameWon = false;
        updateDuelPlayers();
    }

    public void chooseWinner(){
        int randomWinner = Dice.roll(1,2);
        if(randomWinner == 1){
            winGame(player1, player2);
        }else {
            winGame(player2, player1);
        }
    }

    public void winGame(Player winner, Player loser){
        winner.addCoins(10);
        winner.setWonDuel(true);
        loser.addCoins(-10);
        gameWon = true;
        backToBoard();
    }

    public void backToBoard(){
        gameSetup = false;
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

}



