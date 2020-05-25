package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.userInterface.BoardUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

public class BoardState extends State {

    private UIManager uiManager;

    public BoardState(Handler handler) {
        super(handler);

        uiManager = new BoardUI(handler);

        uiManager.addObject(new UIImageButton(1, 1, 4, 4, Assets.diceButton, new ClickListener() {
            @Override
            public void onClick() {

                SoundEffect.DiceRoll();
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
