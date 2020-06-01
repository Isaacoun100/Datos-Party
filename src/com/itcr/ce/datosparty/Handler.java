package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.input.KeyManager;
import com.itcr.ce.datosparty.input.MouseManager;
import com.itcr.ce.datosparty.logic.Board;

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

    public Board getBoard() {
        return gameLoop.getBoard();
    }
}
