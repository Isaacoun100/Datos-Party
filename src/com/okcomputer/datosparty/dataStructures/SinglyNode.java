package com.okcomputer.datosparty.dataStructures;

public  class SinglyNode<T> extends Node<T>{

    private T data;
    private Node<T> next;

    public SinglyNode(T data) {
        super(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrevious() {
        System.out.println("Error: previous can only be referenced in DoublyNode");
        return null;
    }

    @Override
    public void setPrevious(Node<T> previous) {
        System.out.println("Error: previous can only be referenced in DoublyNode");
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
