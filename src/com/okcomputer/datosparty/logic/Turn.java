package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.algorithms.BubbleSort;
import com.okcomputer.datosparty.dataStructures.DoublyLinkedList;
import com.okcomputer.datosparty.dataStructures.SinglyLinkedList;

public class Turn {

    private static DoublyLinkedList<Player> playerOrder;
    private static int numPlayers = 0;

    public static void addPlayer(Player player) {
        if (numPlayers == 4) {
            System.out.println("No more than 4 players can join");
        } else {
            playerOrder.insert(player);
            numPlayers++;
        }
    }

    public static void defineOrder() {
        playerOrder.getNodeByIndex(0).getData().setTurn();
        if (numPlayers >= 2) {
            playerOrder.getNodeByIndex(1).getData().setTurn();
        }
        if (numPlayers >= 3) {
            playerOrder.getNodeByIndex(2).getData().setTurn();
        }
        if (numPlayers == 4) {
            playerOrder.getNodeByIndex(3).getData().setTurn();
        }
        BubbleSort.sort(playerOrder);

    }

    public static DoublyLinkedList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public static void setPlayerOrder(DoublyLinkedList<Player> playerOrder) {
        Turn.playerOrder = playerOrder;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
