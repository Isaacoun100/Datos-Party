package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;

public class BlueBox extends Box {

    private SinglyList<Player> playerList = new SinglyList<>();

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

    @Override
    public void boxAction(Player player, Game game) {
        System.out.println("BlueBox");
    }
}

