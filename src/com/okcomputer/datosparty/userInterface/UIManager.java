package com.okcomputer.datosparty.userInterface;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.dataStructures.SinglyNode;


import java.awt.*;
import java.awt.event.MouseEvent;


public abstract class UIManager {

    private SinglyList<UIObject> objects;
    private SinglyNode<UIObject> current;

    public UIManager(Handler handler){
        objects =  new SinglyList<>();
    }

    public void tick(){
        current = objects.getHead();
        while (current != null) {
            current.getData().tick();
            current = current.getNext();
        }


    }

    public void render(Graphics g){
        current = objects.getHead();
        while (current != null) {
            current.getData().render(g);
            current = current.getNext();
        }
    }

    public void onMouseMove(MouseEvent e){
        current = objects.getHead();
        while (current != null) {
            current.getData().onMouseMove(e);
            current = current.getNext();
        }
    }

    public SinglyList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(SinglyList<UIObject> objects) {
        this.objects = objects;
    }

    public void onMouseRelease(MouseEvent e){
        current = objects.getHead();
        while (current != null) {
            current.getData().onMouseRelease(e);
            current = current.getNext();
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void removeObject(UIObject o){
        objects.getIndexByData(o);
    }
}
