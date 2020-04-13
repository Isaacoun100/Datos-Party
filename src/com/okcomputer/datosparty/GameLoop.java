package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;

public class GameLoop {

    private Display display;
    public int width, height;

    public GameLoop(String title, int width, int height){
        this.width = width;
        this.height = height;

        display = new Display(title,width,height);

    }
}
