package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.random.Dice;
import com.okcomputer.datosparty.userInterface.*;

import java.awt.*;

public class BoardState extends State {

    private UIManager uiManager;

    public BoardState(Handler handler) {
        super(handler);

        uiManager = new BoardUI(handler);

        uiManager.addObject(new UIImageButton(1, 1, 4, 4, Assets.diceButton, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println(Dice.roll());
                }
            }));

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
