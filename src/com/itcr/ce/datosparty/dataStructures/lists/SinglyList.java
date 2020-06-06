package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

/**
 *
 * @param <T>
 */
public class SinglyList<T> extends LinkedList<T> {

    @Override
    public void clear() {
        this.head = null;
        length = 0;
    }

    @Override
    public SinglyNode<T> getHead() {
        return (SinglyNode<T>) head;
    }

    @Override
    public SinglyNode<T> get(int index) {
        if (index >= getLength() || index < 0) {
            System.out.println("Index out of range");
            return null;
        } else {
            SinglyNode<T> currentNode = (SinglyNode<T>) this.head;
            for (int i = 0; i < index; i++) {
                currentNode = (SinglyNode<T>) currentNode.getNext();
            }
            return currentNode;
        }
    }

    /**
     * Returns index where the data is located
     * @param data first node on list that contains it
     * @return index where data is located
     */
    public int getIndexByData(T data) {
        SinglyNode<T> currentNode = (SinglyNode<T>) this.head;
        int index = 0;
        for (int i = 0; currentNode != null; i++) {
            if (currentNode.getData() == data) {
                index = i;
                break;
            } else if (currentNode.getNext() == null) {
                System.out.println("Not found");
                index = 404;
                break;
            } else {
                currentNode = (SinglyNode<T>) currentNode.getNext();
            }
        }
        return index;
    }

    @Override
    public SinglyNode<T> getLast() {
        SinglyNode<T> lastNode = (SinglyNode<T>) this.head;
        while (lastNode.getNext() != null) {
            lastNode = (SinglyNode<T>) lastNode.getNext();
        }
        return lastNode;
    }

    @Override
    public void add(T data) {
        // Create a new node with given data
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            SinglyNode<T> lastNode = getLast();
            // Insert the newNode at last node
            lastNode.setNext(newNode);
        }
        length++;
    }

    @Override
    public void add(T data, int index) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
            return;
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            SinglyNode<T> nodeNext = get(index);
            newNode.setNext(nodeNext);
            if (index > 0) {
                SinglyNode<T> nodePrevious = get(--index);
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
        } else if (index == 0) {
            this.head = ((SinglyNode<T>)this.head).getNext();
            length--;
        } else if (get(index).getNext() == null) {
            get(--index).setNext(null);
            length--;
        } else {
            SinglyNode<T> nodeIndex = get(index);
            SinglyNode<T> nodePrevious = get(--index);
            nodePrevious.setNext(nodeIndex.getNext());
            length--;
        }

    }

    @Override
    public void print() {
        SinglyNode<T> currentNode = (SinglyNode<T>) this.head;

        System.out.print("\n[");

        // Traverse through the LinkedList
        while (currentNode != null) {

            // Print the data at current node
            System.out.print(currentNode.getData());

            if (currentNode.getNext() != null) {
                System.out.print(", ");
            }

            // Go to next node
            currentNode = (SinglyNode<T>) currentNode.getNext();
        }
        System.out.println("]\n");
    }

    public void swap(Node<T> first, Node<T> second) {
        T temporalFirst = first.getData();
        first.setData(second.getData());
        second.setData(temporalFirst);
    }


}
