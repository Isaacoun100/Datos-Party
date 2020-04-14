package com.okcomputer.datosparty;

import com.okcomputer.datosparty.display.Display;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameLoop implements Runnable{

    private Display display;
    public String title;
    public int width, height;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;


    public GameLoop(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;

    }

    private void init(){
        display = new Display(title,width,height);
        Assets.init();

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

        g.drawImage(Assets.titleImage,82,100,null);
        g.drawImage(Assets.pressEnterImage, 256, 400, null);

        // End Rendering
        bs.show();
        g.dispose();
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
