package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Event;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.logic.Turn;
import com.itcr.ce.datosparty.music.SoundEffect;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class GameState extends State{

    private final UIManager gameUI;

    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;
    private final int  maxRound;
    private int playerMovement;
    private Player currentPlayer;
    private Box currentBox;
    private final Font font;
    private final Game game;
    private Event currentEvent;

    public GameState(Handler handler, Game game){

        super(handler);
        this.game = game;
        this.maxRound = game.getMaxRound();

        font = new Font("Windows Command Prompt", Font.PLAIN,50); // "Times New Roman" for emergencies
        gameUI = new UIManager(handler);

        Animation star = new Animation(200, Assets.star);
        Animation coin = new Animation(200, Assets.coin);

        gameUI.addObject(new UIImageButton(1, height-20, 8, 8, Assets.diceButton,
                () -> {
                    if(!currentBox.isCrossRoads()&&!currentBox.isStarBox()) {
                        int diceResult = Dice.roll(1, 6) + Dice.roll(1, 6);
                        currentPlayer.setMovement(diceResult);
                        SoundEffect.DiceRoll();
                        //Thread.sleep(3000);
                        game.resumeGame();
                }
                }));

        // Phase A connector
        gameUI.addObject(new UIImageButton(44,38,4,4,Assets.upArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseA")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject(new UIImageButton(47,42,4,4,Assets.rightArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseA")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        // Phase C1 connector
        gameUI.addObject(new UIImageButton(48,40,4,4,Assets.upArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseC1")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject(new UIImageButton(51,44,4,4,Assets.rightArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseC1")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        // Phase B connector
        gameUI.addObject(new UIImageButton(60,52,4,4,Assets.rightArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseB")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIImageButton(57,55,4,4,Assets.downArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseB")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        // Phase C2 connector
        gameUI.addObject(new UIImageButton(71,5,4,4,Assets.leftArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseC2")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIImageButton(74,8,4,4,Assets.downArrow,
                ()->{
                    if (currentBox.getBoxID().equals("phaseC2")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject((new UIImage(width/2-16, height/2-16, 4*8,2*8,Assets.starPurchaseBackDrop[0])));

        gameUI.addObject((new UIImage(width/2-16, height/2-16, 4*8,8,Assets.buyMsg[0])));

        gameUI.addObject(new UIImageButton(width/2-8,height/2-10,8,8,Assets.yesButton,()->{
            game.buyStar(currentPlayer);
            int movementLeft = currentPlayer.getMovement();
            currentPlayer.setMovement(movementLeft);
            game.resumeGame();

        }));
        gameUI.addObject(new UIImageButton(width/2+2,height/2-10,8,8,Assets.noButton,()->{
            int movementLeft = currentPlayer.getMovement();
            currentPlayer.setMovement(movementLeft);
            game.resumeGame();

        }));

        gameUI.addObject(new UIImageButton(9,height-20,4*2,4*2,Assets.endTurnBtn,()->{
            //Round.endTurn();
        }));

        gameUI.addObject(new UIAnimatedImage(0,3,4,4, star));
        gameUI.addObject(new UIAnimatedImage(0,6,4,4, coin));
    }

    @Override
    public void tick() {
        if(game.getCurrentRound() != maxRound){
            currentPlayer = Turn.getPlayersTurn().getData();
            playerMovement = currentPlayer.getMovement();
            currentBox = currentPlayer.getPosition().getData();
            currentEvent = game.getCurrentEvent();
        } else {
            State.setState(GameLoop.gameDependantStates.get(9).getData());
        }
        handler.getMouseManager().setUiManager(gameUI);
        gameUI.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.mapGuide, -10, 0, null);

        g.setFont(font);
        g.drawString(currentPlayer.getName(),10,40);
        g.drawString("X"+currentPlayer.getStars(),60,100);
        g.drawString("X"+currentPlayer.getCoins(),60,140);
        gameUI.render(g,14);
        gameUI.render(g,15);


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

        SinglyNode<Player> currentPlayerNode = Round.getPlayerOrder().getHead();
        for (int i = 0; i < Round.getPlayerOrder().getLength(); i++) {
            currentPlayerNode.getData().render(g);
            currentPlayerNode = (SinglyNode<Player>) currentPlayerNode.getNext();
        }

        if(!currentBox.isCrossRoads()&&!currentBox.isStarBox()){
            gameUI.render(g,0);
            g.drawString("X"+playerMovement,10*16,(height-15)*16);
        } else if (currentBox.getBoxID().equals("phaseA")) {
            gameUI.render(g,1);
            gameUI.render(g,2);
        } else if (currentBox.getBoxID().equals("phaseC1")) {
            gameUI.render(g,3);
            gameUI.render(g,4);
        } else if (currentBox.getBoxID().equals("phaseB")) {
            gameUI.render(g,5);
            gameUI.render(g,6);
        } else if (currentBox.getBoxID().equals("phaseC2")) {
            gameUI.render(g,7);
            gameUI.render(g,8);
        } else if(currentBox.isStarBox()){
            gameUI.render(g,9);
            gameUI.render(g,10);
            gameUI.render(g,11);
            gameUI.render(g,12);
        } else if (currentPlayerNode.getData().getMovement()==0){
            gameUI.render(g,13);
        }
        if (currentEvent == Event.STEAL_COINS) {
            gameUI.render(g, 9);
        }
    }

    private void renderBoard(SinglyNode<Box> currentBox, int length, Graphics g) {
        for (int i = 0; i < length; i++) {
            currentBox.getData().render(g);
            currentBox = (SinglyNode<Box>) currentBox.getNext();
        }
    }
}
