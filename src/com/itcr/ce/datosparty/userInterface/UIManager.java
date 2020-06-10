package com.itcr.ce.datosparty.userInterface;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.Handler;


import java.awt.*;
import java.awt.event.MouseEvent;


public class UIManager {

    private final SinglyList<UIObject> uiObjects;

    public UIManager(Handler handler){
        uiObjects =  new SinglyList<>();
    }

    public void tick(){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().tick();
            current = (SinglyNode<UIObject>) current.getNext();
        }
        
    }

    public void renderAll(Graphics g){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().render(g);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public void renderById(Graphics g, String id){
        int renderID = retrieveIndexByID(id);
        SinglyNode<UIObject> current;
        current = uiObjects.get(renderID);
        current.getData().render(g);
    }

    public void renderByIndex(Graphics g, int index){
        SinglyNode<UIObject> current;
        current = uiObjects.get(index);
        current.getData().render(g);
    }

    private int retrieveIndexByID(String id){
        Node<UIObject> current = uiObjects.get(0);
        for(int i=0; i< uiObjects.getLength(); i++){
            if(id.equals(current.getData().getId())){
                return i;
            } else{
                current = current.getNext();
            }
        }
        return -1;
    }

    public void onMouseMove(MouseEvent e){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().onMouseMove(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public void onMouseRelease(MouseEvent e) throws InterruptedException {
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().onMouseRelease(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    public void addObject(UIObject o){
        uiObjects.add(o);
    }

    public void changeObjectPos(int objectIndex, int x, int y){
        UIObject o = uiObjects.get(objectIndex).getData();
        o.x = x;
        o.y = y;
    }

    public void removeObject(String id){
        int index = retrieveIndexByID(id);
        uiObjects.remove(index);
    }
}
