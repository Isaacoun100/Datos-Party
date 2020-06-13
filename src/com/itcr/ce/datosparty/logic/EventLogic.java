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

    public void duel(Player player, Game game) {
        pauseEvent(game);
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
        pauseEvent(game);
    }

    public void loseStar(Player player, Game game) {
        if (player.getStars() >= 1)  {
            player.addStars(-1);
            Player randomPlayer = returnRandomPlayer(player);
            randomPlayer.addStars(1);
            System.out.println("Stolen star from " + randomPlayer.getName());
        }
        pauseEvent(game);
    }

    public void winTwoStars(Player player, Game game) {
        player.addStars(2);
        pauseEvent(game);
    }

    public void winFiveStars(Player player, Game game) {
        player.addStars(5);
        pauseEvent(game);
    }

    public void stealStar(Player player, Game game) {
        Player randomPlayer = returnRandomPlayer(player);
        if (randomPlayer.getStars() >= 1) {
            randomPlayer.addStars(-1);
            player.addStars(1);
        }
        pauseEvent(game);
    }

    public void teleport(Player player, Game game) {
        int numRandom;
        CircularList<Box> mainCircuit =  game.getMainCircuit();
        SinglyList<Box> phaseA = game.getPhaseA();
        SinglyList<Box> phaseB = game.getPhaseB();
        DoublyList<Box> phaseC = game.getPhaseC();
        CircularDoublyList<Box> phaseD = game.getPhaseD();
        switch (Dice.roll(1, 5)) {
            case 1 -> {
                numRandom = Dice.roll(0, mainCircuit.getLength() - 1);
                Node<Box> newPosition = mainCircuit.get(numRandom);
                player.setRenderPos(newPosition.getData().getX(),newPosition.getData().getY());
                player.setPosition(newPosition);
            }
            case 2 -> {
                numRandom = Dice.roll(0, phaseA.getLength() - 1);
                Node<Box> newPosition = phaseA.get(numRandom);
                player.setRenderPos(newPosition.getData().getX(),newPosition.getData().getY());
                player.setPosition(phaseA.get(numRandom));

            }
            case 3 -> {
                numRandom = Dice.roll(0, phaseB.getLength() - 1);
                Node<Box> newPosition = phaseB.get(numRandom);
                player.setRenderPos(newPosition.getData().getX(),newPosition.getData().getY());
                player.setPosition(phaseB.get(numRandom));

            }
            case 4 -> {
                numRandom = Dice.roll(0, phaseC.getLength() - 1);
                Node<Box> newPosition = phaseC.get(numRandom);
                player.setRenderPos(newPosition.getData().getX(),newPosition.getData().getY());
                player.setPosition(phaseC.get(numRandom));
            }
            case 5 -> {
                numRandom = Dice.roll(0, phaseD.getLength() - 1);
                Node<Box> newPosition = phaseD.get(numRandom);
                player.setRenderPos(newPosition.getData().getX(),newPosition.getData().getY());
                player.setPosition(phaseD.get(numRandom));
            }
        }
        pauseEvent(game);
    }


    public void swapPlayers(Player player, Game game) {
        int numRandom = Dice.roll(0, game.getNumberOfPlayers() - 1);
        Player randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
        while(player == randomPlayer){
            numRandom = Dice.roll(0, game.getNumberOfPlayers() - 1);
            randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
        }
        Node<Box> randomPosition = randomPlayer.getPosition();
        Node<Box> playerPosition = player.getPosition();
        player.setRenderPos(randomPosition.getData().getX(),randomPosition.getData().getY());
        randomPlayer.setPosition(playerPosition);
        randomPlayer.setRenderPos(playerPosition.getData().getX(),playerPosition.getData().getY());
        player.setPosition(randomPosition);
        pauseEvent(game);

    }
}
