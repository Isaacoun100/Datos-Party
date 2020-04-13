package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;

public class GameLauncher {
    public static void main(String[] args){
        GameLoop gameLoop = new GameLoop("Datos Party",800,600);
        gameLoop.start();
    }
}
