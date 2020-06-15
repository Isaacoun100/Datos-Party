package com.itcr.ce.datosparty.input;

import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * this class allows the mouse to be used for user input
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private UIManager uiManager;

    public MouseManager(){
    }

    /**
     * This method defines which UI will have access to the mouse manager, when set, objects on that UI can be interacted with
     * @param uiManager UI manager obj
     */
    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    /**
     * Unused method extended from Mouse Listener
     * @param e Mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * This method returns a true if a mouse button is pressed, BUTTON1 refers to the left click, BUTTON3 refers to the right click
     * @param e Mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            ;
        else if(e.getButton() == MouseEvent.BUTTON3)
            ;
    }

    /**
     * This method returns a false when the mouse button is released, BUTTON1 refers to the left click, BUTTON3 refers to the right click
     * @param e Mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            ;
        else if(e.getButton() == MouseEvent.BUTTON3)
            ;
        if(uiManager != null) {
            try {
                uiManager.onMouseRelease(e);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    /**
     * Unused method extended from Mouse Listener
     * @param e Mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unused method extended from Mouse Listener
     * @param e Mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Unused method extended from Mouse Listener
     * @param e Mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Unused method extended from Mouse Listener
     * @param e Mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(uiManager != null)
            uiManager.onMouseMove(e);

    }
}
