package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

/**
 * Type of box that gives 3 coins to a player standing on it
 */
public class GreenBox extends Box {

    /**
     * This box adds coins to the player, you can see the abstract class for a more detailed explanation of each parameter
     * @param x position
     * @param y position
     * @param width size
     * @param height size
     */
    public GreenBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * render method for boxes, where we provide the asset associated with it.
     * @param g java.awt graphics object.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.greenBox,(int) x,(int) y, width, height, null);
    }

    /**
     * Gives 3 coins to the player
     * @param player Current Player standing on box when its turn ends
     * @param game Object with all the data contained on current game
     */
    @Override
    public void boxAction(Player player, Game game){
        if(player.getCurrentTurn()) {
            SoundEffect.PopSound();
            player.addCoins(+3);
        }
    }
}
