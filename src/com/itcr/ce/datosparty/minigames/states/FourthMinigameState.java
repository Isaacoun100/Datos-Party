package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.ui.FourthMinigameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class FourthMinigameState extends State {
    private UIManager uiManager;

    public FourthMinigameState(Handler handler) {
        super(handler);

        uiManager = new FourthMinigameUI(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
