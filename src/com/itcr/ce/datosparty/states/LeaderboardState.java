package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class LeaderboardState extends State{

    private final UIManager uiManager;
    private final Font font;

    public LeaderboardState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        font = new Font("Arial", Font.PLAIN,50);

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);

        g.setFont(font);
        g.drawString("JU",10,40);
        g.drawString("JO",60,100);

    }

}
