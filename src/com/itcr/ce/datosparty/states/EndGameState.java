package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.DefineOrder;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class EndGameState extends State {

    private final UIManager uiManager;
    private final Font font;

    public EndGameState(Handler handler) {
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