package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.GameLoop;
import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.ClickListener;
import com.okcomputer.datosparty.userInterface.UIImage;
import com.okcomputer.datosparty.userInterface.UIImageButton;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class MainMenuState extends State{

    private UIManager uiManager;

    public MainMenuState(Handler handler){

        super(handler);
            /*
            uiManager = handler.getUIManager();
            handler.getMouseManager().setUiManager(uiManager);

            uiManager.addObject(new UIImage(6,6,2*20,2*3,Assets.titleImage));

            uiManager.addObject(new UIImageButton(20, 25, 3*3, 3, Assets.playButton, new ClickListener() {
                @Override
                public void onClick() {
                    State.setState(handler.getGameLoop().gameState);
                }
            }));

            uiManager.addObject(new UIImageButton(17, 27, 3*5, 3, Assets.settingsButton, new ClickListener() {
                @Override
                public void onClick() {
                    State.setState(handler.getGameLoop().settingsState);
                }
            }));

            uiManager.addObject(new UIImageButton(17, 29, 3*5, 3, Assets.creditsButton, new ClickListener() {
                @Override
                public void onClick() {
                    State.setState(handler.getGameLoop().creditsState);
                }
            }));
*/
    }

    @Override
    public void tick() {
            //uiManager.tick();
        }

    @Override
    public void render(Graphics g) {
            //uiManager.render(g);
        }

}
