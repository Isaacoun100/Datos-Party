package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;

/**
 * LinkedList where each Node contains a reference to the next and previous Node, and the last Node references the first
 * @param <T> Data type on Nodes
 */
public class CircularDoublyList<T> extends DoublyList<T> {

    /**
     * Gets last node of list, stopping when the node that it's checking has a next that points to head
     * @return last node of list
     */
    @Override
    public DoublyNode<T> getLast() {
        DoublyNode<T> lastNode = (DoublyNode<T>) this.head.getNext();
        while (lastNode.getNext() != this.head) {
            lastNode = (DoublyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

    /**
     * Adds data to the end of the list; the node which has a next reference that points to the head
     * @param data data on the node to be added
     */
    @Override
    public void add(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (this.head == null) {
            newNode.setPrevious(newNode);
            newNode.setNext(newNode);
            this.head = newNode;
        } else if (getLength() == 1) {
            newNode.setPrevious(this.head);
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
            this.head.setNext(newNode);
        } else {
            DoublyNode<T> lastNode = getLast();
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            lastNode.setNext(newNode);
            this.head.setPrevious(newNode);
        }
        length++;
    }

    /**
     * Adds a node with data, linking it's previous and next reference where it's commanded
     * @param data data on the node that will be added to list
     * @param index where the node will be added
     */
    @Override
    public void add(T data, int index) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0 && this.head == null) {
            this.head = newNode;
            this.head.setPrevious(this.head);
            this.head.setNext(this.head);
        } else {
            DoublyNode<T> nodeNext = get(index);
            DoublyNode<T> nodePrevious = (DoublyNode<T>) get(index).getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodePrevious.setNext(newNode);
            nodeNext.setPrevious(newNode);
            if (index == 0) {
                this.head = newNode;
            }
        }
        length++;
    }

    /**
     * Removes a node where the index integer leads to; rearranging it's previous and next references
     * @param index index where the node to remove is located
     */
    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
            return;
        } else if (index == 0) {
            DoublyNode<T> nodePrevious = (DoublyNode<T>) head.getPrevious();
            DoublyNode<T> nodeNext = (DoublyNode<T>) head.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        } else if (get(index).getNext() == this.head) {
            get(--index).setNext(get(index).getNext());
            get(++index).setPrevious(get(index).getPrevious());
        } else {
            removeReferences(get(index));
        }
        length--;
    }

    /**
     * Prints the data inside every node on the list in its order
     */
    public void print() {
        DoublyNode<T> currentNode = this.head;
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
