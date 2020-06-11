package com.itcr.ce.datosparty.gfx;

import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.lists.DoublyList;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;

import java.awt.image.BufferedImage;

public class ReverseAnimation {
    private final int speed;
    private int index;
    private long lastTime, timer;
    BufferedImage[] frames;

    public ReverseAnimation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = frames.length-1;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){

        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index--;
            timer = 0;
            if(index == 0){
                index = frames.length-1;
            }
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}

