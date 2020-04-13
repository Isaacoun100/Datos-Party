package com.okcomputer.datosparty.datastructures;

public abstract class LinkedListNode<T> {

    private T data;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
