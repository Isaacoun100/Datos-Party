package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.states.GameState;
import com.okcomputer.datosparty.states.MainMenuState;
import com.okcomputer.datosparty.states.State;
import com.okcomputer.datosparty.states.TitleScreenState;

import java.awt.*;
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
    private State gameState, mainMenuState, titleScreenState;

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

    }

    /**
     * Initialization method, this runs variables that are used in the game
     */
    private void init(){
        display = new Display(title,width,height);
        Assets.init();
        mainMenuState = new MainMenuState();
        gameState = new GameState();
        titleScreenState = new TitleScreenState();

        State.setState(mainMenuState);

    }

    private void tick() {
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
