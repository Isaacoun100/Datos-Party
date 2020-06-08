package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

public class RedBox extends Box {


    public RedBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.redBox,(int) x,(int) y, width, height, null);

    }

    @Override
    public void boxAction(Player player) {
        SoundEffect.PopSound();
        if(player.getCoins() < 3){
            player.addCoins(0);
        }
        else{
            player.addCoins(-3);
        }
    }
}
