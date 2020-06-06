package com.itcr.ce.datosparty.userInterface;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.Handler;


import java.awt.*;
import java.awt.event.MouseEvent;


public abstract class UIManager {

    private SinglyList<UIObject> objects;

    public UIManager(Handler handler){
        objects =  new SinglyList<>();
    }

    public void tick(){
        SinglyNode<UIObject> current;
        current = objects.getHead();
        while (current != null) {
            current.getData().tick();
            current = (SinglyNode<UIObject>) current.getNext();
        }
        
    }

    public void render(Graphics g){
        SinglyNode<UIObject> current;
        current = objects.getHead();
        while (current != null) {
            current.getData().render(g);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public void render(Graphics g, int renderID){
        SinglyNode<UIObject> current;
        current = objects.get(renderID);
        current.getData().render(g);
    }

    public void onMouseMove(MouseEvent e){
        SinglyNode<UIObject> current;
        current = objects.getHead();
        while (current != null) {
            current.getData().onMouseMove(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public SinglyList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(SinglyList<UIObject> objects) {
        this.objects = objects;
    }

    public void onMouseRelease(MouseEvent e) throws InterruptedException {
        SinglyNode<UIObject> current;
        current = objects.getHead();
        while (current != null) {
            current.getData().onMouseRelease(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void changeObjectPos(int objectIndex, int x, int y){
        UIObject o = objects.get(objectIndex).getData();
        o.x = x;
        o.y = y;
    }

    public void removeObject(UIObject o){
        objects.getIndexByData(o);
    }
}
