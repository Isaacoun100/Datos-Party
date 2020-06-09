package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class LeaderboardState extends State{
    private final UIManager uiManager;

    public LeaderboardState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);



    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }

}
