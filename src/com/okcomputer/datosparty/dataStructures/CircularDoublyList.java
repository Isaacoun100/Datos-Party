package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */
public class CircularDoublyList<T> extends DoublyList<T> {

    //WIP
    @Override
    public int getLength() {

        int length = 0;
        DoublyNode<T> currentNode = this.head;
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

    public DoublyNode<T> getLast() {
        DoublyNode<T> lastNode = this.head.getNext();
        while (lastNode.getNext() != this.head) {
            lastNode = lastNode.getNext();
        }
        return lastNode;
    }

    @Override
    public void add(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
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
            DoublyNode<T> lastNode = getLast();
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            lastNode.setNext(newNode);
            this.head.setPrevious(newNode);
        }
    }

    //WIP
    @Override
    public void add(T data, int index) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0 && this.head == null) {
            newNode.setPrevious(newNode);
            newNode.setNext(newNode);
            this.head = newNode;
        } else {
            DoublyNode<T> nodeNext = getNodeByIndex(index);
            DoublyNode<T> nodePrevious = getNodeByIndex(index).getPrevious();
            newNode.setNext(nodeNext);
            newNode.setPrevious(nodePrevious);
            nodePrevious.setNext(newNode);
            nodeNext.setPrevious(newNode);
            if (index == 0) {
                this.head = newNode;
            }
        }
    }

    //WIP
    @Override
    public void remove(int index) {
        if (index >= getLength()) {
            System.out.println("Index out of range");
        } else if (index == 0) {
            DoublyNode<T> nodePrevious = head.getPrevious();
            DoublyNode<T> nodeNext = head.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        } else if (getNodeByIndex(index).getNext() == this.head) {
            getNodeByIndex(--index).setNext(getNodeByIndex(index).getNext());
            getNodeByIndex(++index).setPrevious(getNodeByIndex(index).getPrevious());
        } else {
            DoublyNode<T> nodeIndex = getNodeByIndex(index);
            DoublyNode<T> nodePrevious = nodeIndex.getPrevious();
            DoublyNode<T> nodeNext = nodeIndex.getNext();
            nodePrevious.setNext(nodeNext);
            nodeNext.setPrevious(nodePrevious);
        }
    }

    public void print() {
        DoublyNode<T> currentNode = this.head;

        System.out.print("\n]]");
        System.out.print(currentNode.getData());
        if (currentNode.getNext() != head) {
            System.out.print(", ");
        }
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
