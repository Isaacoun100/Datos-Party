package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class MainMenuState extends State{

    private final UIManager uiManager;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    private Animation titleAnimation;
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

    @Override
    public void tick() {
        //titleAnimation.tick();
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        }

    @Override
    public void render(Graphics g) {
            uiManager.renderAll(g);
            //g.drawImage(titleAnimation.getCurrentFrame(), (width/2)-19, height/6,40,6,null);
        }

}
