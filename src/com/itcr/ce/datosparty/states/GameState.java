package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.dataStructures.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.logic.Board;
import com.itcr.ce.datosparty.logic.boxes.Box;
import com.itcr.ce.datosparty.userInterface.GameUI;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;

import java.awt.*;

public class GameState extends State{

    private final UIManager uiManager;

    public GameState(Handler handler){


        super(handler);

        uiManager = new GameUI(handler);

        uiManager.addObject(new UIImageButton(20, 25, 3*3, 3, Assets.backButton,
                () -> State.setState(handler.getGameLoop().mainMenuState)));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);

        DoublyNode<Box> current;
        current = Board.phaseA.getHead();
        while (current != null) {
            current.getData().render(g);
            current = current.getNext();
        }
        //handler.getGameLoop().testPlayer.render(g);
    }

}
