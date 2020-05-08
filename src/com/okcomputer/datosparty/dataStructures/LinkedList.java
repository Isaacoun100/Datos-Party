package com.okcomputer.datosparty.dataStructures;

public abstract class LinkedList<T> {

    /**
     *
     * @return
     */
    public abstract int getLength();

    /**
     *
     * @param index
     * @return
     */
    public abstract Node<T> getNodeByIndex(int index);

    /**
     *
     * @return
     */
    public abstract Node<T> getLast();

    /**
     *
     * @param data
     */
    public abstract void add(T data);

    /**
     *
     * @param data
     * @param index
     */
    public abstract void add(T data, int index);

    /**
     *
     * @param index
     */
    public abstract void remove(int index);

    /**
     * Recursively traverse the list and print the node value
     */
    public abstract void print();
}
