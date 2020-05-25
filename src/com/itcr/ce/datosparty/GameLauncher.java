package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.music.LoopMusic;

public class GameLauncher {

    public static void main(String[] args){

        GameLoop gameLoop = new GameLoop("Datos Party",800,608);
        gameLoop.start();

        LoopMusic backgroundMusic = new LoopMusic();
        backgroundMusic.start();

    }
}
