package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

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

    @Override
    public void boxAction(Player player, Game game){
        if(player.getCurrentTurn()) {
            SoundEffect.PopSound();
            player.addCoins(+3);
        }
    }
}
