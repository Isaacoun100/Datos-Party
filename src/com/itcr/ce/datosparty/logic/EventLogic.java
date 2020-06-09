package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.entities.Player;

public class EventLogic {

    public void duel(){}

    public void pauseToSteal(Game game) {
        try {
            game.pauseGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Player returnRandomPlayer(Player currentPlayer) {
        Player randomPlayer = currentPlayer;
        while (randomPlayer == currentPlayer) {
            randomPlayer =Round.getPlayerOrder().get(Dice.roll(0, Round.getPlayerOrder().getLength() - 1)).getData();
        }
        return randomPlayer;
    }

    public void stealCoins(Player thief, Player victim) {
        int randomCoins = Dice.roll(1, 10);
        if (victim.getCoins() < randomCoins) {
            randomCoins = thief.getCoins();
        }
        victim.addCoins(-randomCoins);
        thief.addCoins(randomCoins);
    }

    public static void giftCoins() {

    }
}
