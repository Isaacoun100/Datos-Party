package com.okcomputer.datosparty.dataStructures;

public  class DoublyNode<T> extends Node<T>{

    protected DoublyNode<T> previous;
    protected DoublyNode<T> next;

    public DoublyNode(T data) {
        super(data);
    }

    public DoublyNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }

    public DoublyNode<T> getNext () {
        return next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }
}
