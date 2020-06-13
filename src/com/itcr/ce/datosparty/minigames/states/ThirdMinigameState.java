package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class ThirdMinigameState extends State {

    private UIManager uiManager;
    private Handler handler;
    private Color springGreen;
    private int numPlayers;
    private int aim, turns, P1shot, P2shot, P3shot, P4shot;
    private final int targetX = 700;
    private final int targetY = 350;
    private long timer, lastTime;
    private boolean add = true;

    public ThirdMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        uiManager = new UIManager(handler);
        this.handler = handler;
        this.numPlayers = numPlayers;
        this.turns = 0;

        springGreen = new Color(33,250,144);

        uiManager.addObject(new UIImageButton(43, 40, 7*2, 2*2, Assets.player1Button,"shootButton", new ClickListener() {
            @Override
            public void onClick() {
                shoot();
            }
        }));

        uiManager.addObject( new UIImage(0, 0, 16, 16, Assets.blackScope, "blackScope"));
        uiManager.addObject( new UIImage(0, 0, 16, 16, Assets.redScope, "redScope"));

    }


    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
        changeDirection();
        move();
    }

    private void changeDirection() {
        if (aim >= 50) {
            add = false;
        } else if (aim <= -50) {
            add = true;
        }
    }

    private void move() {
        if (add) {
            aim++;
        } else {
            aim--;
        }
    }

    private void shoot() {
        if (turns != numPlayers) {
            if (turns == 0) {
                P1shot = aim;
                turns++;
            } else if (turns == 1) {
                P2shot = aim;
                turns++;
            } else if (turns == 2) {
                P3shot = aim;
                turns++;
            } else if (turns == 3) {
                P4shot = aim;
                turns++;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawString("aim: " + aim, 100, 100);
        g.drawString("P1shot: " + P1shot, 150, 100);
        g.drawString("P2shot: " + P2shot, 150, 150);
        if (numPlayers >= 3) {
            g.drawString("P3shot: " + P3shot, 150, 200);
        }
        if (numPlayers == 4) {
            g.drawString("P4shot: " + P4shot, 150, 250);
        }
        uiManager.changeObjectPos("blackScope", aim * 5 + targetX, targetY);
        uiManager.changeObjectPos("redScope", targetX, targetY);
        uiManager.renderAll(g);
        if(turns == numPlayers) {
            g.setColor(springGreen);
//            g.drawString("Congratulations", 600, 500);
//            g.drawString(winner, 600, 600);
//            g.drawString("you win!", 600, 700);
//            clickerArtistUI.renderById(g, "endGameOkBtn");
//            clickerArtistUI.renderById(g, "rubble1");
//            g.drawString(loser1, 300, 800);
//
//            if (numPlayers >= 3) {
//                clickerArtistUI.renderById(g, "rubble2");
//                g.drawString(loser2, 1100, 800);
//            }
//            if (numPlayers == 4) {
//                clickerArtistUI.renderById(g, "rubble3");
//                g.drawString(loser3, 700, 300);
//            }
        }
    }
}