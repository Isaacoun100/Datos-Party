package com.itcr.ce.datosparty.gfx;

import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;

import java.awt.image.BufferedImage;

public class ReverseAnimation {
    private final int speed;
    private long lastTime, timer;
    CircularList<BufferedImage> frames;
    Node<BufferedImage> currentFrame;

    public ReverseAnimation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = adapter(frames);
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            timer = 0;
            currentFrame = frames.getHead().getPrevious();
        }
    }

    public BufferedImage getCurrentFrame(){
        return currentFrame.getData();
    }

    public CircularList<BufferedImage> adapter(BufferedImage[] imageArray){
        CircularList<BufferedImage> imageList = new CircularList<>();
        for (BufferedImage image : imageArray) {
            imageList.add(image);
        }
        return imageList;
    }

}

