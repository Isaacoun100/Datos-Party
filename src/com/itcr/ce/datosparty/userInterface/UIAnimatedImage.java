package com.itcr.ce.datosparty.userInterface;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.ReverseAnimation;

import java.awt.*;

/**
 * this class renders an animated image on screen, its sized can be modified with int variables
 */
public class UIAnimatedImage extends UIObject{

    private Animation animation;
    private ReverseAnimation reverseAnimation;

    /**
     * constructor for the class
     * @param x position of the image
     * @param y position of the image
     * @param width of the image
     * @param height of the image
     * @param animation main input of the class, it renders it somewhere on screen
     * @param id String id of the UI object
     */
    public UIAnimatedImage(float x, float y, int width, int height, Animation animation, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.animation = animation;
    }

    /**
     * overload constructor for the class so that it can also take reversed animations
     * @param x position of the image
     * @param y position of the image
     * @param width of the image
     * @param height of the image
     * @param reverseAnimation main input of the class, it renders it somewhere on screen
     * @param id String id of the UI object
     */
    public UIAnimatedImage(float x, float y, int width, int height, ReverseAnimation reverseAnimation, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.reverseAnimation = reverseAnimation;
    }

    /**
     * tick method used to swap between the different animation frames
     */
    @Override
    public void tick() {
        if(animation != null){
            animation.tick();
        }
        else{
            reverseAnimation.tick();
        }
    }

    /**
     * render method that shows the animated image on screen
     * @param g java.awt graphics object
     */
    @Override
    public void render(Graphics g) {
        if(animation != null) {
            assert false;
            g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height, null);
        }else {
            g.drawImage(reverseAnimation.getCurrentFrame(), (int) x, (int) y, width, height, null);
        }
    }

    /**
     * unused method from parent class
     */
    @Override
    public void onClick() {
    }
}
