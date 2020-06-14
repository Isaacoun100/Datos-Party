package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.GameLoop;
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

    public void randomDuel(Player player, Game game) {
        pauseEvent(game);
        Player target = randomTarget(player, game);
        duel(player,target,game);
    }

    public void duel(Player player1, Player player2, Game game){
        game.updateDuelPlayers(player1,player2);
        GameLoop.setState(GameLoop.gameDependantStates.get(0).getData());
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
            Player targetPlayer = randomTarget(player,game);
            targetPlayer.addStars(1);
            System.out.println("Stolen star from " + targetPlayer.getName());
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
        Player randomPlayer = randomTarget(player, game);
        if (randomPlayer.getStars() >= 1) {
            randomPlayer.addStars(-1);
            player.addStars(1);
        }
        pauseEvent(game);
    }

    public void teleport(Player player, Game game) {
        player.setReversed(false);
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
        player.setReversed(false);
        Player targetPlayer = randomTarget(player, game);
        Node<Box> targetPosition = targetPlayer.getPosition();
        Node<Box> playerPosition = player.getPosition();
        player.setRenderPos(targetPosition.getData().getX(),targetPosition.getData().getY());
        targetPlayer.setPosition(playerPosition);
        targetPlayer.setRenderPos(playerPosition.getData().getX(),playerPosition.getData().getY());
        player.setPosition(targetPosition);
        pauseEvent(game);

    }

    private Player randomTarget(Player player, Game game){
        if(game.getNumberOfPlayers()==2){
            if(player == game.getPlayerList().get(0).getData()){
                return game.getPlayerList().get(1).getData();
            }
            else{
                return game.getPlayerList().get(0).getData();
            }
        }
        else {
            int numRandom = Dice.roll(0, game.getNumberOfPlayers() - 1);
            Player randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
            while(player == randomPlayer){
                numRandom = Dice.roll(0, game.getNumberOfPlayers() - 1);
                randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
            }
            return randomPlayer;
        }
    }

}
