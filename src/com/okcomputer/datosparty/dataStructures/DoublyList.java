package com.okcomputer.datosparty.dataStructures;

public class DoublyList<T> extends LinkedList<T>{

    public Node<T> head;

    /**
     *
     * @return
     */
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
    public Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= getLength()) {
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
        insert(newNode);
    }

    /**
     *
     * @param data
     */
    public void addNode(T data) {
        // Create a new node with given data
        Node<T> newNode = new DoublyNode<>(data);
        // If the Linked List is empty, then make the new node as head
        insert(newNode);

    }

    @Override
    public void addNode(Node<T> newNode, int index) {
        insert(newNode, index);
    }

    /**
     *
     * @param data
     * @param index
     */
    public void addNode(T data, int index) {
        Node<T> newNode = new DoublyNode<>(data);
        insert(newNode, index);

    }

    @Override
    protected void insert(Node<T> newNode) {

        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the lastNode node and insert the newNode there
            Node<T> lastNode = this.head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }

            // Insert the newNode at lastNode node
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
        }

    }

    @Override
    protected void insert(Node<T> newNode, int index) {

        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null  && index == 0) {
            this.head = newNode;
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            Node<T> nodePrevious = nodeNext.getPrevious();
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
