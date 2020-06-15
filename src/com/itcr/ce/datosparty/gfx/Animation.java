package com.itcr.ce.datosparty.gfx;

import java.awt.image.BufferedImage;

/**
 * this class creates animated images from buffered image arrays
 */
public class Animation {
    private final int speed;
    private int index;
    private long lastTime, timer;
    BufferedImage[] frames;

    /**
     * Constructor for animation, an animation is an object that goes through a series of images,
     * called frames from lowest index to its higher index
     * @param speed int value of millisecond intervals between frames
     * @param frames a buffered array of images of the same size
     */
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * When called in another tick method it will run the animation to the speed specified in its constructor
     */
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    /**
     * Getter for the current frame
     * @return the current frame that is being rendered by the animation
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
