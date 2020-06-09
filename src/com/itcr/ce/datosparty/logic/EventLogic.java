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
        System.out.println("Stolen " + randomCoins + " coins");
    }

    public void giftCoins(Player player, Game game) {
        pauseEvent(game);
        SinglyNode<Player> playerToAdd = Round.getPlayerOrder().getHead();
        int playersToGift = Round.getPlayerOrder().getLength() - 1;
        int randomCoins = Dice.roll(1, 10);
        while (randomCoins % playersToGift != 0) {
            randomCoins = Dice.roll(1, 10);
        }
        if (player.getCoins() < randomCoins) {
            System.out.println("Can't gift " + randomCoins + " coins.");
            return;
        }
        player.addCoins(-randomCoins);
        while (playerToAdd != null) {
            if (playerToAdd.getData() != player) {
                playerToAdd.getData().addCoins(randomCoins / playersToGift);
            }
            playerToAdd = (SinglyNode<Player>) playerToAdd.getNext();
        }
        System.out.println("Gifted " + randomCoins + " coins.");
    }
}
