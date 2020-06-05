package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.LinkNode;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;

public abstract class LinkedList<T> extends LinearStructure {

    protected Node<T> head;

    /**
     * Used tu connect lists with each other
     * @param index node where the list will be connected
     * @param list to connect
     */
    public void connect(LinkedList<T> list, int index) {
        Node<T> nodePrevious;
        LinkNode<T> connection;
        Node<T> nodeNext;
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            System.out.println("Can't connect on head");
        } else if (get(index) == getLast()) {
            nodePrevious = get(--index);
            connection = new LinkNode<>(get(++index).getData());
            nodePrevious.setNext(connection);
            connection.setLink(list.getHead());
        } else {
            nodePrevious = get(--index);
            connection = new LinkNode<>(get(++index).getData());
            nodeNext = get(++index);
            nodePrevious.setNext(connection);
            connection.setLink(list.getHead());
            connection.setNext(nodeNext);
        }
    }

    /**
     * Clears contents on list
     */
    public abstract void clear();

    /**
     * returns first node of list
     * @return first node of list
     */
    public abstract Node<T> getHead();

    /**
     * Returns last Node of List
     * @return last Node
     */
    public abstract Node<T> getLast();

    /**
     * Returns Node on given position on index
     * @param index of node
     * @return Node indexed
     */
    public abstract Node<T> get(int index);

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
