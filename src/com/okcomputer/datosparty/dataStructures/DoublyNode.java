package com.okcomputer.datosparty.dataStructures;

public  class DoublyNode<T> extends SinglyNode<T>{

    String id = "Doubly";
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
