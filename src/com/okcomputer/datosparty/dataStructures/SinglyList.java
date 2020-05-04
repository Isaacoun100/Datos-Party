package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */

public class SinglyList<T> extends LinkedList<T>{

    private Node<T> head;

    /**
     *
     * @return
     */
    @Override
    public int getLength() {

        int length = 0;
        Node<T> currentNode = this.head;

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
    @Override
    public Node<T> getNodeByIndex(int index) {
        int listLenght = getLength();
        if (index >= listLenght || index < 0) {
            System.out.println("Index out of range");
        } else {
            Node<T> currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
        return null;
    }
    @Override
    public void addNode(Node<T> newNode) {
        // If the Linked List is empty, then make the new node as head
        insert(newNode);
    }

    /**
     *
     * @param data
     */
    @Override
    public void addNode(T data) {

        // Create a new node with given data
        Node<T> newNode = new SinglyNode<>(data);
        // If the Linked List is empty, then make the new node as head
        insert(newNode);
    }

    /**
     *
     * @param newNode
     * @param index
     */
    @Override
    public void addNode(Node<T> newNode, int index) {
        insert(newNode, index);
    }

    /**
     *
     * @param data
     * @param index
     */
    @Override
    public void addNode(T data, int index) {
        Node<T> newNode = new SinglyNode<>(data);
        insert(newNode, index);
    }

    @Override
    protected void insert(Node<T> newNode) {
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            Node<T> last = this.head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            // Insert the newNode at last node
            last.setNext(newNode);
        }
    }

    @Override
    protected void insert(Node<T> newNode, int index) {
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            newNode.setNext(nodeNext);
            if (index != 0) {
                Node<T> nodePrevious = getNodeByIndex(--index);
                nodePrevious.setNext(newNode);
            } else {
                this.head = newNode;
            }
        }
    }

    /**
     *
     */
    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            head = head.getNext();
        } else {
            Node<T> nodeIndex = getNodeByIndex(index);
            Node<T> nodePrevious = getNodeByIndex(--index);
            nodePrevious.setNext(nodeIndex.getNext());
        }

    }

    /**
     * Recursively traverse this list and print the node value
     *
     */
    @Override
    public void print() {
        Node<T> currentNode = this.head;

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
