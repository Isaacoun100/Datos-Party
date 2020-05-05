package com.okcomputer.datosparty.dataStructures;

public abstract class LinkedList<T> {

    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public int getLength() {

        int length = 0;
        Node<T> currentNode = this.head;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    public Node<T> getNodeByIndex(int index) {
        if (index >= getLength() || index < 0) {
            System.out.println("Index out of range");
        } else {
            Node<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Node<T> getLast() {
        Node<T> lastNode = this.head;
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }
        return lastNode;
    }

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

    public abstract void remove(int index);

    /**
     * Recursively traverse the list and print the node value
     */
    public abstract void print();
}
