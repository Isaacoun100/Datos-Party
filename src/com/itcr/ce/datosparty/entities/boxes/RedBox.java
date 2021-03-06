package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

/**
 * Type of box that subtracts 3 coins to a player standing on it
 */
public class RedBox extends Box {

    /**
     * This box subtract coins to the player, you can see the abstract class for a more detailed explanation of each parameter
     * @param x position
     * @param y position
     * @param width size
     * @param height size
     */
    public RedBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * render method for boxes, where we provide the asset associated with it.
     * @param g java.awt graphics object.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.redBox,(int) x,(int) y, width, height, null);

    }

    /**
     * Takes 3 coins from player
     * @param player Current Player standing on box when its turn ends
     * @param game Object with all the data contained on current game
     */
    @Override
    public void boxAction(Player player, Game game) {
        if(player.getCurrentTurn()) {
            SoundEffect.PopSound();
            player.addCoins(-3);
        }
    }
}
