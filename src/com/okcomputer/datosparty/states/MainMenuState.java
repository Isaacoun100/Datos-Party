package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.GameLoop;
import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.ClickListener;
import com.okcomputer.datosparty.userInterface.UIImageButton;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class MainMenuState extends State{

    private UIManager uiManager;

    public MainMenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(360, 400, 103, 23, Assets.playButton, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGameLoop().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(336, 432, 103, 23, Assets.settingsButton, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGameLoop().settingsState);
            }
        }));

        uiManager.addObject(new UIImageButton(344, 464, 103, 23, Assets.creditsButton, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGameLoop().creditsState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);

        g.drawImage(Assets.titleImage,82,100,null);

    }
}
