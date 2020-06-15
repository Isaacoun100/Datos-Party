package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.music.MusicPlayer;
import com.itcr.ce.datosparty.music.SoundEffect;

import java.awt.*;

public class OptionsState extends State{
    private final UIManager uiManager;

    public OptionsState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);

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

        uiManager.addObject(new UIImage(18, 3, 7*2, 2*2, Assets.gameMusic[0],"gameMusic"));

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

        uiManager.addObject(new UIImage(18, 11, 7*2, 2*2, Assets.soundEffects[0], "soundFMessage"));

        uiManager.addObject(new UIImageButton(20, 30, 3*3, 3, Assets.backButton, "bckBtn",
                () -> setState(handler.getGameLoop().mainMenuState)));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }
}
