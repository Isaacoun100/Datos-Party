package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.dataStructures.lists.CircularDoublyList;
import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.lists.DoublyList;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.*;
import com.itcr.ce.datosparty.logic.Event;
import com.itcr.ce.datosparty.minigames.minilogic.Minigame;

import java.awt.*;

public class YellowBox extends Box {
    public YellowBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.yellowBox,(int) x,(int) y, width, height, null);
    }

    @Override
    public void boxAction(Player player, Game game) {
        System.out.println("YellowBox");
        Event event = Event.STEAL_COINS; // game.eventStack.pop();
        try {
            game.pauseGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (event) {
            case DUEL -> {
                System.out.println("DUEL");
                Minigame.playMinigame(1);
            }
            case STEAL_COINS -> {
                System.out.println("STEAL_COINS");
                // Chooses random player
                // Should give player the option to steal whoever player he chooses to
                int maxPlayers = Round.getPlayerOrder().getLength() - 1;
                int randomCoins = Dice.roll(1, 10);
                System.out.println(randomCoins);
                Player randomPlayer = Round.getPlayerOrder().get(Dice.roll(0, maxPlayers)).getData();
                randomPlayer.addCoins(-randomCoins);
                if (randomPlayer.getCoins() < randomCoins) {
                    randomCoins = randomPlayer.getCoins();
                }
                player.addCoins(randomCoins);
            }
            case GIFT_COINS -> {
                System.out.println("GIFT_COINS");
                SinglyNode<Player> playerToAdd = Round.getPlayerOrder().getHead();
                int numPlayers = Round.getPlayerOrder().getLength();
                int randomCoins = Dice.roll(1, 10);
                while (randomCoins % numPlayers != 0) {
                    randomCoins = Dice.roll(1, 10);
                }
                player.addCoins(-randomCoins);
                while (playerToAdd != null) {
                    playerToAdd.getData().addCoins(randomCoins / numPlayers);
                    playerToAdd = (SinglyNode<Player>) playerToAdd.getNext();
                }
            }
            case LOSE_STAR -> {
                System.out.println("LOSE_STAR");
                player.addStars(-1);
            }
            case WIN_2_STARS -> {
                System.out.println("WIN_2_STARS");
                player.addStars(2);
            }
            case WIN_5_STARS -> {
                System.out.println("WIN_5_STARS");
                player.addStars(5);
            }
            case STEAL_STAR -> {
                System.out.println("STEAL_STAR");
                int maxPlayers = Round.getPlayerOrder().getLength() - 1;
                Player randomPlayer = Round.getPlayerOrder().get(Dice.roll(0, maxPlayers)).getData();
                randomPlayer.addStars(-1);
                player.addStars(1);
            }
            case TELEPORT -> {
                System.out.println("TELEPORT");
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
            case SWAP_PLAYERS -> {
                System.out.println("SWAP_PLAYERS");
                int numRandom = Dice.roll(0, Round.getPlayerOrder().getLength() - 1);
                Player randomPlayer =  Round.getPlayerOrder().get(numRandom).getData();
                Node<Box> randomPosition = randomPlayer.getPosition();
                Node<Box> playerPosition = player.getPosition();
                randomPlayer.setPosition(playerPosition);
                player.setPosition(randomPosition);
            }
            default -> System.out.println("Couldn't find event");
        }
    }
}
