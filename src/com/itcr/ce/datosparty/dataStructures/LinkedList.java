package com.itcr.ce.datosparty.dataStructures;

public abstract class LinkedList<T> {

    /**
     * Returns length of list
     * @return int length
     */
    public abstract int getLength();

    /**
     * Returns Node on given position on index
     * @param index of node
     * @return Node indexed
     */
    public abstract Node<T> getNodeByIndex(int index);

    /**
     * Returns last Node of List
     * @return last Node of list
     */
    public abstract Node<T> getLast();

    /**
     * Adds a Node to the List
     * @param data given to the Node to be added to List
     */
    public abstract void add(T data);

    /**
     * Adds a Node to list on given index
     * @param data given to the Node to be added to List
     * @param index where the Node will be added
     */
    public abstract void add(T data, int index);

    /**
     * Remove a Node on the List on given index
     * @param index of the Node to be removed
     */
    public abstract void remove(int index);

    /**
     * Recursively traverse the list and print the node value
     */
    public abstract void print();
}
