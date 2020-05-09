package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.input.KeyManager;
import com.okcomputer.datosparty.input.MouseManager;
import com.okcomputer.datosparty.states.*;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

/**
 *
 */
public class GameLoop implements Runnable{

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
    public State gameState, mainMenuState, titleScreenState, creditsState, settingsState, endGameState;

    /**
     * Input Initialization
     */
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private UIManager uiManager;

    /**
     * Handler
     */
    private Handler handler;

    /**
     * Main Game Loop, runs the entire program, it can handle multiple states, for different options
     * @param title the title displayed on the screen
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public GameLoop(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        uiManager = new UIManager();

    }

    /**
     * Initialization method, this runs variables that are used in the game
     */
    private void init(){

        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);


        mainMenuState = new MainMenuState(handler);
        gameState = new GameState(handler);
        titleScreenState = new TitleScreenState(handler);
        creditsState = new CreditsState(handler);
        settingsState = new SettingsState(handler);
        endGameState = new EndGameState(handler);

        State.setState(endGameState);

    }

    private void tick() {

        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();
    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();

        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        // Begin Rendering

        if(State.getState() != null)
            State.getState().render(g);

        // End Rendering
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public UIManager getUIManager() {
        return uiManager;
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
