package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */
public class SinglyList<T> extends LinkedList<T>{

    private SinglyNode<T> head;

    @Override
    public void add(T data) {
        // Create a new node with given data
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            Node<T> lastNode = getLast();
            // Insert the newNode at last node
            lastNode.setNext(newNode);
        }
    }

    @Override
    public void add(Node<T> node) {
        insert(node);
    }

    @Override
    public void add(LinkedList<T> list) {
        insert(list.getHead());
    }

    @Override
    public void add(T data, int index) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            newNode.setNext(nodeNext);
            if (index > 0) {
                Node<T> nodePrevious = getNodeByIndex(--index);
                nodePrevious.setNext(newNode);
            } else {
                this.head = newNode;
            }
        }
    }

    @Override
    public void add(Node<T> node, int index) {
        insert(node, index);
    }

    @Override
    public void add(LinkedList<T> list, int index) {
        insert(list.getHead(), index);
    }

    @Override
    protected void insert(Node<T> newNode) {
        if (this.head == null) {
            if (newNode.getId().equals("Singly")) {
                this.head = (SinglyNode<T>) newNode;
            } else {
                System.out.println("For list to remain singly, a singly node or list should be added");
            }
        } else {
            // Else traverse till the last node and insert the newNode there
            Node<T> lastNode = getLast();
            // Insert the newNode at last node
            lastNode.setNext(newNode);
        }
    }

    @Override
    protected void insert(Node<T> newNode, int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null) {
            if (newNode.getId().equals("Singly")) {
                this.head = (SinglyNode<T>) newNode;
            } else {
                System.out.println("For list to remain singly, a singly node or list should be added");
            }
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            newNode.setNext(nodeNext);
            if (index > 0) {
                Node<T> nodePrevious = getNodeByIndex(--index);
                nodePrevious.setNext(newNode);
            } else {
                System.out.println("please add a class other than Node or List to the head");
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            if (this.head.getNext().getId().equals("Singly")) {
                this.head = (SinglyNode<T>) this.head.getNext();
            } else {
                System.out.println("For list to remain singly, first node of the list should be singly");
            }
        } else {
            Node<T> nodeIndex = getNodeByIndex(index);
            Node<T> nodePrevious = getNodeByIndex(--index);
            nodePrevious.setNext(nodeIndex.getNext());
        }

    }

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
