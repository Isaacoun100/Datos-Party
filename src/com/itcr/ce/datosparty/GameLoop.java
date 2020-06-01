package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.input.MouseManager;
import com.itcr.ce.datosparty.display.Display;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.input.KeyManager;

import com.itcr.ce.datosparty.logic.Board;
import com.itcr.ce.datosparty.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 *
 */
public class GameLoop implements Runnable {

    /**
     * General Variable Initialization
     */
    private Display display;
    public String title;
    public int width, height;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    /**
     * State Initialization
     */
    public static State gameState, mainMenuState, titleScreenState, creditsState, optionsState, endGameState, boardState,
                            winnerState, selectPlayerState;

    /**
     * Input Initialization
     */

    private KeyManager keyManager;
    private MouseManager mouseManager;

    /**
     * Handler
     */
    private Handler handler;

//
    private Board board;
    //public Player testPlayer; // this will later be a list and we will add players to it, depending on selection

    /**
     * Main Game Loop, runs the entire program, it can handle multiple states, for different options
     *
     * @param title  the title displayed on the screen
     * @param width  the width of the screen
     * @param height the height of the screen
     */
    public GameLoop(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    public static void setState(State state){
        State.setState(state);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Initialization method, this runs variables that are used in the game
     */
    private void init(){


        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        board = new Board();

        //testPlayer = new Player(handler);

        handler = new Handler(this);

        mainMenuState = new MainMenuState(handler);
        gameState = new GameState(handler);
        creditsState = new CreditsState(handler);
        optionsState = new OptionsState(handler);
        endGameState = new EndgameState(handler);
        boardState = new BoardState(handler);
        winnerState = new WinnerState(handler);
        selectPlayerState = new PlayerSelectionState(handler);
        State.setState(mainMenuState);

        //game = new Game(handler, 5);
        //game.start();
        State.setState(mainMenuState);

    }

    private void tick() {

        keyManager.tick();
        if (State.getState() != null)
            State.getState().tick();
    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        // Begin Rendering

        if (State.getState() != null)
            State.getState().render(g);

        // End Rendering
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        float timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }


    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
