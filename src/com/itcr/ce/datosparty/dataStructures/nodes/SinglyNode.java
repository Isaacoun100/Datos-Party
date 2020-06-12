package com.itcr.ce.datosparty.dataStructures.nodes;

/**
 * Nodes with a reference of the Node that follows it
 * @param <T> Data type of the info stored on each Node
 */
public  class SinglyNode<T> extends Node<T> {

    protected Node<T> next;

    public SinglyNode(T data) {
        super(data);
    }

    @Override
    public Node<T> getPrevious() {
        System.out.println("SinglyNode doesn't have previous reference");
        return null;
    }

    @Override
    public void setPrevious(Node<T> previous) {
        System.out.println("SinglyNode doesn't have previous reference");
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}
