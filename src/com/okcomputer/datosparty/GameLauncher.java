package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;

import java.awt.*;

public class GameLauncher {




    public static void main(String[] args){

        int screenHeight;
        int screenWidth;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
        GameLoop gameLoop = new GameLoop("Datos Party",800,608);
        gameLoop.start();

    }
}
