package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.gfx.Assets;

import java.awt.*;

public class MainMenuState extends State{

    public MainMenuState(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.titleImage,82,100,null);
        g.drawImage(Assets.playImage,360,400,null);
        g.drawImage(Assets.settingsImage, 336, 432, null);
        g.drawImage(Assets.creditsImage, 344, 464, null);

    }
}
