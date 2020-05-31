package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.ui.SixthMinigameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class SixthMinigameState extends State {
    private UIManager uiManager;

    public SixthMinigameState(Handler handler) {
        super(handler);

        uiManager = new SixthMinigameUI(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
