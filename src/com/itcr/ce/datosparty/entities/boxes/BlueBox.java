package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;

/**
 * Type of box with no special event given
 */
public class BlueBox extends Box {

    /**
     * Constructor for the box
     * @param x an int of the position where it would be rendered
     * @param y an int of the position where it would be rendered
     * @param width an int of the width of the object
     * @param height and int of the height of the object
     */
    public BlueBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * render method for boxes, where we provide the asset associated with it.
     * @param g java.awt graphics object.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blueBox,(int) x,(int) y, width, height, null);
    }

    /**
     * Only box that has no special event attached to it
     * @param player Current Player standing on box when its turn ends
     * @param game Object with all the data contained on current game
     */
    @Override
    public void boxAction(Player player, Game game) {
    }
}

