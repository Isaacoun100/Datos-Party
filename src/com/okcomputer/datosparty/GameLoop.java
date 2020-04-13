package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;

public class GameLoop implements Runnable{

    private Display display;
    public String title;
    public int width, height;
    private Thread thread;
    private boolean running = false;

    public GameLoop(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;

    }

    private void init(){
        display = new Display(title,width,height);

    }

    private void render() {
    }

    private void tick() {
    }

    @Override
    public void run() {
        init();

        while(running){
            tick();
            render();
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
