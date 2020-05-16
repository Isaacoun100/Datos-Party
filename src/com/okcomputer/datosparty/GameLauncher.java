package com.okcomputer.datosparty;

import com.okcomputer.datosparty.music.LoopMusic;
import java.awt.*;

public class GameLauncher {

    public static void main(String[] args){

        GameLoop gameLoop = new GameLoop("Datos Party",800,608);
        gameLoop.start();

        LoopMusic backgroundMusic = new LoopMusic();
        backgroundMusic.start();

    }
}
