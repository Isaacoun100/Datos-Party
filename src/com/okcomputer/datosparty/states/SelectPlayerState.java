package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.logic.Round;
import com.okcomputer.datosparty.userInterface.*;

import java.awt.*;


public class SelectPlayerState extends State {

    private final UIManager uiManager;
    public int player;

    public SelectPlayerState(Handler handler) {
        super(handler);

        uiManager = new SelectPlayerUI(handler);
        Round.roundInit();

        uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player2Button, new ClickListener() {
            @Override
            public void onClick() {

                Round.playerLoop(2);
                Round.returnPlayers();
                State.setState(handler.getGameLoop().boardState);
            }
        }));

        uiManager.addObject(new UIImageButton(35, 1, 7*2, 2*2, Assets.player3Button, new ClickListener() {
            @Override
            public void onClick() {
                Round.playerLoop(3);
                Round.returnPlayers();
                State.setState(handler.getGameLoop().boardState);
            }
        }));

        uiManager.addObject(new UIImageButton(20, 30, 7*2, 2*2, Assets.player4Button, new ClickListener() {
            @Override
            public void onClick() {
                Round.playerLoop(4);
                Round.returnPlayers();
                State.setState(handler.getGameLoop().boardState);
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
