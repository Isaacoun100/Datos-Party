package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.random.Dice;
import com.okcomputer.datosparty.userInterface.ClickListener;
import com.okcomputer.datosparty.userInterface.UIImageButton;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class BoardState extends State {

    private UIManager uiManager;

    /**
     * Main Constructor for the State
     *
     * @param handler handler element is the only parameter
     */
    public BoardState(Handler handler) {
        super(handler);

        uiManager = handler.getUIManager();
/*
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(20, 25, 4, 4, Assets.diceButton, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println(Dice.roll());
            }
        }));
*/
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
