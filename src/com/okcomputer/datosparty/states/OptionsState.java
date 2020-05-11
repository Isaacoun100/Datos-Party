package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.OptionsUI;
import com.okcomputer.datosparty.userInterface.UIImage;
import com.okcomputer.datosparty.userInterface.UIImageButton;
import com.okcomputer.datosparty.userInterface.UIManager;

import java.awt.*;

public class OptionsState extends State{
    private final UIManager uiManager;

    public OptionsState(Handler handler){
        super(handler);
        uiManager = new OptionsUI(handler);

        uiManager.addObject(new UIImage(4,20,2*9, 2, Assets.notOKComputer));
        uiManager.addObject(new UIImage(12,10,5*2,5*2, Assets.notOKComputerIcon));
        uiManager.addObject(new UIImage(6,14,9*2, 2, Assets.nothingHereMessage));

        uiManager.addObject(new UIImageButton(20, 25, 3*3, 3, Assets.backButton,
                () -> State.setState(handler.getGameLoop().mainMenuState)));
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
