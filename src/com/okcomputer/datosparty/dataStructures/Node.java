package com.okcomputer.datosparty.dataStructures;

public  abstract class Node<T> {

    private T data;

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
