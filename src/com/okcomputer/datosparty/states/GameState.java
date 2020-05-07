package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.GameLoop;
import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.ClickListener;
import com.okcomputer.datosparty.userInterface.UIImage;
import com.okcomputer.datosparty.userInterface.UIImageButton;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class GameState extends State{



    public GameState(Handler handler){

        super(handler);
        /*
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImage(82,100,2*20,2*3, Assets.notOKComputerIcon));

        uiManager.addObject(new UIImageButton(328, 400, 3*3, 3, Assets.backButton, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGameLoop().mainMenuState);
            }
        }));
         */
    }

    @Override
    public void tick() {
       // uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
      //  uiManager.render(g);
    }
}
