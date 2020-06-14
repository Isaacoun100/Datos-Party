package com.itcr.ce.datosparty.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    public boolean enter, esc, space,key_Q,key_Z,key_P,key_M,
            numPad1, numPad2, numPad3, num1, num2, num3;

    /**
     * KeyManager constructor, simply creates an array of keys, that corresponds to the average number of keys on a keyboard
     * its an extended class from KeyListener
     */
    public KeyManager(){
        keys = new boolean[256];
    }

    /**
     * tick method for keyManager, it constantly checks if one of the specified keys has been pressed
     */
    public void tick(){
        enter = keys[KeyEvent.VK_ENTER];
        esc = keys[KeyEvent.VK_ESCAPE];
        space = keys[KeyEvent.VK_SPACE];
        key_Q = keys[KeyEvent.VK_Q];
        key_Z = keys[KeyEvent.VK_Z];
        key_P = keys[KeyEvent.VK_P];
        key_M = keys[KeyEvent.VK_M];
        numPad1 = keys[KeyEvent.VK_NUMPAD1];
        numPad2 = keys[KeyEvent.VK_NUMPAD2];
        numPad3 = keys[KeyEvent.VK_NUMPAD3];
        num1 = keys[KeyEvent.VK_1];
        num2 = keys[KeyEvent.VK_2];
        num3 = keys[KeyEvent.VK_3];
    }

    /**
     * Unused method extended from parent class
     * @param e key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method checks if a key determined above is pressed, by returning a true boolean value
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    /**
     * This method changes the value of a key to false, if the key is not being pressed
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
