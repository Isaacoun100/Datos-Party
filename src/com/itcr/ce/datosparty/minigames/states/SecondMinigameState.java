package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.ui.SecondMinigameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class SecondMinigameState extends State {
    private UIManager uiManager;

    public SecondMinigameState(Handler handler) {
        super(handler);

        uiManager = new SecondMinigameUI(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
