package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */
public class DoublyList<T> extends LinkedList<T>{

    protected DoublyNode<T> head;

    @Override
    public void add(T data) {
        // Create a new node with given data
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Else traverse till the last node and insert the newNode there
            Node<T> lastNode = getLast();
            // Insert the newNode at last node
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
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
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            Node<T> nodePrevious = nodeNext.getPrevious();
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
            if (newNode.getId().equals("Doubly")) {
                this.head = (DoublyNode<T>) newNode;
            } else {
                System.out.println("For list to remain doubly, a doubly node or list should be added");
            }
        } else {
            // Else traverse till the lastNode node and insert the newNode there
            Node<T> lastNode = getLast();
            // Insert the newNode at lastNode node
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
        }

    }

    @Override
    protected void insert(Node<T> newNode, int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (this.head == null) {
            if (newNode.getId().equals("Doubly")) {
                this.head = (DoublyNode<T>) newNode;
            } else {
                System.out.println("For list to remain doubly, a doubly node or list should be added");
            }
        } else {
            Node<T> nodeNext = getNodeByIndex(index);
            Node<T> nodePrevious = nodeNext.getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodeNext.setPrevious(newNode);
            if (index > 0) {
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
            if (this.head.getNext().getId().equals("Doubly")) {
                this.head = (DoublyNode<T>) this.head.getNext();
                this.head.setPrevious(null);
            } else {
                System.out.println("For list to remain doubly, first node of the list should be doubly");
            }
        } else {
            Node<T> nodeIndex = getNodeByIndex(index);
            Node<T> nodePrevious = nodeIndex.getPrevious();
            Node<T> nodeNext = nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        }

    }

    @Override
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
