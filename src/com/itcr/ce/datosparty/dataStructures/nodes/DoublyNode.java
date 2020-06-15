package com.itcr.ce.datosparty.dataStructures.nodes;

/**
 * Nodes with a reference of the Node previous and next to it
 * @param <T> Data type of the info stored on each Node
 */
public  class DoublyNode<T> extends SinglyNode<T> {

    protected Node<T> previous;

    /**
     * Constructor that sets the data to the node
     * @param data data of generic type saved on new node
     */
    public DoublyNode(T data) {
        super(data);
    }

    /**
     * Gets the node that it references as its previous
     * @return previous node
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Sets a node and puts it before it, according to the list order.
     * @param previous previous reference
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

}
