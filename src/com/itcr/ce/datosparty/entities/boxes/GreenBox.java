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

    public GreenBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.greenBox,(int) x,(int) y, width, height, null);

    }

    /**
     * Gives 3 coins to the player
     * @param player that stands on the box ont the end of its Turn
     * @param game played currently
     */
    @Override
    public void boxAction(Player player, Game game){
        SoundEffect.PopSound();
        player.addCoins(+3);
        player.setBoxAction(false);

    }
}
