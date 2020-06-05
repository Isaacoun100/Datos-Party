package com.itcr.ce.datosparty.dataStructures.nodes;

import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

public  class DoublyNode<T> extends SinglyNode<T> {

    protected Node<T> previous;

    public DoublyNode(T data) {
        super(data);
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

}
