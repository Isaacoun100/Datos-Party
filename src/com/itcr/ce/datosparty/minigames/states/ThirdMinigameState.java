package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.input.KeyManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class ThirdMinigameState extends State {
    private UIManager uiManager;
    private int numPlayers;
    private int bombX = 24 * 16;
    private int bombY = 20 * 16;
    private boolean reverseX = true;
    private KeyManager keys;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        this.keys = handler.getKeyManager();

        Animation startbomb =  new Animation(500, Assets.bomb1);

        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImage(20, 20, 4, 4 * 2, Assets.player1Static,"player1Image"));

        uiManager.addObject(new UIImage(20 * 3, 20, 4, 4 * 2, Assets.player2Static,"player2Image"));

        if (numPlayers >= 3) {
            uiManager.addObject(new UIImage(20, 20 * 2, 4, 4 * 2, Assets.player3Static, "player3Image"));
        }

        if (numPlayers == 4) {
            uiManager.addObject(new UIImage(20 * 2, 20 * 2, 4, 4 * 2, Assets.player4Static, "player4Image"));
        }


        uiManager.addObject(new UIAnimatedImage(0, 0, 8, 8, startbomb, "startbomb"));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        if (keys.space && (bombX == 384 || bombX > 863)) {
            System.out.println("test");
            changeDirection();
        }
        moveRight();
        moveLeft();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
        uiManager.changeObjectPos("startbomb", bombX, bombY);
    }

    public void moveRight() {
        if (bombX < 990-(8*16) && !reverseX) { //862
            bombX += 10;
        }
    }

    public void moveLeft() {
        if (bombX > 24 * 16 && reverseX) { //384
            bombX -= 10;
        }
    }

    public void changeDirection() {
        reverseX = !reverseX;
    }

}