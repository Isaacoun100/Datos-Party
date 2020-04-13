package com.okcomputer.datosparty.dataStructures;

/**
 *
 * @param <T>
 */

public class SinglyLinkedListNode<T> extends LinkedListNode<T> {

    private SinglyLinkedListNode<T> next;

    public SinglyLinkedListNode(T data) {
        super(data);
    }

    public SinglyLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }
}
