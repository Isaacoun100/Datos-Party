package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.input.KeyManager;
import com.itcr.ce.datosparty.input.MouseManager;
import com.itcr.ce.datosparty.logic.Board;

/**
 * The handler class is a middle point that restricts what information can be pulled from the GameLoop class
 * this class just forwards data from the main gameLoop
 */
public class Handler {

    private final GameLoop gameLoop;

    /**
     * constructor for the handler, it takes the gameLoop and sets it as a local variable
     * @param gameLoop gameLoop obj
     */
    public Handler(GameLoop gameLoop){
        this.gameLoop = gameLoop;
    }

    /**
     * this returns the gameLoop
     * @return the gameLoop object
     */
    public GameLoop getGameLoop() {
        return gameLoop;
    }

    /**
     * getter for the keyManager
     * @return the single keyManager obj, used to register key input
     */
    public KeyManager getKeyManager(){
        return gameLoop.getKeyManager();
    }

    /**
     * getter for the MouseManager
     * @return the single MouseManager obj, used to register mouse input
     */
    public MouseManager getMouseManager(){
        return gameLoop.getMouseManager();
    }

    /**
     * getter for the board of the game
     * @return the board class, and thus access to all phases
     */
    public Board getBoard() {
        return gameLoop.getBoard();
    }
}
