package com.itcr.ce.datosparty.dataStructures.nodes;

/**
 * Nodes with a reference of the Node previous and next to it
 * @param <T> Data type of the info stored on each Node
 */
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
