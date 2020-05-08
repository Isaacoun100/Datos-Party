package com.okcomputer.datosparty.dataStructures;

public  class SinglyNode<T> extends Node<T>{

    protected SinglyNode<T> next;

    public SinglyNode(T data) {
        super(data);
    }

    public SinglyNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyNode<T> next) {
        this.next = next;
    }
}
