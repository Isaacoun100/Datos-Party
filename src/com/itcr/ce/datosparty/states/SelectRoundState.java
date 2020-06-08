package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.userInterface.*;

import java.awt.*;

public class SelectRoundState extends State {

    private final UIManager selectRoundUI
            ;
    public static int round;

    public static int getRound() {
        return round;
    }

    public SelectRoundState(Handler handler) {
        super(handler);

        selectRoundUI = new UIManager(handler);

        selectRoundUI.addObject(new SingleUIImage(29,5,12*4,6*4,Assets.roundTitle));

        selectRoundUI.addObject(new UIImageButton(4, 40, 7*4, 2*4, Assets.noviceButton, () -> {

            round=5;
            State.setState(GameLoop.selectPlayerState);

        }));

        selectRoundUI.addObject(new UIImageButton(37, 40, 7*4, 2*4, Assets.proButton, () -> {

            round=10;
            State.setState(GameLoop.selectPlayerState);

        }));

        selectRoundUI.addObject(new UIImageButton(69, 40, 7*4, 2*4, Assets.eliteButton, () -> {

            round=20;
            State.setState(GameLoop.selectPlayerState);

        }));


    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(selectRoundUI);
        selectRoundUI.tick();
    }

    @Override
    public void render(Graphics g) {
        selectRoundUI.render(g);
    }
}
