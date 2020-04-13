package com.okcomputer.datosparty.dataStructures;

public class DoublyLinkedListNode<T> extends LinkedListNode<T>{

    private DoublyLinkedListNode<T> previous;
    private DoublyLinkedListNode<T> next;

    public DoublyLinkedListNode(T data) {
        super(data);
    }

    public void setPrevious(DoublyLinkedListNode<T> previous) {
        this.previous = previous;
    }

    public DoublyLinkedListNode<T> getPrevious() {
        return previous;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public DoublyLinkedListNode<T> getNext() {
        return next;
    }
}
