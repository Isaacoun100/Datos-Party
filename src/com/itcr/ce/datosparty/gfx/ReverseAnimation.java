package com.itcr.ce.datosparty.gfx;

import java.awt.image.BufferedImage;

/**
 * Grants the ability to play an animation's frames on reverse
 */
public class ReverseAnimation {
    private final int speed;
    private int index;
    private long lastTime, timer;
    BufferedImage[] frames;

    /**
     * Constructor for animation, an animation is an object that goes through a series of images,
     * called frames from highest index to its lowest index
     * @param speed int value of millisecond intervals between frames
     * @param frames a buffered array of images of the same size
     */
    public ReverseAnimation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = frames.length-1;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * When called in another tick method it will run the reverse animation to the speed specified in its constructor
     */
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

    /**
     * Getter for the current frame
     * @return the current frame that is being rendered by the reverse animation
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}

