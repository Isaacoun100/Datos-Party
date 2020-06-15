package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.miniLogic.WinnerLogic;
import com.itcr.ce.datosparty.minigames.miniGameLogic.MemoryList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

/**
 * This class is the Memory mini game, it runs once per cycle of mini games
 */
public class EighthMinigameState extends State {

    private SinglyNode<Player> activePlayer = Round.getPlayerOrder().getHead();
    private Animation titanAn, samAn, tuxAn, koichiAn,ghostAn;
    WinnerLogic gameLogic;
    private boolean active=false;
    private boolean first=true;
    private UIManager MemoryUI;
    private String done = "";
    public Game game;
    int timer=0;

    /**
     * This is the constructor for the  EightMinigame, this will initialize the positions, but not the graphics, this
     * approach doesn't just runs faster with lower cpu usage, but makes the logic much more simpler
     * @param handler
     * @param numPlayers to know how many players are going to be playing the game
     * @param game
     */
    public EighthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
         this.game= game;
         initPositions();
        gameLogic = new WinnerLogic(this.game);
    }

    /**
     * This class initalizes the positions of the sprites and buttons in the game when called, this will run once, when
     * the game is called
     */
    public void initPositions(){
        newPositions();
        MemoryUI = new UIManager(handler);

        MemoryUI.addObject(new UIBackground(Assets.memoryWallpaper, "wp"));

        titanAn = new Animation(100, Assets.titanCard);
        samAn = new Animation(100, Assets.samCard);
        tuxAn = new Animation(100,Assets.tuxCard);
        koichiAn = new Animation(100,Assets.koichiCard);
        ghostAn = new Animation(100,Assets.ghostCard);

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(0)[0],(float)MemoryList.getCoords(0)[1],4*4,6*4, ghostAn,"ghostAn1"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(0)[0], MemoryList.getCoords(0)[1], 4*4, 6*4, Assets.backCard,"ghostCard1", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(1)[0],(float)MemoryList.getCoords(1)[1],4*4,6*4, ghostAn,"ghostAn2"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(1)[0], MemoryList.getCoords(1)[1], 4*4, 6*4, Assets.backCard,"ghostCard2", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(2)[0],(float)MemoryList.getCoords(2)[1],4*4,6*4, titanAn,"titanAn1"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(2)[0], MemoryList.getCoords(2)[1], 4*4, 6*4, Assets.backCard,"titanCard1", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(3)[0],(float)MemoryList.getCoords(3)[1],4*4,6*4, titanAn,"titanAn2"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(3)[0], MemoryList.getCoords(3)[1], 4*4, 6*4, Assets.backCard,"titanCard2", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(4)[0],(float)MemoryList.getCoords(4)[1],4*4,6*4, koichiAn,"koichiAn1"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(4)[0], MemoryList.getCoords(4)[1], 4*4, 6*4, Assets.backCard,"koichiCard1", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(5)[0],(float)MemoryList.getCoords(5)[1],4*4,6*4, koichiAn,"koichiAn2"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(5)[0], MemoryList.getCoords(5)[1], 4*4, 6*4, Assets.backCard,"koichiCard2", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(6)[0],(float)MemoryList.getCoords(6)[1],4*4,6*4, samAn,"samAn1"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(6)[0], MemoryList.getCoords(6)[1], 4*4, 6*4, Assets.backCard,"samCard1", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(7)[0],(float)MemoryList.getCoords(7)[1],4*4,6*4, samAn,"samAn2"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(7)[0], MemoryList.getCoords(7)[1], 4*4, 6*4, Assets.backCard,"samCard2", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(8)[0],(float)MemoryList.getCoords(8)[1],4*4,6*4, tuxAn,"tuxAn1"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(8)[0], MemoryList.getCoords(8)[1], 4*4, 6*4, Assets.backCard,"tuxCard1", new ClickListener() {
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

        MemoryUI.addObject(new UIAnimatedImage((float)MemoryList.getCoords(9)[0],(float)MemoryList.getCoords(9)[1],4*4,6*4, tuxAn,"tuxAn2"));

        MemoryUI.addObject(new UIImageButton(MemoryList.getCoords(9)[0], MemoryList.getCoords(9)[1], 4*4, 6*4, Assets.backCard,"tuxCard2", new ClickListener() {
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
                first=true;
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

    /**
     * This tick is going to run the graphics at the beginning, then it will stop render them, this way every time the
     * mini game is called the cards are going to be different from the last time.
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(MemoryUI);
        if(first){
            initPositions();
            first=false;
        }
        MemoryUI.tick();
    }

    /**
     * This method clears all the positions from the last time it was played, also initializes a new list
     */
    public void newPositions(){
        MemoryList.initCoords();
        MemoryList.shuffle();
    }

    /**
     *  Renders the wallpaper constantly, the animation sprites will run only for 3 seconds, then it will flip the cards
     *  to receive the user input by clicking the cards. A button will be rendered to check the results.
     * @param g graphics parameter passed to gameloop
     */
    @Override
    public void render(Graphics g) {

        MemoryUI.renderById(g,"wp");

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
