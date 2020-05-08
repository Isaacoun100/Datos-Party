package com.okcomputer.datosparty.algorithms;

import com.okcomputer.datosparty.dataStructures.DoublyList;
import com.okcomputer.datosparty.dataStructures.Node;
import com.okcomputer.datosparty.logic.Player;

public class BubbleSort {

    public static <T extends Comparable<T>> void swap(Node<Player> node1, Node<Player> node2) {
        Player data1 = node1.getData();
        Player data2 =  node2.getData();
        node1.setData(data2);
        node2.setData(data1);
    }

    public static void sort (DoublyList<Player> lista)  {
        int i, j;
        for (i = 0; i < lista.getLength() - 1; i++) {
            for (j = 0; j < lista.getLength() - 1 - i; j++) {
                int numNode1 = lista.getNodeByIndex(j).getData().getTurn();
                int numNode2 = lista.getNodeByIndex((j+1)).getData().getTurn();
                while (numNode1 == numNode2) {
                    lista.getNodeByIndex(j+1).getData().setTurn();
                    numNode2 = lista.getNodeByIndex((j+1)).getData().getTurn();
                }
                if (numNode1 < numNode2) {
                    swap(lista.getNodeByIndex(j), lista.getNodeByIndex((j+1)));
                }
            }
        }
    }
}
