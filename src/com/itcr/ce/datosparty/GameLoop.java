package com.itcr.ce.datosparty;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.input.MouseManager;
import com.itcr.ce.datosparty.input.KeyManager;
import com.itcr.ce.datosparty.display.Display;
import com.itcr.ce.datosparty.logic.Board;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;



/**
 * Main loop of the program, it continues running unless the program is closed
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
    public static State mainMenuState, creditsState, optionsState,
                        selectPlayerState, selectRoundState;
    public static SinglyList<State> gameDependantStates = new SinglyList<>();

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

    /**
     * This state is used by all classes to set a specific state as active
     * @param state obj that will be rendered and executed
     */
    public static void setState(State state){
        State.setState(state);
    }

    /**
     * Getter for the board and its phases
     * @return the board object, and all its phases
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Initialization method, sets up the main variables used throughout the program
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
        creditsState = new CreditsState(handler);
        optionsState = new OptionsState(handler);
        selectRoundState = new SelectRoundState(handler);
        selectPlayerState = new PlayerSelectionState(handler);

        State.setState(mainMenuState);

    }

    /**
     * Main tick method, it runs the tick manager, and pulls the data from each state to execute here
     * @throws InterruptedException
     */
    private void tick() throws InterruptedException {
        keyManager.tick();
        if (State.getState() != null)
            State.getState().tick();
    }

    /**
     * Main render method, it pulls the render information from each state, and renders it on the screen
     */
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

    /**
     * Main run method for the gameLoop, and the program itself,
     * it defines the tick and render, and the speed the execute in
     */
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
                try {
                    tick();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    /**
     * getter for the key manager, is only used by the handler
     * @return returns the key manager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * getter for the mouse manager, its only used by the handler
     * @return returns the mouse manager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    /**
     * Thread start method, its synchronized to avoid sync issues
     */
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Thread stop method, its synchronized to avoid sync issues
     */
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
