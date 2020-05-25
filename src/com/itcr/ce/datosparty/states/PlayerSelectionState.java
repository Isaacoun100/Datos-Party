package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.logic.DefineOrder;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.userInterface.SelectPlayerUI;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.userInterface.*;

import java.awt.*;


public class PlayerSelectionState extends State {

    private final UIManager uiManager;
    DefineOrder setOrder = new DefineOrder();

    public PlayerSelectionState(Handler handler) {
        super(handler);

        uiManager = new SelectPlayerUI(handler);
        DefineOrder.initTemporal();

        uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player2Button, new ClickListener() {
            @Override
            public void onClick() {

                setOrder.recursiveAdd(2);
                setOrder.searchDraw().print();
            }
        }));

        uiManager.addObject(new UIImageButton(35, 1, 7*2, 2*2, Assets.player3Button, new ClickListener() {
            @Override
            public void onClick() {

                setOrder.recursiveAdd(3);
                setOrder.searchDraw().print();

            }
        }));

        uiManager.addObject(new UIImageButton(20, 30, 7*2, 2*2, Assets.player4Button, new ClickListener() {
            @Override
            public void onClick() {
                setOrder.recursiveAdd(4);
                setOrder.searchDraw().print();
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
