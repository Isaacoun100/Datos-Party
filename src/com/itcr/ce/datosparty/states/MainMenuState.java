package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

/**
 * state for the main menu, from here you can start a new game, change settings or check the credits
 */
public class MainMenuState extends State{

    private final UIManager uiManager;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    private Animation titleAnimation;

    /**
     * Constructor for the main state, it only takes in the handler
     * @param handler handler in order to switch the state to this, and for accessing variables
     */
    public MainMenuState(Handler handler){

        super(handler);

        uiManager = new UIManager(handler);

        uiManager.addObject(new UIBackground(Assets.loginWallpaper, "wp"));

        titleAnimation = new Animation(500, Assets.titleImage);

        uiManager.addObject(new UIAnimatedImage((width/2)-39,height/6,40*2,6*2,titleAnimation,"titleAnimation"));

        uiManager.addObject(new UIImageButton((width/2)-4, (height/2), 9, 3, Assets.playButton,"playBtn",
                () -> State.setState(GameLoop.selectRoundState)));

        uiManager.addObject(new UIImageButton((width/2)-7, (height/2)+3, 15, 3, Assets.settingsButton,"settingsBtn",
                () -> State.setState(GameLoop.optionsState)));

        uiManager.addObject(new UIImageButton((width/2)-7, (height/2)+6, 15, 3, Assets.creditsButtonMenu,"creditsBtn",
                () -> State.setState(GameLoop.creditsState)));

    }

    /**
     * tick method for the main menu, it simply switches the UI to the one for the main menu
     */
    @Override
    public void tick() {
        //titleAnimation.tick();
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        }

    /**
     * render method for the main menu, it simply renders all items
     * @param g graphics parameter passed to game loop
     */
    @Override
    public void render(Graphics g) {
            uiManager.renderAll(g);
            //g.drawImage(titleAnimation.getCurrentFrame(), (width/2)-19, height/6,40,6,null);
        }

}
