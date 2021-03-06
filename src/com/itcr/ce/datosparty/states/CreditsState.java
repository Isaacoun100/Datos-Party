package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

/**
 * this class is meant to show the credits of the game,
 */
public class CreditsState extends State{
    private final UIManager uiManager;

    public CreditsState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImage(20,10,5*2,5*2, Assets.notOKComputerIcon[0],"notOkCompIco"));
        uiManager.addObject(new UIImage(16,19,2*9, 2, Assets.notOKComputer[0],"notOKCompMsg"));
        uiManager.addObject(new UIImage(7,26,9*4, 4, Assets.nothingHereMessage[0],"nothingMsg"));

        uiManager.addObject(new UIImageButton(20, 30, 3*3, 3, Assets.backButton,"bckBtn",
                () -> setState(GameLoop.mainMenuState)));

    }

    /**
     * tick method, it changes the UI and then it ticks its objects so they can be interacted with
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    /**
     * render method for the class, currently renders all objects
     * @param g graphics parameter passed to gameloop
     */
    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }
}
