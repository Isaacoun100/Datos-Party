package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.ui.FirstMinigameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class FirstMinigameState extends State {
    private UIManager uiManager;

    public FirstMinigameState(Handler handler) {
        super(handler);

        uiManager = new FirstMinigameUI(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
