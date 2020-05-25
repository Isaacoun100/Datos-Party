package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.userInterface.EndGameUI;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.userInterface.*;

import java.awt.*;

public class EndgameState extends State {

    private final UIManager uiManager;


    public EndgameState(Handler handler) {
        super(handler);

        uiManager = new EndGameUI(handler);

        uiManager.addObject(new UIImageButton(18, 32, 7*2, 2*2, Assets.creditsButton,
                () -> State.setState(handler.getGameLoop().creditsState)));


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