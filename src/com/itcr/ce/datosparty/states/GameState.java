package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.nodes.DoublyNode;
import com.itcr.ce.datosparty.dataStructures.nodes.Node;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.entities.boxes.Box;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.gfx.ReverseAnimation;
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

    private final int width = GameLauncher.width/16;
    private final int height = GameLauncher.height/16;
    private final int  maxRound;
    private int playerMovement, enoughCoins = 0;
    private Player currentPlayer;
    private boolean clicked = false;

    private Box currentBox;
    private final Font font;
    private final Game game;
    private Event currentEvent;
    private final Player player1;
    private final Player player2;
    private Player player3;
    private Player player4;
    private final EventLogic stealCoinsLogic = new EventLogic();
    private Node<Player> currentPlayerNode;

    public GameState(Handler handler, Game game){

        super(handler);
        this.game = game;
        this.maxRound = game.getMaxRound();
        CircularList<Player> playerList = new CircularList<>();
        font = Assets.bitArtFont.deriveFont(Font.PLAIN, 50); //new Font("Windows Command Prompt", Font.PLAIN,50);
        gameUI = new UIManager(handler);

        this.player1 = game.getPlayerList().get(0).getData();
        playerList.add(player1);
        this.player2 = game.getPlayerList().get(1).getData();
        playerList.add(player2);
        if (game.getNumberOfPlayers() >= 3) {
            this.player3 = game.getPlayerList().get(2).getData();
            playerList.add(player3);
        }
        if (game.getNumberOfPlayers() >= 4) {
            this.player4 = game.getPlayerList().get(3).getData();
            playerList.add(player4);
        }
        currentPlayerNode = playerList.getHead();

        ReverseAnimation star = new ReverseAnimation(200, Assets.star);
        Animation coin = new Animation(200, Assets.coin);
        Animation duel = new Animation(200,Assets.duel);
        Animation stealCoins = new Animation(200,Assets.stealCoins);
        Animation giveCoins = new Animation(200, Assets.giveCoins);
        Animation loseStar = new Animation(200,Assets.loseStar);
        Animation win2Stars = new Animation(200,Assets.win2Stars);
        Animation win5Stars = new Animation(200,Assets.win5Stars);
        Animation stealStar = new Animation(200,Assets.stealStar);
        Animation teleport = new Animation(200,Assets.teleport);
        Animation swapPlace = new Animation(200,Assets.swapPlace);

        gameUI.addObject(new UIImageButton(1, height-20, 8, 8, Assets.diceButton, "dice",
                () -> {
                    if(!currentPlayer.getCurrentTurn()) {
                        currentPlayer.setCurrentTurn(true);
                        int diceResult = Dice.roll(1, 6) + Dice.roll(1, 6);
                        currentPlayer.setMovement(diceResult);
                        SoundEffect.DiceRoll();
//                       Thread.sleep(3000);
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

        gameUI.addObject((new UIImage((float)width/2-16, (float)height/2-16, 4*8,2*8,Assets.starPurchaseBackDrop[0],"starPBackDrop")));

        gameUI.addObject((new UIImage((float)width/2-16, (float)height/2-16, 4*8,8,Assets.buyMsg[0],"buyMsg")));

        gameUI.addObject((new UIImage((float)width/2-16, (float)height/2-15   , 4*8,8,Assets.noCoinsMsg,"noCoinsMsg")));

        gameUI.addObject((new UIImage((float)width/2-16, (float)height/2-16, 4*8,8,Assets.enoughCoins,"enoughCoins")));


        gameUI.addObject(new UIImageButton((float)width/2-4,(float)height/2-10,8,8,Assets.okBtn,"okBtnStars",
                ()->{ if(clicked){
                    int movementLeft = currentPlayer.getMovement();
                    currentPlayer.setMovement(movementLeft);
                    this.clicked = false;
                    if(enoughCoins==1) {
                        this.enoughCoins = 0;
                        game.buyStar(currentPlayer);
                        if (enoughCoins == 0) {
                            game.resumeGame();
                        }
                    }
                }
                }));

        gameUI.addObject(new UIImageButton((float)width/2-4,(float)height/2-10,8,8,Assets.okBtn,"okBtnNoStars",
                ()->{ if(clicked){
                    this.enoughCoins = 0;
                    int movementLeft = currentPlayer.getMovement();
                    currentPlayer.setMovement(movementLeft);
                    this.clicked = false;
                    if (enoughCoins == 0) {
                        game.resumeGame();
                    }
                }
                }));

        gameUI.addObject(new UIImageButton((float)width/2-8,(float)height/2-10,8,8,Assets.yesBtn,"yesBtn",
                ()->{
                    if(currentBox.isStarBox()&&!clicked) {
                        int coins = currentPlayer.getCoins();
                        if(coins>=10) {
                            this.enoughCoins = 1;
                        } else{
                            this.enoughCoins = 2;
                        }
                        this.clicked = true;
                    }
                }));
        gameUI.addObject(new UIImageButton((float)width/2+2,(float)height/2-10,8,8,Assets.noBtn,"noBtn",()->{
            if(!clicked) {
                int movementLeft = currentPlayer.getMovement();
                currentPlayer.setMovement(movementLeft);
                game.resumeGame();
            }

        }));

        gameUI.addObject(new UIImageButton((float)width/2-14,(float)height/2-22, 8,2*8,Assets.stealCoins1,"player1Btn",
                ()->{
                    if(game.getCurrentEvent()== Event.STEAL_COINS){
                        stealCoinsLogic.stealCoins(currentPlayer, player1);
                        game.setCurrentEvent(null);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIAnimatedImage(0,3,4,4, star,"star1"));
        gameUI.addObject(new UIAnimatedImage(0,6,4,4, coin,"coin1"));

        gameUI.addObject(new UIImageButton((float)width/2-14,(float)height/2-7, 8,2*8,Assets.stealCoins2,"player2Btn",
                ()->{
                    if(game.getCurrentEvent()== Event.STEAL_COINS) {
                        stealCoinsLogic.stealCoins(currentPlayer, player2);
                        game.setCurrentEvent(null);
                        game.resumeGame();
                    }
                }));
        gameUI.addObject(new UIAnimatedImage(11,3,4,4, star,"star2"));
        gameUI.addObject(new UIAnimatedImage(11,6,4,4, coin,"coin2"));

        if (game.getNumberOfPlayers() >= 3) {
            gameUI.addObject(new UIImageButton((float)width/2+6,(float)height/2-22, 8,2*8,Assets.stealCoins3,"player3Btn",
                    ()->{
                        if(game.getCurrentEvent()== Event.STEAL_COINS) {
                            stealCoinsLogic.stealCoins(currentPlayer, player3);
                            game.setCurrentEvent(null);
                            game.resumeGame();
                        }
                    }));
            gameUI.addObject(new UIAnimatedImage(0,15,4,4, star,"star3"));
            gameUI.addObject(new UIAnimatedImage(0,18,4,4, coin,"coin3"));
        }

        if (game.getNumberOfPlayers() >= 4) {
            gameUI.addObject(new UIImageButton((float)width/2+6,(float)height/2-7, 8,2*8,Assets.stealCoins4,"player4Btn",
                    ()->{
                        if(game.getCurrentEvent()== Event.STEAL_COINS) {
                            stealCoinsLogic.stealCoins(currentPlayer, player4);
                            game.setCurrentEvent(null);
                            stealCoinsLogic.stealCoins(currentPlayer, player4);
                            game.resumeGame();
                        }
                    }));
            gameUI.addObject(new UIAnimatedImage(11,15,4,4, star,"star4"));
            gameUI.addObject(new UIAnimatedImage(11,18,4,4, coin,"coin4"));
        }

        gameUI.addObject(new UIImageButton(9,height-20,4*2,4*2,Assets.endTurnBtn,"endTurnBtn",()->{
            if (currentPlayer.getCurrentTurn() && currentPlayer.getMovement()==0) {
                currentPlayer.setCurrentTurn(false);
                game.resumeGame();
            }
        }));

        gameUI.addObject((new UIImage((float)width/2-8, (float)height/2-24, 2*8,3*8,Assets.eventBackDrop,"eventBackDrop")));

        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,stealCoins,"stealCoins"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-6, (float)height/2-18, 4*3,2*3,duel,"duel"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,giveCoins,"giveCoins"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,loseStar,"loseStar"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,win2Stars,"win2Stars"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,win5Stars,"win5Stars"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 4*2,4*2,stealStar,"stealStar"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-3, (float)height/2-18, 4,2*4,teleport,"teleport"));
        gameUI.addObject(new UIAnimatedImage((float)width/2-4, (float)height/2-18, 2*4,2*4,swapPlace,"swapPlace"));

        gameUI.addObject(new UIImageButton((float)width/2-4,(float)height/2-2,8,8,Assets.okBtn,"okBtnEvents",
                ()->{ if(currentEvent != null){
                    int movementLeft = currentPlayer.getMovement();
                    currentPlayer.setMovement(movementLeft);
                    game.setCurrentEvent(null);
                    game.resumeGame();
                }
                }));

    }

    @Override
    public void tick() {
        if(game.getCurrentRound() < maxRound){
            currentPlayer = Turn.getPlayersTurn().getData();
            playerMovement = currentPlayer.getMovement();
            currentBox = currentPlayer.getPosition().getData();
            currentEvent = game.getCurrentEvent();
            if(!currentPlayer.getCurrentTurn()){
                currentPlayerNode = currentPlayerNode.getNext();
            }
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
        g.drawString("it´s "+currentPlayer.getName()+"'s turn!",550,1020);
        g.drawString("Round: "+game.getCurrentRound(),1300,90);

        g.drawString(player1.getName(), 10, 40);
        g.drawString("X" + player1.getStars(), 60, 100);
        g.drawString("X" + player1.getCoins(), 60, 140);
        gameUI.renderById(g, "star1");
        gameUI.renderById(g, "coin1");

        g.drawString(player2.getName(), 191, 40);
        g.drawString("X" + player2.getStars(), 60 + 181, 100);
        g.drawString("X" + player2.getCoins(), 60 + 181, 140);
        gameUI.renderById(g, "star2");
        gameUI.renderById(g, "coin2");

        if (game.getNumberOfPlayers() >= 3) {
            g.drawString(player3.getName(), 10, 235);
            g.drawString("X" + player3.getStars(), 60, 100+195);
            g.drawString("X" + player3.getCoins(), 60, 140+195);
            gameUI.renderById(g, "star3");
            gameUI.renderById(g, "coin3");
        }

        if (game.getNumberOfPlayers() >= 4) {
            g.drawString(player4.getName(), 191, 235);
            g.drawString("X" + player4.getStars(), 60 + 181, 100 + 195);
            g.drawString("X" + player4.getCoins(), 60 + 181, 140 + 195);
            gameUI.renderById(g, "star4");
            gameUI.renderById(g, "coin4");
        }

        SinglyNode<Box> currentBoxMain = handler.getBoard().getMainCircuit().getHead();
        int mainCircuitLength = handler.getBoard().getMainCircuit().getLength();
        renderBoard(currentBoxMain, mainCircuitLength, g);

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

        gameUI.renderById(g, "dice");
        g.drawString("X" + playerMovement, 10 * 16, (height - 15) * 16);

        if( currentPlayer.getMovement()!=0) {
            switch (currentBox.getBoxID()) {
                case "phaseA" -> {
                    gameUI.renderById(g, "uArrowPhaseA");
                    gameUI.renderById(g, "rArrowPhaseA");
                }
                case "phaseC1" -> {
                    gameUI.renderById(g, "uArrowPhaseC1");
                    gameUI.renderById(g, "rArrowPhaseC1");
                }
                case "phaseB" -> {
                    gameUI.renderById(g, "rArrowPhaseB");
                    gameUI.renderById(g, "dArrowPhaseB");
                }
                case "phaseC2" -> {
                    gameUI.renderById(g, "lArrowPhaseC2");
                    gameUI.renderById(g, "dArrowPhaseC2");
                }
            }
        }

        if (currentBox.isStarBox() && !clicked) {
            gameUI.renderById(g, "starPBackDrop");
            gameUI.renderById(g, "buyMsg");
            gameUI.renderById(g, "yesBtn");
            gameUI.renderById(g, "noBtn");
        } else if (currentBox.isStarBox() && enoughCoins == 1 && clicked) {
            gameUI.renderById(g, "starPBackDrop");
            gameUI.renderById(g, "enoughCoins");
            gameUI.renderById(g, "okBtnStars");
        } else if (currentBox.isStarBox() && enoughCoins == 2 && clicked) {
            gameUI.renderById(g, "starPBackDrop");
            gameUI.renderById(g, "noCoinsMsg");
            gameUI.renderById(g, "okBtnNoStars");
        }

        if (currentEvent != null) {
            gameUI.renderById(g, "eventBackDrop");
            if (currentEvent == Event.STEAL_COINS) {
                gameUI.renderById(g, "stealCoins");
                g.drawString("STEAL", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("COINS!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                if (currentPlayer != player1) {
                    gameUI.renderById(g, "player1Btn");
                }
                if (currentPlayer != player2) {
                    gameUI.renderById(g, "player2Btn");
                }
                if (game.getNumberOfPlayers() >= 3 && currentPlayer != player3) {
                    gameUI.renderById(g, "player3Btn");
                }
                if (game.getNumberOfPlayers() == 4 && currentPlayer != player4) {
                    gameUI.renderById(g, "player4Btn");
                }
            } else if (currentEvent == Event.DUEL) {
                gameUI.renderById(g, "duel");
                g.drawString("DUEL!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.GIFT_COINS) {
                gameUI.renderById(g, "giveCoins");
                g.drawString("GIFT", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("COINS!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.LOSE_STAR) {
                gameUI.renderById(g, "loseStar");
                g.drawString("LOSE", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("STAR!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.SWAP_PLAYERS) {
                gameUI.renderById(g, "swapPlace");
                g.drawString("SWAP!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.TELEPORT) {
                gameUI.renderById(g, "teleport");
                g.drawString("TELE-", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("PORT!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.WIN_2_STARS) {
                gameUI.renderById(g, "win2Stars");
                g.drawString("WIN 2", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("STARS!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.WIN_5_STARS) {
                gameUI.renderById(g, "win5Stars");
                g.drawString("WIN 5", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("STARS!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            } else if (currentEvent == Event.STEAL_STAR) {
                gameUI.renderById(g, "stealStar");
                g.drawString("STEAL", ((width * 16)) / 2 - 55, (height * 16) / 2 - 120);
                g.drawString("STAR!", ((width * 16)) / 2 - 55, (height * 16) / 2 - 88);
                gameUI.renderById(g, "okBtnEvents");
            }
        }

        if (currentPlayer.getCurrentTurn() && currentPlayer.getMovement()==0){
            gameUI.renderById(g,"endTurnBtn");
        }
    }
    private void renderBoard(SinglyNode<Box> currentBox, int length, Graphics g) {
        for (int i = 0; i < length; i++) {
            currentBox.getData().render(g);
            currentBox = (SinglyNode<Box>) currentBox.getNext();
        }
    }
}
