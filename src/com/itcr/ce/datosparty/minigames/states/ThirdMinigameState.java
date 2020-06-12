package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
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
    private final int player1bombX = 33 * 16;
    private final int player2bombX = 62 * 16;
    private final int player3bombY = 44 * 16;
    private final int player1bombY = 20 * 16;
    private final int frameData = 32;
    private UIManager uiManager;
    private int numPlayers;
    private long ticks;
    private int bombX = player1bombX;
    private int bombY = player1bombY;
    private KeyManager keys;
    private Game game;
    private Player player1, player2, player3, player4;
    private SinglyList<Player> players = new SinglyList<>();
    private boolean reverseX = false;
    private BombDirections whereTo;
    private SinglyList<Integer> moveTo2 = new SinglyList<>();
    private SinglyList<Integer> moveTo3 = new SinglyList<>();
    private SinglyList<Integer> moveTo1 = new SinglyList<>();

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.game = game;

        this.keys = handler.getKeyManager();

        Animation startbomb =  new Animation(500, Assets.bomb1);

        uiManager = new UIManager(handler);

        this.numPlayers = numPlayers;
        createFrames();

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
    private void createFrames() {
        moveTo2.add(player1bombX);
        moveTo2.add(player1bombX + frameData);
        moveTo2.add(player1bombX + frameData * 2);
        moveTo2.add(player1bombX + frameData * 3);
        moveTo2.add(player1bombX + frameData * 4);
        moveTo2.add(player1bombX + frameData * 5);
        moveTo2.add(player1bombX + frameData * 6);
        moveTo2.add(player1bombX + frameData * 7);
        moveTo2.add(player1bombX + frameData * 8);
        moveTo2.add(player1bombX + frameData * 9);
        moveTo2.add(player1bombX + frameData * 10);
        moveTo2.add(player1bombX + frameData * 11);
        moveTo2.add(player1bombX + frameData * 12);
        moveTo2.add(player1bombX + frameData * 13);
        moveTo2.add(player1bombX + frameData * 14);
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        if (players.getLength() == 2) {
            if (keys.space) { //  && (bombX == player2bombX || bombX == player1bombX)
                System.out.println(ticks);
                test();
//                changeDirection();
            }
            moveBomb();
        }
        if (players.getLength() == 3) {
            if (keys.space && (bombX >= player2bombX || bombX == player1bombX) || bombY >= 668) {
                System.out.println(ticks);
                changeDirection();
            }
            moveBomb();
        }
        ticks += 1;
        setNextRound();
    }

    private void test() {
        SinglyNode<Integer> current = moveTo2.getHead();
        while (current != null) {
           bombX = current.getData();
           current = (SinglyNode<Integer>) current.getNext();
        }
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
                if (bombX < 624) {
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
            if (bombX <= player2bombX && reverseX) {
                bombX += 10;
            } else if (bombX >= player1bombX && !reverseX) {
            bombX -= 10;
            }
        } else if (players.getLength() == 3) {
            if (bombX < player2bombX && whereTo == TO_PLAYER_2) {
                bombX += 10;
            } else if (bombY <= player3bombY && whereTo == TO_PLAYER_3) {
                bombX -= 4;
                bombY += 8;
            } else if (bombY >= player1bombY && bombX <= 802 && whereTo == TO_PLAYER_1) {
                bombX -= 5;
                bombY -= 8;
            }
        }
    }

    public void changeDirection() {
        if (players.getLength() == 2) {
            reverseX = !reverseX;
        } else if (players.getLength() == 3) {
            if (bombX == player1bombX) {
                whereTo = TO_PLAYER_2;
            } else if (bombX >= player2bombX) {
                whereTo = TO_PLAYER_3;
            } else if (bombY <= player3bombY && bombX <= 804) {
            whereTo = TO_PLAYER_1;
            }
        }
    }

    private void winGame(Player player) {
        player.addCoins(10);
        game.resumeGame();
        State.setState(GameLoop.gameDependantStates.get(8).getData());
    }

}