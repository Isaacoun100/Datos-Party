package com.itcr.ce.datosparty.dataStructures.lists;

import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

import java.util.EmptyStackException;

public class Stack<T> extends LinearStructure{

    SinglyNode<T> top;

    public void push(T data) {
        SinglyNode<T> newNode = new SinglyNode<>(data);
        newNode.setNext(top);
        top = newNode;
        length++;
    }

    public T pop() {
        if(top == null){
            return null;
        }
        SinglyNode<T> node = top;
        top = (SinglyNode<T>) top.getNext();
        length--;
        return node.getData();
    }

    public T peek(){
        if(top == null){
            return null;
        }
        return top.getData();
    }

}
