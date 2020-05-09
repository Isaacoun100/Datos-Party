package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.UIManager;
import com.okcomputer.datosparty.userInterface.ClickListener;
import com.okcomputer.datosparty.userInterface.UIImage;
import com.okcomputer.datosparty.userInterface.UIImageButton;

import java.awt.*;

public class EndGameState extends State {


    private UIManager uiManager;

    /**
     * Main Constructor for the State
     *
     * @param handler handler element is the only parameter
     */
    public EndGameState(Handler handler) {
        super(handler);

        uiManager = handler.getUIManager();
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(328, 400, 3*3, 3, Assets.playButton, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("hola mundo");
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


        g.drawImage(Assets.notOKComputerIcon[0] , 82,100,null);
        g.drawImage(Assets.notOKComputer[0] , 40,130,null);

    }
}
