package com.okcomputer.datosparty.dataStructures;

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
}
