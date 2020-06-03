package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.ui.ThirdMinigameUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class ThirdMinigameState extends State {
    private UIManager uiManager;
    private int numPlayers;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new ThirdMinigameUI(handler);

        if(numPlayers>=1){
            uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player1Button, new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("1");
                }
            }));
        }

        if(numPlayers>=2){
            uiManager.addObject(new UIImageButton(30, 1, 7*2, 2*2, Assets.player2Button, new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("2");
                }
            }));
        }

        if(numPlayers>=3){
            uiManager.addObject(new UIImageButton(1, 30, 7*2, 2*2, Assets.player3Button, new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("3");
                }
            }));
        }

        if(numPlayers>=4){
            uiManager.addObject(new UIImageButton(30, 30, 7*2, 2*2, Assets.player4Button, new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("4");
                }
            }));
        }

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