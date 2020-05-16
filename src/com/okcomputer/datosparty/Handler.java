package com.okcomputer.datosparty;

import com.okcomputer.datosparty.input.*;

public class Handler {

    private GameLoop gameLoop;

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public Handler(GameLoop gameLoop){
        this.gameLoop = gameLoop;
    }

    public KeyManager getKeyManager(){
        return gameLoop.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return gameLoop.getMouseManager();
    }

}
