package com.itcr.ce.datosparty.dataStructures;

/**
 *
 * @param <T>
 */
public class SinglyList<T> extends LinkedList<T>{

    int length = 0;

    private SinglyNode<T> head;

    public SinglyNode<T> getHead() {
        return head;
    }

    public void clear() {
        this.head= null;
    }

    @Override
    public int getLength() {

//        int length = 0;
//        SinglyNode<T> currentNode = this.head;
//
//        while (currentNode != null) {
//            length++;
//            currentNode = currentNode.getNext();
//        }
      return length;
    }

    @Override
    public SinglyNode<T> getNodeByIndex(int index) {
        if (index >= getLength() || index < 0) {
            System.out.println("Index out of range");
            return null;
        } else {
            SinglyNode<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
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
        SinglyNode<T> currentNode = this.head;
        for (int i =0; currentNode != null; i++) {
            if (currentNode.getData() == data) {
                return i;
            } else if (currentNode.getNext() == null) {
                System.out.println("Not found");
                return 404;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return 404;
    }

    @Override
    public SinglyNode<T> getLast() {
        SinglyNode<T> lastNode = this.head;
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
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
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            SinglyNode<T> nodeNext = getNodeByIndex(index);
            newNode.setNext(nodeNext);
            if (index > 0) {
                SinglyNode<T> nodePrevious = getNodeByIndex(--index);
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
            this.head = this.head.getNext();
            length--;
        } else if (getNodeByIndex(index).getNext() == null) {
            getNodeByIndex(--index).setNext(null);
            length--;
        } else {
            SinglyNode<T> nodeIndex = getNodeByIndex(index);
            SinglyNode<T> nodePrevious = getNodeByIndex(--index);
            nodePrevious.setNext(nodeIndex.getNext());
            length--;
        }

    }

    @Override
    public void print() {
        SinglyNode<T> currentNode = this.head;

        System.out.print("\n[");

        // Traverse through the LinkedList
        while (currentNode != null) {

            // Print the data at current node
            System.out.print(currentNode.getData());

            if (currentNode.getNext() != null) {
                System.out.print(", ");
            }

            // Go to next node
            currentNode = currentNode.getNext();
        }
        System.out.println("]\n");
    }

    public void swap(Node<T> first, Node<T> second) {
        T temporalFirst = first.getData();
        first.setData(second.getData());
        second.setData(temporalFirst);
    }


}
