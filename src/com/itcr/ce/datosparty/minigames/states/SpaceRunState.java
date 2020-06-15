package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.miniLogic.WinnerLogic;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIBackground;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import java.awt.*;

/**
 * This class is the Space Run mini game, it runs once per cycle of mini games
 */
public class SpaceRunState extends State {
    private SinglyNode<Player> activePlayer = Round.getPlayerOrder().getHead();
    private boolean firstMove, secondMove, thirdMove, fourthMove;
    private boolean isZpressed,isQpressed,isPpressed,isMpressed;
    private int numPlayers=Round.getPlayerOrder().getLength();
    private int firsty,secondy,thirdy,fourthy,count;
    private UIManager SpaceRunUI,StarUI;
    private WinnerLogic winner;
    private final Font font;
    private boolean first=true;
    private Animation star;
    private Game game;


    /**
     * Constructor for the SpaceRunState
     * @param handler
     * @param numPlayers
     * @param game
     */
    public SpaceRunState(Handler handler, int numPlayers, Game game) {
        super(handler);
        this.game=game;
        font = Assets.upHeavett.deriveFont(Font.BOLD, 32);
        winner = new WinnerLogic(this.game);
        SpaceRunUI = new UIManager(handler);
        StarUI = new UIManager(handler);
    }

    /**
     * This method initializes the positions  for the game to be on set every time it's executed
     */
    public void initPositions(){
        first=false;
        isQpressed=isZpressed=isPpressed=isMpressed=true;
        firstMove=secondMove=thirdMove=fourthMove=true;
        firsty=secondy=thirdy=fourthy=65;
        initGraphics();
        count=0;
    }

    /**
     * This method intializes the visuals, graphics, images and animations.
     */
    public void initGraphics(){

        SpaceRunUI.addObject(new UIImage((float)15,(float)firsty, 2*3,3*3,Assets.firstShip,"FirstPlayer"));
        SpaceRunUI.addObject(new UIImage((float)40,(float)secondy, 2*3,3*3,Assets.secondShip,"SecondPlayer"));

        if(numPlayers>=3){
            SpaceRunUI.addObject(new UIImage((float)65,(float)thirdy, 2*3,3*3,Assets.thirdShip,"ThirdPlayer"));
        }
        if(numPlayers>=4){
            SpaceRunUI.addObject(new UIImage((float)90,(float)fourthy, 2*3,3*3,Assets.fourthShip,"FourthPlayer"));
        }

        StarUI.addObject(new UIBackground(Assets.spaceWallpaper,"wp"));
        star = new Animation(100,Assets.bornStar);


        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star1"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star2"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star3"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star4"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star5"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star6"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star7"));
        StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star8"));

    }

    /**
     * The tick initializes once the state is called, the main difference with the constructor is that the constructor
     * runs when the program itself starts but the tick each time the state is called
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(SpaceRunUI);
        if(first)initPositions();
        SpaceRunUI.tick();
    }

    /**
     * Render method runs constantly in a loop once the game is started and finishes the moment the game also finishes,
     * this method also is the method in charge of rendering the graphics
     * @param g graphics parameter passed to GameLoop
     */
    @Override
    public void render(Graphics g) {

        StarUI.renderAll(g);

        g.setFont(font);
        Color color = new Color(255, 255, 255);
        g.setColor(color);

        if(count>100){

            StarUI.removeObject("star1");
            StarUI.removeObject("star2");
            StarUI.removeObject("star3");
            StarUI.removeObject("star4");
            StarUI.removeObject("star5");
            StarUI.removeObject("star6");
            StarUI.removeObject("star7");
            StarUI.removeObject("star8");

            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star1"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star2"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star3"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star4"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star5"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star6"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star7"));
            StarUI.addObject(new UIAnimatedImage((float) Dice.roll(1,98),(float)Dice.roll(1,70),2,2, star,"star8"));
            count=0;
        }

        if(numPlayers==2){
            if(handler.getKeyManager().key_Q && isQpressed){
                firstMove =true;
                isQpressed =false;
            }
            if(handler.getKeyManager().key_P && isPpressed){
                secondMove =true;
                isPpressed =false;
            }
            if(!handler.getKeyManager().key_Q){
                isQpressed =true;
            }
            if(!handler.getKeyManager().key_P){
                isPpressed =true;
            }
            if(firstMove){
                firsty-=1;
                SpaceRunUI.addObject(new UIImage((float)15,(float)firsty, 2*3,3*3,Assets.firstShip,"FirstPlayer"));
                SpaceRunUI.removeObject("FirstPlayer");
                firstMove =false;
            }
            if(secondMove){
                secondy-=1;
                SpaceRunUI.addObject(new UIImage((float)40,(float)secondy, 2*3,3*3,Assets.secondShip,"SecondPlayer"));
                SpaceRunUI.removeObject("SecondPlayer");
                secondMove=false;
            }

            SpaceRunUI.renderById(g,"FirstPlayer");

            if(firsty>10){
                g.drawString(activePlayer.getData().getName(), 13*15, 3*15);
                g.drawString("Spam Q!", 13*15, 7*15);
            }

            SpaceRunUI.renderById(g,"SecondPlayer");

            if(secondy>10){
                g.drawString(activePlayer.getNext().getData().getName(), 38*15, 3*15);
                g.drawString("Spam P!", 38*15, 7*15);
            }

            if(firsty==-3){
                winner.winGame(activePlayer.getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            else if(secondy==-3){
                winner.winGame(activePlayer.getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }

        }
        else if(numPlayers==3){
            if(handler.getKeyManager().key_Q && isQpressed){
                firstMove =true;
                isQpressed =false;
            }
            if(handler.getKeyManager().key_P && isPpressed){
                secondMove =true;
                isPpressed =false;
            }
            if(handler.getKeyManager().key_Z && isZpressed){
                thirdMove =true;
                isZpressed =false;
            }

            if(!handler.getKeyManager().key_Q){
                isQpressed =true;
            }
            if(!handler.getKeyManager().key_P){
                isPpressed =true;
            }
            if(!handler.getKeyManager().key_Z){
                isZpressed =true;
            }

            if(firstMove){
                firsty-=1;
                SpaceRunUI.addObject(new UIImage((float)15,(float)firsty, 2*3,3*3,Assets.firstShip,"FirstPlayer"));
                SpaceRunUI.removeObject("FirstPlayer");
                firstMove =false;
            }
            if(secondMove){
                secondy-=1;
                SpaceRunUI.addObject(new UIImage((float)40,(float)secondy, 2*3,3*3,Assets.secondShip,"SecondPlayer"));
                SpaceRunUI.removeObject("SecondPlayer");
                secondMove=false;
            }
            if(thirdMove){
                thirdy-=1;
                SpaceRunUI.addObject(new UIImage((float)65,(float)thirdy, 2*3,3*3,Assets.thirdShip,"ThirdPlayer"));
                SpaceRunUI.removeObject("ThirdPlayer");
                thirdMove=false;
            }

            SpaceRunUI.renderById(g,"FirstPlayer");

            if(firsty>10){
                g.drawString(activePlayer.getData().getName(), 13*15, 3*15);
                g.drawString("Spam Q!", 13*15, 7*15);
            }

            SpaceRunUI.renderById(g,"SecondPlayer");

            if(secondy>10){
                g.drawString(activePlayer.getNext().getData().getName(), 38*15, 3*15);
                g.drawString("Spam P!", 38*15, 7*15);
            }

            SpaceRunUI.renderById(g,"ThirdPlayer");

            if(thirdy>10){
                g.drawString(activePlayer.getNext().getNext().getData().getName(), 63*15, 3*15);
                g.drawString("Spam Z!", 63*15, 7*15);
            }

            if(firsty==-3){
                winner.winGame(activePlayer.getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            else if(secondy==-3){
                winner.winGame(activePlayer.getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            else if(thirdy==-3){
                winner.winGame(activePlayer.getNext().getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }

        }
        else if(numPlayers==4){
            if(handler.getKeyManager().key_Q && isQpressed){
                firstMove =true;
                isQpressed =false;
            }
            if(handler.getKeyManager().key_P && isPpressed){
                secondMove =true;
                isPpressed =false;
            }
            if(handler.getKeyManager().key_Z && isZpressed){
                thirdMove =true;
                isZpressed =false;
            }
            if(handler.getKeyManager().key_M && isMpressed){
                fourthMove =true;
                isMpressed =false;
            }

            if(!handler.getKeyManager().key_Q){
                isQpressed =true;
            }
            if(!handler.getKeyManager().key_P){
                isPpressed =true;
            }
            if(!handler.getKeyManager().key_Z){
                isZpressed =true;
            }
            if(!handler.getKeyManager().key_M){
                isMpressed =true;
            }


            if(firstMove){
                firsty-=1;
                SpaceRunUI.addObject(new UIImage((float)15,(float)firsty, 2*3,3*3,Assets.firstShip,"FirstPlayer"));
                SpaceRunUI.removeObject("FirstPlayer");
                firstMove =false;
            }
            if(secondMove){
                secondy-=1;
                SpaceRunUI.addObject(new UIImage((float)40,(float)secondy, 2*3,3*3,Assets.secondShip,"SecondPlayer"));
                SpaceRunUI.removeObject("SecondPlayer");
                secondMove=false;
            }
            if(thirdMove){
                thirdy-=1;
                SpaceRunUI.addObject(new UIImage((float)65,(float)thirdy, 2*3,3*3,Assets.thirdShip,"ThirdPlayer"));
                SpaceRunUI.removeObject("ThirdPlayer");
                thirdMove=false;
            }
            if(fourthMove){
                fourthy-=1;
                SpaceRunUI.addObject(new UIImage((float)90,(float)fourthy, 2*3,3*3,Assets.fourthShip,"FourthPlayer"));
                SpaceRunUI.removeObject("FourthPlayer");
                fourthMove=false;
            }

            SpaceRunUI.renderById(g,"FirstPlayer");

            if(firsty>10){
                g.drawString(activePlayer.getData().getName(), 13*15, 3*15);
                g.drawString("Spam Q!", 13*15, 7*15);
            }

            SpaceRunUI.renderById(g,"SecondPlayer");

            if(secondy>10){
                g.drawString(activePlayer.getNext().getData().getName(), 38*15, 3*15);
                g.drawString("Spam P!", 38*15, 7*15);
            }

            SpaceRunUI.renderById(g,"ThirdPlayer");

            if(thirdy>10){
                g.drawString(activePlayer.getNext().getNext().getData().getName(), 63*15, 3*15);
                g.drawString("Spam Z!", 63*15, 7*15);
            }

            SpaceRunUI.renderById(g,"FourthPlayer");

            if(fourthy>10){
                g.drawString(activePlayer.getNext().getNext().getNext().getData().getName(), 88*15, 3*15);
                g.drawString("Spam M!", 88*15, 7*15);
            }

            if(firsty==-3){
                winner.winGame(activePlayer.getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            if(secondy==-3){
                winner.winGame(activePlayer.getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            if(thirdy==-3){
                winner.winGame(activePlayer.getNext().getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }
            if(fourthy==-3){
                winner.winGame(activePlayer.getNext().getNext().getNext().getData());
                first=true;
                State.setState(GameLoop.gameDependantStates.get(8).getData());
            }

        }

        count++;
    }


}

