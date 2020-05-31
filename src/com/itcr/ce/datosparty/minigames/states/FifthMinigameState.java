package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.ui.FifthMinigameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class FifthMinigameState extends State {
    private UIManager uiManager;

    public FifthMinigameState(Handler handler) {
        super(handler);

        uiManager = new FifthMinigameUI(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
