package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.Node;
import com.okcomputer.datosparty.dataStructures.SinglyList;

public class Board {

    private SinglyList<Box> board = new SinglyList<>();

    public Board(int length) {
        int cont = 0;

        while (cont < length) {
            this.board.add(new Box());
            Node<Box> currentNode = this.board.getNodeByIndex(cont);
            currentNode.getData().boxNode(currentNode);
            cont++;
        }
    }

    public SinglyList<Box> getBoard() {
        return board;
    }
}
