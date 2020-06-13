package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.minigames.miniLogic.FirstMiniGameLogic;
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

public class SecondMinigameState extends State {
    private SinglyNode<Player> activePlayer = Round.getPlayerOrder().getHead();
    private boolean firstMove, secondMove, thirdMove, fourthMove;
    private boolean isZpressed,isQpressed,isPpressed,isMpressed;
    private int numPlayers=Round.getPlayerOrder().getLength();
    private int firsty,secondy,thirdy,fourthy,count;
    private UIManager SpaceRunUI,StarUI;
    private FirstMiniGameLogic winner;
    private final Font font;
    private boolean first=true;
    private Animation star;
    private Game game;



    public SecondMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);
        this.game=game;
        font = Assets.upHeavett.deriveFont(Font.BOLD, 32);
        winner = new FirstMiniGameLogic(this.game);
        SpaceRunUI = new UIManager(handler);
        StarUI = new UIManager(handler);
    }

    public void initPositions(){
        first=false;
        isQpressed=isZpressed=isPpressed=isMpressed=true;
        firstMove=secondMove=thirdMove=fourthMove=true;
        firsty=secondy=thirdy=fourthy=65;
        initGraphics();
        count=0;
    }

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


    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(SpaceRunUI);
        if(first)initPositions();
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

