package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class WinnerState extends State {

    private UIManager uiManager;

    /**
     * Main Constructor for the State
     *
     * @param handler handler element is the only parameter
     */
    public WinnerState(Handler handler) {

        super(handler);

        uiManager = handler.getUIManager();

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
