package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.DefineOrder;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class EndGameState extends State {

    private final UIManager uiManager;


    public EndGameState(Handler handler, Game game) {
        super(handler);

        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImageButton(18, 32, 7*2, 2*2, Assets.creditsButton,"creditsBtn",
                () -> {
                game.setActive(false);
                GameLoop.gameDependantStates.clear();
                Round.getPlayerOrder().clear();
                DefineOrder.getTemporalPlayerList().clear();
                setState(GameLoop.creditsState); }));


    }
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }
}