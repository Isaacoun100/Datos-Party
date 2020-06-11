package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.input.KeyManager;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.miniGameLogic.BombDirections;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

import static com.itcr.ce.datosparty.minigames.miniGameLogic.BombDirections.*;

public class ThirdMinigameState extends State {
    private UIManager uiManager;
    private int numPlayers;
    private long ticks;
    private int bombX = 24 * 16;
    private int bombY = 20 * 16;
    private boolean moveX = true;
    private KeyManager keys;
    private Game game;
    private Player player1, player2, player3, player4;
    private SinglyList<Player> players = new SinglyList<>();
    private boolean reverseX = true;
    private BombDirections whereTo;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.game = game;

        this.keys = handler.getKeyManager();

        Animation startbomb =  new Animation(500, Assets.bomb1);

        uiManager = new UIManager(handler);

        this.numPlayers = numPlayers;

        this.player1 = game.getPlayerList().get(0).getData();
        players.add(player1);
        uiManager.addObject(new UIImage(30, 15, 4*2, 8 * 2, Assets.player1Static,"player1Image"));

        this.player2 = game.getPlayerList().get(1).getData();
        players.add(player2);
        uiManager.addObject(new UIImage(20 * 3, 15, 4 * 2, 8 * 2, Assets.player2Static,"player2Image"));

        if (numPlayers >= 3) {
            this.player3 = game.getPlayerList().get(2).getData();
            players.add(player3);
            uiManager.addObject(new UIImage(46, 40, 4 * 2, 8 * 2, Assets.player3Static, "player3Image"));
        }

        if (numPlayers == 4) {
            this.player4 = game.getPlayerList().get(3).getData();
            players.add(player4);
            uiManager.addObject(new UIImage(46, 20 * 2, 4 * 2, 8 * 2, Assets.player4Static, "player4Image"));
        }


        uiManager.addObject(new UIAnimatedImage(bombX, bombY, 8, 8, startbomb, "startbomb"));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        if (players.getLength() == 2) {
            if (keys.space && (bombX == 384 || bombX == 864)) {
                System.out.println(ticks);
                changeDirection();
                moveX = true;
            }
            moveBomb();
        }
        if (players.getLength() == 3) {
            if (keys.space && (bombX == 384 || bombX == 864) || bombX == 624) {
                System.out.println(ticks);
                changeDirection();
                moveX = true;
            }
            moveBomb();
        }
        ticks += 1;
        setNextRound();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderById(g, "player1Image");
        uiManager.renderById(g, "player2Image");
        if (players.getLength() >= 3) {
            uiManager.renderById(g, "player3Image");
            uiManager.changeObjectPos("player3Image", 46 * 16, 40 * 16);
        }
        if (players.getLength() == 4) {
            uiManager.renderById(g, "player4Image");
            uiManager.changeObjectPos("player3Image", 30 * 16, 40 * 16);
            uiManager.changeObjectPos("player4Image", 60 * 16, 40 * 16);
        }
        uiManager.renderById(g, "startbomb");
        uiManager.changeObjectPos("startbomb", bombX, bombY);
    }

    public void setNextRound() {
        if (players.getLength() == 2) {
            if (ticks >= 500) {
                if (bombX < 623) {
                    winGame(player1);
                } else {
                    winGame(player2);
                }
            }
        } else if (players.getLength() == 3) {

        }

    }

    public void moveBomb() {
        if (players.getLength() == 2) {
            if (bombX < 990-(8*16) && !reverseX) { //862
                bombX += 10;
            } else if (bombX > 24 * 16 && reverseX) { //384
                bombX -= 10;
            }
        }
    }

    public void changeDirection() {
        if (players.getLength() == 2) {
            reverseX = !reverseX;
        } else if (players.getLength() == 3) {
            if (bombY != 320) {
                whereTo = TO_PLAYER_1;
            } else if (bombX == 384) {
                whereTo = TO_PLAYER_2;
            } else if (bombX == 864) {
                whereTo = TO_PLAYER_3;
            }
        }
    }

    private void winGame(Player player) {
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

}