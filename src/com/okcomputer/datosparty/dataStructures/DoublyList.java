package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */
public class DoublyList<T> extends LinkedList<T>{

    protected DoublyNode<T> head;

    public DoublyNode<T> getHead() {
        return head;
    }

    public int getLength() {

        int length = 0;
        DoublyNode<T> currentNode = this.head;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    @Override
    public DoublyNode<T> getNodeByIndex(int index) {
        if (index >= getLength() || index < 0) {
            System.out.println("Index out of range");
            return null;
        } else {
            DoublyNode<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
    }

    public DoublyNode<T> getLast() {
        DoublyNode<T> lastNode = this.head;
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
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
    }

    @Override
    public void add(T data, int index) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            DoublyNode<T> nodeNext = getNodeByIndex(index);
            DoublyNode<T> nodePrevious = nodeNext.getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodeNext.setPrevious(newNode);
            if (index > 0) {
                nodePrevious.setNext(newNode);
            } else {
                this.head = newNode;
            }

        }
    }

    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else if (getNodeByIndex(index).getNext() == null) {
            getNodeByIndex(index).setPrevious(null);
            getNodeByIndex(--index).setNext(null);
        } else {
            DoublyNode<T> nodeIndex = getNodeByIndex(index);
            DoublyNode<T> nodePrevious = nodeIndex.getPrevious();
            DoublyNode<T> nodeNext = nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        }

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
            currentNode = currentNode.getNext();
        }
        System.out.println("]]\n");
    }

}
