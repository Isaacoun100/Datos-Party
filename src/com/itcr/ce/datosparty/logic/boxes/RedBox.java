package com.itcr.ce.datosparty.logic.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Player;

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
        if(player.getCoins() < 3){
            player.setCoins(0);
        }
        else{
            player.setCoins(-3);
        }
        System.out.println("RedBox");
        System.out.println("Current Coins: "+player.getCoins());
    }
}
