package com.okcomputer.datosparty.dataStructures;

public class CircularDoublyList<T> extends DoublyList<T> {

    /**
     *
     * @return
     */
    public int getLength() {

        int length = 0;
        Node<T> currentNode = this.head;

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


    public void addNode(Node<T> newNode) {
        // If the Linked List is empty, then make the new node as head
        insert(newNode);
    }

    /**
     *
     * @param data
     */
    public void addNode(T data) {
        // Create a new node with given data
        Node<T> newNode = new Node<>(data);

        // If the Linked List is empty, then make the new node as head
        insert(newNode);
    }

    public void addNode(Node<T> newNode, int index) {
        insert(newNode, index);
    }

    /**
     *
     * @param data
     * @param index
     */
    public void addNode(T data, int index) {
        Node<T> newNode = new Node<>(data);
        insert(newNode, index);
    }

    protected void insert(Node<T> newNode) {
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
            Node<T> lastNode = this.head.getNext();
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

    protected void insert(Node<T> newNode, int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null && index == 0) {
            newNode.setPrevious(newNode);
            newNode.setNext(newNode);
            this.head = newNode;
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            Node<T> nodePrevious = getNodeByIndex(index).getPrevious();
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
            Node<T> nodePrevious = head.getPrevious();
            Node<T> nodeNext = head.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        } else {
            Node<T> nodeIndex = getNodeByIndex(index);
            Node<T> nodePrevious = nodeIndex.getPrevious();
            Node<T> nodeNext = nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        }
    }

    /**
     * Recursively traverse this list and print the node value
     *
     */
    public void print() {
        Node<T> currentNode = this.head;

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
