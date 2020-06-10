package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.ui.ThirdMinigameUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class ThirdMinigameState extends State {
    private UIManager uiManager;
    private int numPlayers;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new ThirdMinigameUI(handler);

        uiManager.addObject(new UIImage(1, 1, 7*2, 2*2, Assets.player1Static,"player1Image"));

        uiManager.addObject(new UIImage(1, 2, 7*2, 2*2, Assets.player1Static,"player2Image"));

        uiManager.addObject(new UIImage(2, 1, 7*2, 2*2, Assets.player1Static,"player3Image"));

        uiManager.addObject(new UIImage(2, 2, 7*2, 2*2, Assets.player1Static,"player4Image"));

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