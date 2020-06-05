package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.userInterface.GameUI;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class GameState extends State{

    private final UIManager uiManager;


    public GameState(Handler handler){


        super(handler);

        uiManager = new GameUI(handler);

//        uiManager.addObject(new UIImageButton(20, 25, 3*3, 3, Assets.backButton,
//                () -> State.setState(handler.getGameLoop().mainMenuState)));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {

        uiManager.render(g);
        DoublyNode<Box> currentBox = handler.getBoard().getPhaseA().getHead();
        for (int i = 0; i < handler.getBoard().getPhaseA().getLength(); i++){
            currentBox.getData().render(g);
            currentBox = (DoublyNode<Box>) currentBox.getNext();
        }
        SinglyNode<Player> currentPlayer = Round.getPlayerOrder().getHead();
        for (int i = 0; i < Round.getPlayerOrder().getLength(); i++){
            currentPlayer.getData().render(g);
            currentPlayer = (SinglyNode<Player>) currentPlayer.getNext();
        }
    }
}
