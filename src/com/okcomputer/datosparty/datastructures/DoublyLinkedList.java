package com.okcomputer.datosparty.datastructures;

public class DoublyLinkedList<T> {

    public DoublyLinkedListNode<T> head;

    /**
     *
     * @return
     */
    public int getLength() {

        int length = 0;
        DoublyLinkedListNode<T> currentNode = this.head;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    /**
     *
     * @param index
     * @return
     */
    public DoublyLinkedListNode<T> getNodeByIndex(int index) {
        if (index < 0 || index >= getLength()) {
            System.out.println("Index out of range");
        } else {
            DoublyLinkedListNode<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
        return null;
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
            this.head = newNode;
        } else {
            // Else traverse till the lastNode node and insert the newNode there
            DoublyLinkedListNode<T> lastNode = this.head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }

            // Insert the newNode at lastNode node
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
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
        } else if (this.head == null  && index == 0) {
            this.head = newNode;
        } else {
            DoublyLinkedListNode<T> nodeNext = getNodeByIndex(index);
            DoublyLinkedListNode<T> nodePrevious = nodeNext.getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodeNext.setPrevious(newNode);
            if (index > 0) {
                nodePrevious.setNext(newNode);
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
            head = head.getNext();
            head.setPrevious(null);
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
