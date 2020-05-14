package com.okcomputer.datosparty.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    // remember to change to Gabo's data structures
    private boolean[] keys;
    public boolean enter, esc;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        enter = keys[KeyEvent.VK_ENTER];
        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
