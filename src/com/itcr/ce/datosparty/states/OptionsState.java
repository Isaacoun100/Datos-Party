package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.music.MusicPlayer;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

/**
 * This class is a state that contains the options that modifies the audio and clip sounds
 */
public class OptionsState extends State{
    private final UIManager uiManager;

    /**
     * Constructor for the Options state that ensure the use of the handler for the IUManager
     * @param handler
     */
    public OptionsState(Handler handler){
        super(handler);

        uiManager = new OptionsUI(handler);

        uiManager.addObject(new UIImageButton(44, 3, 3, 3, Assets.volumeUp,"volumeUpMusic", new ClickListener() {
            @Override
            public void onClick() {
                SoundEffect.PopSound();
                MusicPlayer.musicVolumeUp();
            }}));

        uiManager.addObject(new UIImageButton(3, 3, 3, 3, Assets.volumeDown,"volumeDownMusic", new ClickListener() {
            @Override
            public void onClick() {
                SoundEffect.PopSound();
                MusicPlayer.musicVolumeDown();
            }}));

        uiManager.addObject(new SingleUIImage(18, 3, 7*2, 2*2, Assets.gameMusic,"gameMusic"));

        uiManager.addObject(new UIImageButton(44, 11, 3, 3, Assets.volumeUp,"volumeUpEffect", new ClickListener() {
            @Override
            public void onClick() {
                SoundEffect.PopSound();
                MusicPlayer.clipVolumeUp();
            }}));

        uiManager.addObject(new UIImageButton(3, 11, 3, 3, Assets.volumeDown,"volumeDownEffect", new ClickListener() {
            @Override
            public void onClick() {
                SoundEffect.PopSound();
                MusicPlayer.clipVolumeDown();
            }}));

        uiManager.addObject(new SingleUIImage(18, 11, 7*2, 2*2, Assets.soundEffects, "soundFMessage"));

        uiManager.addObject(new UIImageButton(20, 30, 3*3, 3, Assets.backButton, "bckBtn",
                () -> setState(handler.getGameLoop().mainMenuState)));

    }

    /**
     * The tick initializes once the state is called, the main difference with the constructor is that the constructor
     * runs when the program itself starts but the tick each time the state is called
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    /**
     * Render method runs constantly in a loop once the game is started and finishes the moment the game also finishes,
     * this method also is the method in charge of rendering the graphics
     * @param g graphics parameter passed to GameLoop
     */
    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }
}
