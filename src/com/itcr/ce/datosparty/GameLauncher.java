package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.music.LoopMusic;

public class GameLauncher {

    public static int width = 800*2;
    public static int height = 608*2;

    public static void main(String[] args){

        GameLoop gameLoop = new GameLoop("Datos Party",width,height);
        gameLoop.start();

        LoopMusic backgroundMusic = new LoopMusic();
        backgroundMusic.start();

    }
}
