package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.minigames.ui.FirstMinigameUI;
import com.itcr.ce.datosparty.minigames.ui.SecondMinigameUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class SecondMinigameState extends State {
    private UIManager uiManager;

    public SecondMinigameState(Handler handler) {
        super(handler);

        uiManager = new SecondMinigameUI(handler);
        int numPlayers=4; //Round.getPlayerOrder().getLength();

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

