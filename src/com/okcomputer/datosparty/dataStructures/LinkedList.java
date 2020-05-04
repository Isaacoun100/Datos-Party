package com.okcomputer.datosparty.dataStructures;

public abstract class LinkedList<T> {

    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public abstract int getLength();

    public abstract Node<T> getNodeByIndex(int index);

    public abstract void addNode(Node<T> newNode);

    public abstract void addNode(T data);

    public abstract void addNode(Node<T> newNode, int index);

    public abstract void addNode(T data, int index);

    protected abstract void insert(Node<T> newNode);

    protected abstract void insert(Node<T> newNode, int index);

    public abstract void remove(int index);

    public abstract void print();
}
