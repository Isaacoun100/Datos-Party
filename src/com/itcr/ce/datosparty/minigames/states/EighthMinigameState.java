package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class EighthMinigameState extends State {

    private UIManager MemoryUI;
    private String done = "";
    private boolean active=true;

    public EighthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        MemoryUI = new UIManager(handler);

        MemoryUI.addObject(new UIImageButton(6, 10, 4*4, 6*4, Assets.backCard,"ghostCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='g' && active){
                        done+="g";
                    }
                }
                else{
                    done+="g";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(6, 39, 4*4, 6*4, Assets.backCard,"ghostCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='G' && active){
                        done+="G";
                    }
                }
                else{
                    done+="G";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(24, 10, 4*4, 6*4, Assets.backCard,"titanCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='t' && active){
                        done+="t";
                    }
                }
                else{
                    done+="t";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(24, 39, 4*4, 6*4, Assets.backCard,"titanCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='T' && active){
                        done+="T";
                    }
                }
                else{
                    done+="T";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(42, 10, 4*4, 6*4, Assets.backCard,"koichiCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='k' && active){
                        done+="k";
                    }
                }
                else{
                    done+="k";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(42, 39, 4*4, 6*4, Assets.backCard,"koichiCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='K' && active){
                        done+="K";
                    }
                }
                else{
                    done+="K";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(60, 10, 4*4, 6*4, Assets.backCard,"samCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='s' && active){
                        done+="s";
                    }
                }
                else{
                    done+="s";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(60, 39, 4*4, 6*4, Assets.backCard,"samCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='S' && active){
                        done+="S";
                    }
                }
                else{
                    done+="S";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(78, 10, 4*4, 6*4, Assets.backCard,"tuxCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='x' && active){
                        done+="x";
                    }
                }
                else{
                    done+="x";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(78, 39, 4*4, 6*4, Assets.backCard,"tuxCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='X' && active){
                        done+="X";
                    }
                }
                else{
                    done+="X";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(1, 1, 3*3, 3*3, Assets.checkButton,"checkButton", new ClickListener() {
            @Override
            public void onClick() {
                if(!active){
                    done=done.toUpperCase();

                    if(done.charAt(0)==done.charAt(1)){
                        System.out.println("Nice job");
                    }

                    else{
                        System.out.println("Ups try again");
                    }

                    System.out.println(done);
                    active=true;
                    done="";
                }
            }}));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(MemoryUI);
        MemoryUI.tick();
    }

    @Override
    public void render(Graphics g) {

        if(done.length()==2){
            MemoryUI.renderById(g,"checkButton");
            active=false;
        }
        else{
            MemoryUI.renderById(g,"ghostCard1");
            MemoryUI.renderById(g,"ghostCard2");
            MemoryUI.renderById(g,"titanCard1");
            MemoryUI.renderById(g,"titanCard2");
            MemoryUI.renderById(g,"koichiCard1");
            MemoryUI.renderById(g,"koichiCard2");
            MemoryUI.renderById(g,"samCard1");
            MemoryUI.renderById(g,"samCard2");
            MemoryUI.renderById(g,"tuxCard1");
            MemoryUI.renderById(g,"tuxCard2");
        }

    }

}
