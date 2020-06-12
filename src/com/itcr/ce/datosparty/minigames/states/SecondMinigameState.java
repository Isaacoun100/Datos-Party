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

public class SecondMinigameState extends State {
    private UIManager SpaceRunUI;
    private int x,count;
    private boolean first=true;
    private boolean move=false;
    private boolean keypressed=true;


    public SecondMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        SpaceRunUI = new UIManager(handler);
        count=0;
        x=5;

        initPositions();
    }

    public void initPositions(){
        SpaceRunUI.addObject(new UIImageButton(1, 1, 7 * 2, 2 * 2, Assets.player1Button, "player1Btn", new ClickListener() {
            public void onClick() {
                move=true;
            }

        }));

        SpaceRunUI.addObject(new UIImage((float)x,(float)x, 1,2,Assets.player1Static,"MovingPlayer"));

    }


    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(SpaceRunUI);
        SpaceRunUI.tick();
    }

    @Override
    public void render(Graphics g) {

        if(handler.getKeyManager().key_Q && keypressed){
            move=true;
            keypressed=false;
        }

        if(!handler.getKeyManager().key_Q){
          keypressed=true;
        }

        SpaceRunUI.renderById(g,"player1Btn");
        if(move){
            x+=2;
            SpaceRunUI.addObject(new UIImage((float)x,(float)x, 1,2,Assets.player1Static,"MovingPlayer"));
            SpaceRunUI.removeObject("MovingPlayer");
            move=false;
        }

        SpaceRunUI.renderById(g,"MovingPlayer");
    }


}

