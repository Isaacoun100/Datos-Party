package com.okcomputer.datosparty.dataStructures;

public  abstract class Node<T> {

    protected String id;
    protected T data;

    public Node(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
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
