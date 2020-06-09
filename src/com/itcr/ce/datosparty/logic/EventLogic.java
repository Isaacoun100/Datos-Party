package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.CircularDoublyList;
import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.lists.DoublyList;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;

public class EventLogic {

    public void duel(){}

    public void pauseEvent(Game game) {
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

    public void loseStar(Player player, Game game) {
        pauseEvent(game);
        if (player.getStars() >= 1)  {
            player.addStars(-1);
            Player randomPlayer = returnRandomPlayer(player);
            randomPlayer.addStars(1);
            System.out.println("Stolen star from " + randomPlayer.getName());
        }
    }

    public void winTwoStars(Player player, Game game) {
        pauseEvent(game);
        player.addStars(2);
    }

    public void winFiveStars(Player player, Game game) {
        pauseEvent(game);
        player.addStars(5);
    }

    public void stealStar(Player player, Game game) {
        pauseEvent(game);
        Player randomPlayer = returnRandomPlayer(player);
        randomPlayer.addStars(-1);
        if (randomPlayer.getStars() >= 1) {
            player.addStars(1);
        }
        System.out.println("Stolen from " + randomPlayer.getName());
    }

    public void teleport(Player player, Game game) {
        pauseEvent(game);
        int numRandom;
        CircularList<Box> mainCircuit =  game.getMainCircuit();
        SinglyList<Box> phaseA = game.getPhaseA();
        SinglyList<Box> phaseB = game.getPhaseB();
        DoublyList<Box> phaseC = game.getPhaseC();
        CircularDoublyList<Box> phaseD = game.getPhaseD();
        switch (Dice.roll(1, 5)) {
            case 1 -> {
                numRandom = Dice.roll(0, mainCircuit.getLength() - 1);
                player.setPosition(mainCircuit.get(numRandom));
            }
            case 2 -> {
                numRandom = Dice.roll(0, phaseA.getLength() - 1);
                player.setPosition(phaseA.get(numRandom));
            }
            case 3 -> {
                numRandom = Dice.roll(0, phaseB.getLength() - 1);
                player.setPosition(phaseB.get(numRandom));
            }
            case 4 -> {
                numRandom = Dice.roll(0, phaseC.getLength() - 1);
                player.setPosition(phaseC.get(numRandom));
            }
            case 5 -> {
                numRandom = Dice.roll(0, phaseD.getLength() - 1);
                player.setPosition(phaseD.get(numRandom));
            }
        }
    }

    public void swapPlayers(Player player, Game game) {
        pauseEvent(game);
        int numRandom = Dice.roll(0, Round.getPlayerOrder().getLength() - 1);
        Player randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
        Node<Box> randomPosition = randomPlayer.getPosition();
        Node<Box> playerPosition = player.getPosition();
        randomPlayer.setPosition(playerPosition);
        player.setPosition(randomPosition);
    }
}
