package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.gfx.Assets;

import java.awt.*;

public class TitleScreenState extends State{

    public TitleScreenState(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.titleImage,82,100,null);
        g.drawImage(Assets.pressEnterImage, 256, 400, null);

    }
}
