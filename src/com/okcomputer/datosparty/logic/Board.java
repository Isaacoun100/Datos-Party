package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyLinkedList;
import com.okcomputer.datosparty.dataStructures.SinglyLinkedListNode;

public class Board {

    private SinglyLinkedList<Box> board = new SinglyLinkedList<>();

    public Board(int length) {
        int cont = 0;

        while (cont < length) {
            this.board.insert(new Box());
            SinglyLinkedListNode<Box> currentNode = this.board.getNodeByIndex(cont);
            currentNode.getData().boxNode(currentNode);
            cont++;
        }
    }

    public SinglyLinkedList<Box> getBoard() {
        return board;
    }
}
