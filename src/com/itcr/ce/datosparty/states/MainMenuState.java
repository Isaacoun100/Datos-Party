package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class MainMenuState extends State{

    private final UIManager uiManager;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    public MainMenuState(Handler handler){

        super(handler);

        uiManager = new UIManager(handler);
            uiManager.addObject(new UIImage((width/2)-19,height/6,40,6,Assets.titleImage));

            uiManager.addObject(new UIImageButton((width/2)-4, (height/2), 9, 3, Assets.playButton,
                    () -> State.setState(GameLoop.selectPlayerState)));

            uiManager.addObject(new UIImageButton((width/2)-7, (height/2)+3, 15, 3, Assets.settingsButton,
                    () -> State.setState(GameLoop.optionsState)));

            uiManager.addObject(new UIImageButton((width/2)-7, (height/2)+6, 15, 3, Assets.creditsButtonMenu,
                    () -> State.setState(GameLoop.creditsState)));

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
