package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class Round {

    private static SinglyList<Player> playerOrder;
    private static int numPlayers;
    private static int numRound = 0;

    public static void definePlayers(SinglyList<String> playerNames) {
        if (1 < playerNames.getLength() && playerNames.getLength() <= 4) {
            numPlayers = playerNames.getLength();
            playerOrder = new SinglyList<>();
            for (int i = 0; i < 4; i++) {
                playerOrder.add(new Player(playerNames.getNodeByIndex(i).getData()));
                if (playerNames.getNodeByIndex(i).getNext() == null) {
                    break;
                }
            }
        }
    }

    public static void defineOrder() {
        playerOrder.getNodeByIndex(0).getData().setMovement();
        playerOrder.getNodeByIndex(1).getData().setMovement();
        if (numPlayers >= 3) {
            playerOrder.getNodeByIndex(2).getData().setMovement();
        }
        if (numPlayers == 4) {
            playerOrder.getNodeByIndex(3).getData().setMovement();
        }
        sort(playerOrder);
    }

    private static void sort(SinglyList<Player> list)  {
        SinglyNode<Player> node1 = list.getHead();
        while (node1.getNext() != null) {
            SinglyNode<Player> node2 = node1.getNext();
            int num1 = node1.getData().getMovement();
            int num2 = node2.getData().getMovement();
            while (num1 == num2) {
                node2.getData().setMovement();
                num2 = node2.getData().getMovement();
            }
            if (num1 < num2) {
                swap(node1, node2);
                node1 = node1.getNext();
            } else {
                node1 = node1.getNext();
            }
        }
        isOrdered(list);
    }

    private static void swap(SinglyNode<Player> node1, SinglyNode<Player> node2) {
        Player data1 = node1.getData();
        Player data2 =  node2.getData();
        node1.setData(data2);
        node2.setData(data1);
    }

    private static void isOrdered(SinglyList<Player> list) {
        SinglyNode<Player> node = list.getHead();
        while (node.getNext() != null) {
            int num1 = node.getData().getMovement();
            int num2 = node.getNext().getData().getMovement();
            if (num1 <= num2) {
                sort(list);
            }
            node = node.getNext();
        }
    }

    public static SinglyList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public static int getNumRound() {
        return numRound;
    }

    public static void setNumRound(int numRound) {
        Round.numRound = numRound;
    }
}
