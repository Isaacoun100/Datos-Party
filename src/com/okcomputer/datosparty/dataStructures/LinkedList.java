package com.okcomputer.datosparty.dataStructures;

public abstract class LinkedList<T> {

    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public abstract int getLength();

    public abstract Node<T> getNodeByIndex(int index);

    public abstract Node<T> getLast();

    /**
     *
     * @param data
     */
    public abstract void add(T data);

    /**
     *
     * @param node
     */
    public abstract void add(Node<T> node);

    /**
     *
     * @param list
     */
    public abstract void add(LinkedList<T> list);

    public abstract void add(T data, int index);

    public abstract void add(Node<T> node, int index);

    public abstract void add(LinkedList<T> list, int index);

    protected abstract void insert(Node<T> newNode);

    protected abstract void insert(Node<T> newNode, int index);

    public abstract void remove(int index);

    /**
     * Recursively traverse the list and print the node value
     */
    public abstract void print();
}
