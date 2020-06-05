package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

/**
 *
 * @param <T>
 */
public class CircularList<T> extends SinglyList<T> {

    public SinglyNode<T> getLast() {
        SinglyNode<T> lastNode = (SinglyNode<T>) this.head.getNext();
        while (lastNode.getNext() != this.head) {
            lastNode = (SinglyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

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

    //WIP
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

    //WIP
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

    public void print() {
        SinglyNode<T> currentNode = (SinglyNode<T>) this.head;

        System.out.print("\n]]");
        System.out.print(currentNode.getData());
        if (currentNode.getNext() != head) {
            System.out.print(", ");
        }
        currentNode = (DoublyNode<T>) currentNode.getNext();
        // Traverse through the LinkedList
        while (currentNode != this.head) {

            // Print the data at current node
            System.out.print(currentNode.getData());

            if (currentNode.getNext() != this.head) {
                System.out.print(", ");
            }

            // Go to next node
            currentNode = (DoublyNode<T>) currentNode.getNext();
        }
        System.out.println("[[\n");
    }

}
