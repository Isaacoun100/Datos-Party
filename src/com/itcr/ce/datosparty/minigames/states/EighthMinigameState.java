package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.minigames.miniLogic.FirstMiniGameLogic;
import com.itcr.ce.datosparty.minigames.miniGameLogic.MemoryList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.ClickListener;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class EighthMinigameState extends State {

    private SinglyNode<Player> activePlayer = Round.getPlayerOrder().getHead();
    private Animation titanAn, samAn, tuxAn, koichiAn,ghostAn;
    private SinglyNode<int[]> newCoords;
    private boolean active=false;
    private UIManager MemoryUI;
    private String done = "";
    int timer=0;

    public EighthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        MemoryList.initCoords();
        newCoords=MemoryList.getHead();
        MemoryUI = new UIManager(handler);
        FirstMiniGameLogic gameLogic = new FirstMiniGameLogic(game);

        titanAn = new Animation(100, Assets.titanCard);
        samAn = new Animation(100, Assets.samCard);
        tuxAn = new Animation(100,Assets.tuxCard);
        koichiAn = new Animation(100,Assets.koichiCard);
        ghostAn = new Animation(100,Assets.ghostCard);


        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, ghostAn,"ghostAn1"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"ghostCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='g' && active){
                        done+="g";
                    }
                }
                else{
                    done+="g";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, ghostAn,"ghostAn2"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"ghostCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='G' && active){
                        done+="G";
                    }
                }
                else{
                    done+="G";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, titanAn,"titanAn1"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"titanCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='t' && active){
                        done+="t";
                    }
                }
                else{
                    done+="t";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, titanAn,"titanAn2"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"titanCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='T' && active){
                        done+="T";
                    }
                }
                else{
                    done+="T";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, koichiAn,"koichiAn1"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"koichiCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='k' && active){
                        done+="k";
                    }
                }
                else{
                    done+="k";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, koichiAn,"koichiAn2"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"koichiCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='K' && active){
                        done+="K";
                    }
                }
                else{
                    done+="K";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, samAn,"samAn1"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"samCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='s' && active){
                        done+="s";
                    }
                }
                else{
                    done+="s";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, samAn,"samAn2"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"samCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='S' && active){
                        done+="S";
                    }
                }
                else{
                    done+="S";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, tuxAn,"tuxAn1"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"tuxCard1", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='x' && active){
                        done+="x";
                    }
                }
                else{
                    done+="x";
                }
            }}));

        newCoords= (SinglyNode<int[]>) newCoords.getNext();

        MemoryUI.addObject(new UIAnimatedImage((float)newCoords.getData()[0],(float)newCoords.getData()[1],4*4,6*4, tuxAn,"tuxAn2"));

        MemoryUI.addObject(new UIImageButton(newCoords.getData()[0], newCoords.getData()[1], 4*4, 6*4, Assets.backCard,"tuxCard2", new ClickListener() {
            @Override
            public void onClick() {
                if(done.length()>0){
                    if(done.charAt(0)!='X' && active){
                        done+="X";
                    }
                }
                else{
                    done+="X";
                }
            }}));

        MemoryUI.addObject(new UIImageButton(50, 20, 3*3, 3*3, Assets.checkButton,"checkButton", new ClickListener() {
            @Override
            public void onClick() {
                if(!active){
                    done=done.toUpperCase();

                    if(done.charAt(0)==done.charAt(1)){
                        gameLogic.winGame(activePlayer.getData());
                        activePlayer=Round.getPlayerOrder().getHead();
                        State.setState(GameLoop.gameDependantStates.get(8).getData());
                    }

                    else{
                        if(activePlayer.getNext()!=null){
                            activePlayer= (SinglyNode<Player>) activePlayer.getNext();
                        }
                        else{
                            activePlayer=Round.getPlayerOrder().getHead();
                        }
                    }

                    timer=0;
                    done="";
                }
            }}));


    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(MemoryUI);
        MemoryUI.tick();
    }

    @Override
    public void render(Graphics g) {

        if(done.length()==2){
            MemoryUI.renderById(g,"checkButton");
            active=false;
        }
        else{
            if(timer>300){

                active=true;

                MemoryUI.renderById(g,"ghostCard1");
                MemoryUI.renderById(g,"ghostCard2");

                MemoryUI.renderById(g,"titanCard1");
                MemoryUI.renderById(g,"titanCard2");

                MemoryUI.renderById(g,"koichiCard1");
                MemoryUI.renderById(g,"koichiCard2");

                MemoryUI.renderById(g,"samCard1");
                MemoryUI.renderById(g,"samCard2");

                MemoryUI.renderById(g,"tuxCard1");
                MemoryUI.renderById(g,"tuxCard2");

            }
            else{
                active=false;


                MemoryUI.renderById(g,"ghostAn1");
                MemoryUI.renderById(g,"ghostAn2");

                MemoryUI.renderById(g,"titanAn1");
                MemoryUI.renderById(g,"titanAn2");

                MemoryUI.renderById(g,"koichiAn1");
                MemoryUI.renderById(g,"koichiAn2");

                MemoryUI.renderById(g,"samAn1");
                MemoryUI.renderById(g,"samAn2");

                MemoryUI.renderById(g,"tuxAn1");
                MemoryUI.renderById(g,"tuxAn2");

                timer++;
            }
        }

    }

}
