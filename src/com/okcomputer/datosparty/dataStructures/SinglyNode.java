package com.okcomputer.datosparty.dataStructures;

public  class SinglyNode<T> extends Node<T>{

    String id = "Singly";
    protected Node<T> next;

    public SinglyNode(T data) {
        super(data);
    }

    @Override
    public Node<T> getPrevious() {
        System.out.println("Singly doesn't reference previous node");
        return null;
    }

    @Override
    public void setPrevious(Node<T> previous) {
        System.out.println("Singly doesn't reference previous node");
    }

    @Override
    public Node<T> getNext() {
        return next;
    }

    @Override
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
