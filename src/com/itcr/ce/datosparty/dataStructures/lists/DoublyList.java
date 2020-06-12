package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;

/**
 * LinkedList where each Node contains a reference to the next and previous Node
 * @param <T> Data type on Nodes
 */
public class DoublyList<T> extends LinkedList<T> {

    protected DoublyNode<T> head;

    @Override
    public void clear() {
        this.head = null;
        length = 0;
    }

    public DoublyNode<T> getHead() {
        return head;
    }

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

    public DoublyNode<T> getLast() {
        DoublyNode<T> lastNode = this.head;
        while (lastNode.getNext() != null) {
            lastNode = (DoublyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

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
    protected void removeReferences(DoublyNode<T> nodeIndex) {
        DoublyNode<T> nodePrevious = (DoublyNode<T>) nodeIndex.getPrevious();
        DoublyNode<T> nodeNext = (DoublyNode<T>) nodeIndex.getNext();
        nodePrevious.setNext(nodeNext);
        nodeNext.setPrevious(nodePrevious);

    }

    @Override
    public void print() {
        DoublyNode<T> currentNode = this.head;

        System.out.print("\n[[");

        // Traverse through the LinkedList
        while (currentNode != null) {

            // Print the data at current node
            System.out.print(currentNode.getData());

            if (currentNode.getNext() != null) {
                System.out.print(", ");
            }

            // Go to next node
            currentNode = (DoublyNode<T>) currentNode.getNext();
        }
        System.out.println("]]\n");
    }

}
