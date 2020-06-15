package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;

/**
 * LinkedList where each Node contains a reference to the next and previous Node
 * @param <T> Data type on Nodes
 */
public class DoublyList<T> extends LinkedList<T> {

    protected DoublyNode<T> head;

    /**
     * Deletes all the contents of the list by setting its head to null
     */
    @Override
    public void clear() {
        this.head = null;
        length = 0;
    }

    /**
     * Gets the first element of the list
     * @return first element of the list
     */
    public DoublyNode<T> getHead() {
        return head;
    }

    /**
     * Gets the node on the index position. Used on CircularDoublyList
     * @param index where Node is located
     * @return node on index
     */
    @Override
    public DoublyNode<T> get(int index) {
        if (index >= getLength() || index < 0) {
            System.out.println("Index out of range");
            return null;
        } else {
            DoublyNode<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = (DoublyNode<T>) currentNode.getNext();
            }
            return currentNode;
        }
    }

    /**
     * Gets last node of list, the one which has its next pointing to null
     * @return
     */
    public DoublyNode<T> getLast() {
        DoublyNode<T> lastNode = this.head;
        while (lastNode.getNext() != null) {
            lastNode = (DoublyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

    /**
     * Adds node to the end of the list, making the last node's next point to it, then its next pointing to null
     * @param data info to be added on List
     */
    @Override
    public void add(T data) {
        // Create a new node with given data
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            DoublyNode<T> lastNode = getLast();
            // Insert the newNode at last node
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
        }
        length++;
    }

    /**
     * Adds a node where on the index location by setting its previous and next references accordingly
     * @param data info to be added on List
     * @param index where the Node will be added
     */
    @Override
    public void add(T data, int index) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
            return;
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            DoublyNode<T> nodeNext = get(index);
            DoublyNode<T> nodePrevious = (DoublyNode<T>) nodeNext.getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodeNext.setPrevious(newNode);
            if (index > 0) {
                nodePrevious.setNext(newNode);
            } else {
                this.head = newNode;
            }
        }
        length++;
    }

    /**
     * Removes the node located on index, pointing the previous and next references of the nodes around it accordingly
     * @param index where Node to be removed is located
     */
    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
            return;
        } else if (index == 0) {
            this.head = (DoublyNode<T>) this.head.getNext();
            this.head.setPrevious(null);
        } else if (get(index).getNext() == null) {
            get(index).setPrevious(null);
            get(--index).setNext(null);
        } else {
            removeReferences(get(index));
        }
        length--;
    }

    /**
     * Removes indexed Node when the node to remove is not the first nor last one ont the list
     * @param nodeIndex Node to be removed
     */
    protected void removeReferences(DoublyNode<T> nodeIndex) {
        DoublyNode<T> nodePrevious = (DoublyNode<T>) nodeIndex.getPrevious();
        DoublyNode<T> nodeNext = (DoublyNode<T>) nodeIndex.getNext();
        nodePrevious.setNext(nodeNext);
        nodeNext.setPrevious(nodePrevious);

    }

    /**
     * Prints the data inside every node on the list in its order
     */
    @Override
    public void print() {
        DoublyNode<T> currentNode = this.head;
        System.out.print("\n[[");
        while (currentNode != null) {
            System.out.print(currentNode.getData());
            if (currentNode.getNext() != null) {
                System.out.print(", ");
            }
            currentNode = (DoublyNode<T>) currentNode.getNext();
        }
        System.out.println("]]\n");
    }

}
