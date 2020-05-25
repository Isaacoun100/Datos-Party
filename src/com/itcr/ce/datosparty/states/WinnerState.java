package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.userInterface.WinnerUI;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class WinnerState extends State {

    private final UIManager uiManager;


    public WinnerState(Handler handler) {
        super(handler);

        uiManager = new WinnerUI(handler);

        uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player1Button, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("1");
            }
        }));

        uiManager.addObject(new UIImageButton(1, 30, 7*2, 2*2, Assets.player2Button, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("2");
            }
        }));

        uiManager.addObject(new UIImageButton(35, 1, 7*2, 2*2, Assets.player3Button, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("3");
            }
        }));

        uiManager.addObject(new UIImageButton(35, 30, 7*2, 2*2, Assets.player4Button, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("4");
            }
        }));

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