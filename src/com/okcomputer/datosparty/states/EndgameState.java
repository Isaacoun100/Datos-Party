package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.*;

import java.awt.*;

public class EndgameState extends State {

    private final UIManager uiManager;


    public EndgameState(Handler handler) {
        super(handler);

        uiManager = new MainMenuUI(handler);

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