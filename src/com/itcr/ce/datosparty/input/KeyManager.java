package com.itcr.ce.datosparty.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    public boolean enter, esc, space,key_Q,key_Z,key_P,key_M;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        enter = keys[KeyEvent.VK_ENTER];
        esc = keys[KeyEvent.VK_ESCAPE];
        space = keys[KeyEvent.VK_SPACE];
        key_Q = keys[KeyEvent.VK_Q];
        key_Z = keys[KeyEvent.VK_Z];
        key_P = keys[KeyEvent.VK_P];
        key_M = keys[KeyEvent.VK_M];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
