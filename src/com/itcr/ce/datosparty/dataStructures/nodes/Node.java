package com.itcr.ce.datosparty.dataStructures.nodes;

public  abstract class Node<T> {

    protected T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public abstract Node<T> getPrevious();

    public abstract void setPrevious(Node<T> previous);

    public abstract Node<T> getNext();

    public abstract void setNext(Node<T> next);
}