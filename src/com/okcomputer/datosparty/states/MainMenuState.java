package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.*;

import java.awt.*;

public class MainMenuState extends State{

    private final UIManager uiManager;

    public MainMenuState(Handler handler){

        super(handler);

            uiManager = new MainMenuUI(handler);

            uiManager.addObject(new UIImage(6,6,2*20,2*3,Assets.titleImage));

            uiManager.addObject(new UIImageButton(20, 24, 3*3, 3, Assets.playButton,
                    () -> State.setState(handler.getGameLoop().gameState)));

            uiManager.addObject(new UIImageButton(17, 28, 3*5, 3, Assets.settingsButton,
                    () -> State.setState(handler.getGameLoop().optionsState)));

            uiManager.addObject(new UIImageButton(17, 32, 3*5, 3, Assets.creditsButton,
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
