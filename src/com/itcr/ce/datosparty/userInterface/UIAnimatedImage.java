package com.itcr.ce.datosparty.userInterface;
import com.itcr.ce.datosparty.gfx.Animation;

import java.awt.*;

public class UIAnimatedImage extends UIObject{

    private Animation animation;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param animation
     */
    public UIAnimatedImage(float x, float y, int width, int height, Animation animation) {
        super(x*16, y*16, width*16, height*16);
        this.animation = animation;
    }

    @Override
    public void tick() {
        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
    }
}
