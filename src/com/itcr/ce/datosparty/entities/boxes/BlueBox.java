package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;

/**
 * Type of box with no special event given
 */
public class BlueBox extends Box {

    public BlueBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }



    @Override
    public void tick() {
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blueBox,(int) x,(int) y, width, height, null);
    }

    /**
     * Only box that has no special event attached to it
     * @param player that stands on the box at the end of its Turn
     * @param game played currently
     */
    @Override
    public void boxAction(Player player, Game game) {
        player.setBoxAction(false);
    }
}

