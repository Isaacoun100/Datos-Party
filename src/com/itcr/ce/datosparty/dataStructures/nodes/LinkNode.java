package com.itcr.ce.datosparty.dataStructures.nodes;

public class LinkNode<T> extends SinglyNode<T>{

    private Node<T> link;

    public LinkNode(T data) {
        super(data);
    }

    public Node<T> getLink() {
        return link;
    }

    public void setLink(Node<T> link) {
        this.link = link;
    }
}
