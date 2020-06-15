package com.itcr.ce.datosparty.userInterface;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.Handler;


import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * a class that handles objects rendered in it, it needs to be declared in a separate state in order to render UI objects
 */
public class UIManager {

    private final SinglyList<UIObject> uiObjects;

    /**
     * constructor the the UI manager, it creates a singly list of uiObjects to store different objects in
     * @param handler
     */
    public UIManager(Handler handler){
        uiObjects =  new SinglyList<>();
    }

    /**
     * tick method for the UI manager, when called it makes sure that objects in the ui manager can be interacted with
     */
    public void tick(){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().tick();
            current = (SinglyNode<UIObject>) current.getNext();
        }
        
    }

    /**
     * This method renders all objects in a UI manager
     * @param g java.awt graphics object
     */
    public void renderAll(Graphics g){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().render(g);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    /**
     * this method renders a specific item from the list using the String id
     * @param g java.awt Graphics object
     * @param id String related to the id of the object
     */
    public void renderById(Graphics g, String id){
        int renderID = retrieveIndexByID(id);
        SinglyNode<UIObject> current;
        current = uiObjects.get(renderID);
        current.getData().render(g);
    }

    /**
     * method created to render an object using the int index of it
     * @param g java.awt graphics object
     * @param index int that corresponds to the index of the object in the list
     */
    public void renderByIndex(Graphics g, int index){
        SinglyNode<UIObject> current;
        current = uiObjects.get(index);
        current.getData().render(g);
    }

    /**
     * this method checks an objects id, and returns the index of it,
     * so that an image can be called easily utilizing its string ID
     * @param id String id related to an image when it was initialized
     * @return an int corresponding to an object's ID
     */
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

    /**
     * this method checks if the mouse is moved on the screen, and returns the position,
     * so that interactive objects can use this data
     * @param e mouse event
     */
    public void onMouseMove(MouseEvent e){
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().onMouseMove(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    /**
     * this checks if the mouse was released, so that interactive objects can utilize the data
     * @param e mouse event
     * @throws InterruptedException this is needed because some interactive objects stop or sleep threads
     */
    public void onMouseRelease(MouseEvent e) throws InterruptedException {
        SinglyNode<UIObject> current;
        current = uiObjects.getHead();
        while (current != null) {
            current.getData().onMouseRelease(e);
            current = (SinglyNode<UIObject>) current.getNext();
        }
    }

    /**
     * this method adds an object to the UI manager's object list
     * @param o UI manager object
     */
    public void addObject(UIObject o){
        uiObjects.add(o);
    }

    /**
     * this method changes the x and y value of an object, and thus it renders on a different position
     * @param id String id of the object
     * @param x int value of the nex X position
     * @param y int value of the new Y position
     */
    public void changeObjectPos(String id, int x, int y){
        int objectIndex = retrieveIndexByID(id);
        UIObject o = uiObjects.get(objectIndex).getData();
        o.x = x;
        o.y = y;
    }

    /**
     * removes an object from the object list
     * @param id String id of the object
     */
    public void removeObject(String id){
        int index = retrieveIndexByID(id);
        uiObjects.remove(index);
    }

}
