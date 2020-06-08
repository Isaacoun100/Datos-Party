package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.ui.EighthMinigameUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class EighthMinigameState extends State {
    private UIManager uiManager;

    public EighthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new EighthMinigameUI(handler);
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}
