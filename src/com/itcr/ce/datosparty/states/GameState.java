package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.*;
import com.itcr.ce.datosparty.music.SoundEffect;
import com.itcr.ce.datosparty.userInterface.GameUI;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class GameState extends State{

    private final UIManager gameUI;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    private Player currentPlayer;
    private Box currentBox;

    private final Game game;

    public GameState(Handler handler, Game game){
        super(handler);
        this.game = game;

        gameUI = new GameUI(handler);

        gameUI.addObject(new UIImageButton(3, height-20, 8, 8, Assets.diceButton,
                () -> {
                    if(!currentBox.isCrossRoads()) {
                        currentPlayer.setMovement(Dice.roll(1, 6) + Dice.roll(1, 6));
                        SoundEffect.DiceRoll();
                        //Thread.sleep(3000);
                        game.resumeGame();
                }
                }));

        gameUI.addObject(new UIImageButton(6,height-20,8,8,Assets.upArrow,
                ()->{
                    currentPlayer.setDirection(true);
                    int movementLeft = currentPlayer.getMovement();
                    currentPlayer.setMovement(movementLeft);
                    game.resumeGame();
                }));

        gameUI.addObject(new UIImageButton(12,height-20,8,8,Assets.rightArrow,
                ()->{
                    currentPlayer.setDirection(false);
                    int movementLeft = currentPlayer.getMovement();
                    currentPlayer.setMovement(movementLeft);
                    game.resumeGame();
                }));
    }

    @Override
    public void tick() {
        currentPlayer = Turn.getPlayersTurn().getData();
        currentBox = currentPlayer.getPosition().getData();
        handler.getMouseManager().setUiManager(gameUI);
        gameUI.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.mapGuide, -10, 0, null);

        SinglyNode<Box> currentBoxMain = handler.getBoard().getMainCircuit().getHead();
        int mainCircuitLength = handler.getBoard().getMainCircuit().getLength();
        renderBoard(currentBoxMain,mainCircuitLength,g);

        SinglyNode<Box> currentBoxA = handler.getBoard().getPhaseA().getHead();
        int phaseALength = handler.getBoard().getPhaseA().getLength();
        renderBoard(currentBoxA, phaseALength, g);

        SinglyNode<Box> currentBoxB = handler.getBoard().getPhaseB().getHead();
        int phaseBLength = handler.getBoard().getPhaseB().getLength();
        renderBoard(currentBoxB, phaseBLength, g);

        DoublyNode<Box> currentBoxC = handler.getBoard().getPhaseC().getHead();
        int phaseCLength = handler.getBoard().getPhaseC().getLength();
        renderBoard(currentBoxC, phaseCLength, g);

        DoublyNode<Box> currentBoxD = handler.getBoard().getPhaseD().getHead();
        int phaseDLength = handler.getBoard().getPhaseD().getLength();
        renderBoard(currentBoxD, phaseDLength, g);


        game.starSeller.render(g);

        SinglyNode<Player> currentPlayer = Round.getPlayerOrder().getHead();
        for (int i = 0; i < Round.getPlayerOrder().getLength(); i++) {
            currentPlayer.getData().render(g);
            currentPlayer = (SinglyNode<Player>) currentPlayer.getNext();
        }

        if(!currentBox.isCrossRoads()){
            gameUI.render(g,0);
        } else if (currentBox.isCrossRoads()) {
            gameUI.render(g,1);
            gameUI.render(g,2);
        }
    }

    private void renderBoard(SinglyNode<Box> currentBox, int length, Graphics g) {
        for (int i = 0; i < length; i++) {
            currentBox.getData().render(g);
            currentBox = (SinglyNode<Box>) currentBox.getNext();
        }
    }
}
