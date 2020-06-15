package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

/**
 * LinkedList where each Node contains a reference to the next, and the last Node references the first
 * @param <T> Data type on Nodes
 */
public class CircularList<T> extends SinglyList<T> {

    /**
     * Gets last node of list, stopping when the node that it's checking has a next that points to head
     * @return last node of list
     */
    @Override
    public SinglyNode<T> getLast() {
        SinglyNode<T> lastNode = (SinglyNode<T>) this.head.getNext();
        while (lastNode.getNext() != this.head) {
            lastNode = (SinglyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

    /**
     * Adds a node to the end of the list, with it's next reference pointing to null
     * @param data
     */
    @Override
    public void add(T data) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (this.head == null) {
            newNode.setNext(newNode);
            this.head = newNode;
        } else if (getLength() == 1) {
            newNode.setNext(this.head);
            this.head.setNext(newNode);
        } else {
            SinglyNode<T> lastNode = getLast();
            newNode.setNext(this.head);
            lastNode.setNext(newNode);
        }
        length++;
    }

    /**
     * Adds a node with data using an index as guide, where it's previous node will point to it and it's next node will point
     * to the next
     * @param data data data on the node that will be added to list
     * @param index where the node will be added
     */
    @Override
    public void add(T data, int index) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0 && this.head == null) {
            this.head = newNode;
            this.head.setNext(this.head);
        } else {
            SinglyNode<T> nodeNext = get(index);
            SinglyNode<T> nodePrevious = (SinglyNode<T>) get(index).getPrevious();
            newNode.setNext(nodeNext);
            nodePrevious.setNext(newNode);
            if (index == 0) {
                this.head = newNode;
            }
        }
        length++;
    }

    /**
     * Removes a node at the index it's given by setting the next reference of it's previous node to it's next
     * @param index where the node to remove is located
     */
    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
            return;
        } else if (index == 0) {
            SinglyNode<T> nodePrevious = (SinglyNode<T>) head.getPrevious();
            SinglyNode<T> nodeNext = (SinglyNode<T>) head.getNext();
            nodePrevious.setNext(nodeNext);
        } else if (get(index).getNext() == this.head) {
            get(--index).setNext(get(index).getNext());
        } else {
            SinglyNode<T> nodeIndex = get(index);
            SinglyNode<T> nodePrevious = (SinglyNode<T>) nodeIndex.getPrevious();
            SinglyNode<T> nodeNext = (SinglyNode<T>) nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
        }
        length--;
    }

    /**
     * Prints the data inside every node on the list in its order
     */
    public void print() {
        SinglyNode<T> currentNode = (SinglyNode<T>) this.head;
        System.out.print("\n]]");
        System.out.print(currentNode.getData());
        if (currentNode.getNext() != head) {
            System.out.print(", ");
        }
        currentNode = (DoublyNode<T>) currentNode.getNext();
        while (currentNode != this.head) {
            System.out.print(currentNode.getData());
            if (currentNode.getNext() != this.head) {
                System.out.print(", ");
            }
            currentNode = (DoublyNode<T>) currentNode.getNext();
        }
        System.out.println("[[\n");
    }

}
