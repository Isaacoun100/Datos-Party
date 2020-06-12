package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

/**
 * SinglyList with simpler methods but harder access to nodes. Stacks Nodes on LIFO format.
 * @param <T> Data type on Nodes
 */
public class Stack<T> extends LinearStructure{

    SinglyNode<T> top;

    /**
     * Adds Node to the tail of the list
     * @param data on Node to be added on List
     */
    public void push(T data) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        newNode.setNext(top);
        top = newNode;
        length++;
    }

    /**
     * Removes last Node of the list
     * @return Data on last Node
     */
    public T pop() {
        if(top == null){
            return null;
        }
        SinglyNode<T> node = top;
        top = (SinglyNode<T>) top.getNext();
        length--;
        return node.getData();
    }

    /**
     * Peeks last Node of list
     * @return last Node
     */
    public T peek(){
        if(top == null){
            return null;
        }
        return top.getData();
    }

}
