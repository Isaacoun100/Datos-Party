package com.okcomputer.datosparty.dataStructures;

public  class DoublyNode<T> extends Node<T>{

    private T data;
    private Node<T> previous;
    private Node<T> next;

    public DoublyNode(T data) {
        super(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
