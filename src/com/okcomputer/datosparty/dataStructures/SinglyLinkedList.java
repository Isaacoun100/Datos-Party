package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */

public class SinglyLinkedList<T> {

    private SinglyLinkedListNode<T> head;

    /**
     *
     * @return
     */
    public int getLength() {

        int length = 0;
        SinglyLinkedListNode<T> currentNode = this.head;

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
    public SinglyLinkedListNode<T> getNodeByIndex(int index) {
        int listLenght = getLength();
        if (index >= listLenght || index < 0) {
            System.out.println("Index out of range");
        } else {
            SinglyLinkedListNode<T> currentNode = this.head;
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
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        // If the Linked List is empty, then make the new node as head
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            SinglyLinkedListNode<T> last = this.head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            // Insert the newNode at last node
            last.setNext(newNode);
        }
    }

    /**
     *
     * @param data
     * @param index
     */
    public void insert(T data, int index) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            SinglyLinkedListNode<T> nodeNext = getNodeByIndex(index);
            newNode.setNext(nodeNext);
            if (index != 0) {
                SinglyLinkedListNode<T> nodePrevious = getNodeByIndex(--index);
                nodePrevious.setNext(newNode);
            } else {
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
            head = head.getNext();
        } else {
            SinglyLinkedListNode<T> nodeIndex = getNodeByIndex(index);
            SinglyLinkedListNode<T> nodePrevious = getNodeByIndex(--index);
            nodePrevious.setNext(nodeIndex.getNext());
        }

    }

    /**
     * Recursively traverse this list and print the node value
     *
     */
    public void print() {
        SinglyLinkedListNode<T> currentNode = this.head;

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

}
