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
import com.itcr.ce.datosparty.logic.*;
import com.itcr.ce.datosparty.logic.Event;
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
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    public GameState(Handler handler, Game game){

        super(handler);
        this.game = game;
        this.maxRound = game.getMaxRound();

        font = new Font("Windows Command Prompt", Font.PLAIN,50); // "Times New Roman" for emergencies
        gameUI = new UIManager(handler);

        Animation star = new Animation(200, Assets.star);
        Animation coin = new Animation(200, Assets.coin);

        gameUI.addObject(new UIImageButton(1, height-20, 8, 8, Assets.diceButton, "dice",
                () -> {
                    if(currentPlayer.getMovement()==0) {
                        int diceResult = Dice.roll(1, 6) + Dice.roll(1, 6);
                        currentPlayer.setMovement(diceResult);
                        SoundEffect.DiceRoll();
                        Thread.sleep(3000);
                        game.resumeGame();
                }
                }));

        // Phase A connector
        gameUI.addObject(new UIImageButton(44,38,4,4,Assets.upArrow, "uArrowPhaseA",
                ()->{
                    if (currentBox.getBoxID().equals("phaseA")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject(new UIImageButton(47,42,4,4,Assets.rightArrow,"rArrowPhaseA",
                ()->{
                    if (currentBox.getBoxID().equals("phaseA")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        // Phase C1 connector
        gameUI.addObject(new UIImageButton(48,40,4,4,Assets.upArrow,"uArrowPhaseC1",
                ()->{
                    if (currentBox.getBoxID().equals("phaseC1")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject(new UIImageButton(51,44,4,4,Assets.rightArrow,"rArrowPhaseC1",
                ()->{
                    if (currentBox.getBoxID().equals("phaseC1")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        // Phase B connector
        gameUI.addObject(new UIImageButton(60,52,4,4,Assets.rightArrow,"rArrowPhaseB",
                ()->{
                    if (currentBox.getBoxID().equals("phaseB")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIImageButton(57,55,4,4,Assets.downArrow,"dArrowPhaseB",
                ()->{
                    if (currentBox.getBoxID().equals("phaseB")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        // Phase C2 connector
        gameUI.addObject(new UIImageButton(71,5,4,4,Assets.leftArrow,"lArrowPhaseC2",
                ()->{
                    if (currentBox.getBoxID().equals("phaseC2")) {
                        currentPlayer.setDirection(false);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIImageButton(74,8,4,4,Assets.downArrow,"dArrowPhaseC2",
                ()->{
                    if (currentBox.getBoxID().equals("phaseC2")) {
                        currentPlayer.setDirection(true);
                        int movementLeft = currentPlayer.getMovement();
                        currentPlayer.setMovement(movementLeft);
                        game.resumeGame();
                    }
                }));

        gameUI.addObject((new UIImage(width/2-16, height/2-16, 4*8,2*8,Assets.starPurchaseBackDrop[0],"starPBackDrop")));

        gameUI.addObject((new UIImage(width/2-16, height/2-16, 4*8,8,Assets.buyMsg[0],"buyMsg")));

        gameUI.addObject(new UIImageButton(width/2-8,height/2-10,8,8,Assets.yesBtn,"yesBtn",
                ()->{ game.buyStar(currentPlayer);
                int movementLeft = currentPlayer.getMovement();
                currentPlayer.setMovement(movementLeft);
                game.resumeGame();

        }));
        gameUI.addObject(new UIImageButton(width/2+2,height/2-10,8,8,Assets.noBtn,"noBtn",()->{
            int movementLeft = currentPlayer.getMovement();
            currentPlayer.setMovement(movementLeft);
            game.resumeGame();

        }));

        gameUI.addObject(new UIImageButton(width/2-14,height/2-13,7*2,2*2,Assets.player1Button,"player1Btn",
                ()->{
            EventLogic.stealCoins(currentPlayer, player1);
            game.setCurrentEvent(null);
            game.resumeGame();
                }));

        gameUI.addObject(new UIImageButton(width/2-14,height/2-7,7*2,2*2,Assets.player2Button,"player2Btn",
                ()->{
            EventLogic.stealCoins(currentPlayer, player2);
            game.setCurrentEvent(null);
            game.resumeGame();
                }));
        if (game.getNumberOfPlayers() >= 3) {
            gameUI.addObject(new UIImageButton(width/2+3,height/2-13,7*2,2*2,Assets.player3Button,"player3Btn",
                    ()->{
                EventLogic.stealCoins(currentPlayer, player3);
                game.setCurrentEvent(null);
                game.resumeGame();
                    }));
        }

        if (game.getNumberOfPlayers() >= 4) {
            gameUI.addObject(new UIImageButton(width/2+3,height/2-7,7*2,2*2,Assets.player4Button,"player4Btn",
                    ()->{
                this.player4 = game.getPlayerList().get(3).getData();
                game.setCurrentEvent(null);
                EventLogic.stealCoins(currentPlayer, player4);
                game.resumeGame();
                    }));
        }

        gameUI.addObject(new UIImageButton(9,height-20,4*2,4*2,Assets.endTurnBtn,"endTurnBtn",()->{
            //Round.endTurn();
        }));

        gameUI.addObject(new UIAnimatedImage(0,3,4,4, star,"star"));
        gameUI.addObject(new UIAnimatedImage(0,6,4,4, coin,"coin"));
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
        g.drawString("X" + currentPlayer.getStars(),60,100);
        g.drawString("X" + currentPlayer.getCoins(),60,140);
        gameUI.renderById(g,"star");
        gameUI.renderById(g,"coin");


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

        gameUI.renderById(g,"dice");
        g.drawString("X"+playerMovement,10*16,(height-15)*16);
         if (currentBox.getBoxID().equals("phaseA")) {
            gameUI.renderById(g,"uArrowPhaseA");
            gameUI.renderById(g,"rArrowPhaseA");
        } else if (currentBox.getBoxID().equals("phaseC1")) {
            gameUI.renderById(g,"uArrowPhaseC1");
            gameUI.renderById(g,"rArrowPhaseC1");
        } else if (currentBox.getBoxID().equals("phaseB")) {
            gameUI.renderById(g,"rArrowPhaseB");
            gameUI.renderById(g,"dArrowPhaseB");
        } else if (currentBox.getBoxID().equals("phaseC2")) {
            gameUI.renderById(g,"lArrowPhaseC2");
            gameUI.renderById(g,"dArrowPhaseC2");
        } else if(currentBox.isStarBox()){
            gameUI.renderById(g,"starPBackDrop");
            gameUI.renderById(g,"buyMsg");
            gameUI.renderById(g,"yesBtn");
            gameUI.renderById(g,"noBtn");
        }
        if (currentEvent == Event.STEAL_COINS) {
            gameUI.renderById(g, "starPBackDrop");
            this.player1 = game.getPlayerList().get(0).getData();
            this.player2 = game.getPlayerList().get(1).getData();
            if (currentPlayer != player1) {
                gameUI.renderById(g, "player1Btn");
            }
            if (currentPlayer != player2) {
                gameUI.renderById(g, "player2Btn");
            }
            if (game.getNumberOfPlayers() == 3 &&  currentPlayer !=  game.getPlayerList().get(2).getData()) {
                gameUI.renderById(g, "player3Btn");
            }
            if (game.getNumberOfPlayers() == 4 && currentPlayer != game.getPlayerList().get(3).getData()) {
                gameUI.renderById(g, "player4Btn");
            }
            g.drawString(currentEvent.toString(), (width*13)- 50, 50);
        }
    }

    private void renderBoard(SinglyNode<Box> currentBox, int length, Graphics g) {
        for (int i = 0; i < length; i++) {
            currentBox.getData().render(g);
            currentBox = (SinglyNode<Box>) currentBox.getNext();
        }
    }
}
