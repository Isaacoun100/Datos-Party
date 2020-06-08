package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.ui.SixthMinigameUI;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class SixthMinigameState extends State {
    private UIManager uiManager;
    private int numPlayers;

    public SixthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new SixthMinigameUI(handler);


        if(numPlayers>=1){
            uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player1Button,"player1Btn", new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("1");
                }
            }));
        }

        if(numPlayers>=2){
            uiManager.addObject(new UIImageButton(30, 1, 7*2, 2*2, Assets.player2Button,"player2Btn", new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("2");
                }
            }));
        }

        if(numPlayers>=3){
            uiManager.addObject(new UIImageButton(1, 30, 7*2, 2*2, Assets.player3Button,"player3Btn", new ClickListener() {
                @Override
                public void onClick() {
                    System.out.println("3");
                }
            }));
        }

        if(numPlayers>=4){
            uiManager.addObject(new UIImageButton(30, 30, 7*2, 2*2, Assets.player4Button,"player4Btn", new ClickListener() {
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
        uiManager.renderAll(g);
    }

}
