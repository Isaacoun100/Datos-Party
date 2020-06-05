package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.logic.Turn;
import com.itcr.ce.datosparty.music.SoundEffect;
import com.itcr.ce.datosparty.userInterface.GameUI;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class GameState extends State{

    private final UIManager uiManager;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    private Player currentPlayer;
    private Game game;

    public GameState(Handler handler, Game game){


        super(handler);

        uiManager = new GameUI(handler);

        uiManager.addObject(new UIImageButton(3, height-20, 8, 8, Assets.diceButton,
                () -> {currentPlayer.setMovement();
                    SoundEffect.DiceRoll();
                    Thread.sleep(3000);
                    game.resumeGame();
                }));


    }

    @Override
    public void tick() {
        currentPlayer = Turn.getPlayersTurn().getData();
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.mapGuide, -10, 0, null);

        uiManager.render(g);

        SinglyNode<Box> currentBoxMain = handler.getBoard().getMainCircuit().getHead();
        for (int i = 0; i < handler.getBoard().getMainCircuit().getLength(); i++) {
            currentBoxMain.getData().render(g);
            currentBoxMain = (SinglyNode<Box>) currentBoxMain.getNext();
        }
        SinglyNode<Box> currentBoxA = handler.getBoard().getPhaseA().getHead();
        for (int i = 0; i < handler.getBoard().getPhaseA().getLength(); i++) {
            currentBoxA.getData().render(g);
            currentBoxA = (SinglyNode<Box>) currentBoxA.getNext();
        }
        SinglyNode<Box> currentBoxB = handler.getBoard().getPhaseB().getHead();
        for (int i = 0; i < handler.getBoard().getPhaseB().getLength(); i++) {
            currentBoxB.getData().render(g);
            currentBoxB = (SinglyNode<Box>) currentBoxB.getNext();
        }
        DoublyNode<Box> currentBoxC = handler.getBoard().getPhaseC().getHead();
        for (int i = 0; i < handler.getBoard().getPhaseC().getLength(); i++) {
            currentBoxC.getData().render(g);
            currentBoxC = (DoublyNode<Box>) currentBoxC.getNext();
        }
        DoublyNode<Box> currentBoxD = handler.getBoard().getPhaseD().getHead();
        for (int i = 0; i < handler.getBoard().getPhaseD().getLength(); i++) {
            currentBoxD.getData().render(g);
            currentBoxD = (DoublyNode<Box>) currentBoxD.getNext();
        }
        SinglyNode<Player> currentPlayer = Round.getPlayerOrder().getHead();
        for (int i = 0; i < Round.getPlayerOrder().getLength(); i++) {
            currentPlayer.getData().render(g);
            currentPlayer = (SinglyNode<Player>) currentPlayer.getNext();
        }
    }
}
