package com.itcr.ce.datosparty.userInterface;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.ReverseAnimation;

import java.awt.*;

public class UIAnimatedImage extends UIObject{

    private Animation animation;
    private ReverseAnimation reverseAnimation;

    /**
     *
     * @param x position of the image
     * @param y position of the image
     * @param width of the image
     * @param height of the image
     * @param animation main input of the class, it renders it somewhere on screen
     */
    public UIAnimatedImage(float x, float y, int width, int height, Animation animation, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.animation = animation;
    }
    public UIAnimatedImage(float x, float y, int width, int height, ReverseAnimation reverseAnimation, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.reverseAnimation = reverseAnimation;
    }

    @Override
    public void tick() {
        if(animation != null){
            animation.tick();

        }
        else{
            reverseAnimation.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if(animation != null) {
            assert false;
            g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height, null);
        }else {
            g.drawImage(reverseAnimation.getCurrentFrame(), (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
    }
}
