package com.okcomputer.datosparty.datastructures;

public class CircularDoublyList<T> extends DoublyLinkedList<T>{

    /**
     *
     * @return
     */
    public int getLength() {

        int length = 0;
        DoublyLinkedListNode<T> currentNode = this.head;

        if (currentNode != null) {
            currentNode = currentNode.getNext();
            length++;
            while (currentNode != this.head) {
                currentNode = currentNode.getNext();
                length++;
            }
        }
        return length;
    }

    /**
     *
     * @param data
     */
    public void insert(T data) {
        // Create a new node with given data
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);

        // If the Linked List is empty, then make the new node as head
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
            DoublyLinkedListNode<T> lastNode = this.head.getNext();
            // Else traverse till the lastNode node and insert the newNode there
            while (lastNode.getNext() != this.head) {
                lastNode = lastNode.getNext();
            }
            // Insert the newNode at lastNode node
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            lastNode.setNext(newNode);
            this.head.setPrevious(newNode);
        }
    }

    /**
     *
     * @param data
     * @param index
     */
    public void insert(T data, int index) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null && index == 0) {
            newNode.setPrevious(newNode);
            newNode.setNext(newNode);
            this.head = newNode;
        } else {
            DoublyLinkedListNode<T> nodeNext = getNodeByIndex(index);
            DoublyLinkedListNode<T> nodePrevious = getNodeByIndex(index).getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodePrevious.setNext(newNode);
            nodeNext.setPrevious(newNode);
            if (index == 0) {
                this.head = newNode;
            }
        }
    }

    /**
     *
     */
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            DoublyLinkedListNode<T> nodePrevious = head.getPrevious();
            DoublyLinkedListNode<T> nodeNext = head.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        } else {
            DoublyLinkedListNode<T> nodeIndex = getNodeByIndex(index);
            DoublyLinkedListNode<T> nodePrevious = nodeIndex.getPrevious();
            DoublyLinkedListNode<T> nodeNext = nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        }
    }

    /**
     * Recursively traverse this list and print the node value
     *
     */
    public void printList() {
        DoublyLinkedListNode<T> currentNode = this.head;

        System.out.print("\n]]");
        System.out.print(currentNode.getData() + ", ");
        currentNode = currentNode.getNext();

        // Traverse through the LinkedList
        while (currentNode != this.head) {

            // Print the data at current node
            System.out.print(currentNode.getData());

            if (currentNode.getNext() != this.head) {
                System.out.print(", ");
            }

            // Go to next node
            currentNode = currentNode.getNext();
        }
        System.out.println("[[\n");
    }

}
