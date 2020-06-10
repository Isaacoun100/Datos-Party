package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.DefineOrder;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Leaderboard;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class EndGameState extends State {

    private final UIManager endGameUI;
    private final Font font;
    private final int width = GameLauncher.width/16;
    private final int height = GameLauncher.height/16;
    private final SinglyNode<Player> firstPlace;
    private final SinglyNode<Player> secondPlace;
    private  SinglyNode<Player> thirdPlace;
    private  SinglyNode<Player> fourthPlace;
    private final Game game;


    public EndGameState(Handler handler, Game game) {
        super(handler);
        this.game = game;
        endGameUI = new UIManager(handler);
        font = Assets.bitArtFont2;

        Animation star = new Animation(200, Assets.star);
        Animation coin = new Animation(200, Assets.coin);
        Animation firstPlaceAnim = new Animation(200, Assets.firstPlaceAnim);
        Animation fourthPlaceAnim = new Animation(200, Assets.fourthPlayerAnim);


        endGameUI.addObject(new UIImageButton(18, 32, 7*2, 2*2, Assets.creditsButton,"creditsBtn",
                () -> {
                    game.setActive(false);
                    GameLoop.gameDependantStates.clear();
                    Round.getPlayerOrder().clear();
                    DefineOrder.getTemporalPlayerList().clear();
                    setState(GameLoop.creditsState); }));

        endGameUI.addObject(new UIAnimatedImage((float)width/2+6,(float)height/2-22, 8,2*8,firstPlaceAnim,"firstPlace"));
        endGameUI.addObject(new UIAnimatedImage(0,3,4,4, star,"star1"));
        endGameUI.addObject(new UIAnimatedImage(0,6,4,4, coin,"coin1"));
        this.firstPlace = Leaderboard.getLeaderboard().getHead();
        endGameUI.addObject(new UIImage((float)width/2-14,(float)height/2-22, 8,2*8,Assets.secondPLaceImg,"secondPlace"));
        endGameUI.addObject(new UIAnimatedImage(0,3,4,4, star,"star2"));
        endGameUI.addObject(new UIAnimatedImage(0,6,4,4, coin,"coin2"));
        secondPlace = (SinglyNode<Player>) this.firstPlace.getNext();
        if (game.getNumberOfPlayers() >= 3) {
            endGameUI.addObject(new UIImage((float) width / 2 - 14, (float) height / 2 - 22, 8, 2 * 8, Assets.thirdPlaceImg, "thirdPlace"));
            endGameUI.addObject(new UIAnimatedImage(0, 15, 4, 4, star, "star3"));
            endGameUI.addObject(new UIAnimatedImage(0, 18, 4, 4, coin, "coin3"));
            thirdPlace = (SinglyNode<Player>) secondPlace.getNext();
        }
        if (game.getNumberOfPlayers() == 4) {
            endGameUI.addObject(new UIAnimatedImage((float)width/2+6,(float)height/2-22, 8,2*8,fourthPlaceAnim,"fourthPlace"));
            endGameUI.addObject(new UIAnimatedImage(10,3,4,4, star,"star4"));
            endGameUI.addObject(new UIAnimatedImage(10,6,4,4, coin,"coin4"));
            fourthPlace = (SinglyNode<Player>) thirdPlace.getNext();
        }
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(endGameUI);
        endGameUI.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        int localWidth = width*16;
        int localHeight = height*16;
        g.drawString(firstPlace.getData().getName() + "is the Winner!", localWidth/2, localHeight/2);
        endGameUI.renderById(g, "coin1");
        endGameUI.renderById(g, "star1");
        g.drawString("X" + firstPlace.getData().getCoins(), localWidth/2, localHeight/2 + 100);
        g.drawString("X" + firstPlace.getData().getStars(), localWidth/2, localHeight/2 + 200);
        g.drawString("2nd place belongs to " + secondPlace.getData().getName(), 10, localHeight/2);
        endGameUI.renderById(g, "coin2");
        endGameUI.renderById(g, "star2");
        g.drawString("X" + secondPlace.getData().getCoins(), localWidth/2, localHeight/2);
        g.drawString("X" + secondPlace.getData().getStars(), localWidth/2, localHeight/2);
        if (game.getNumberOfPlayers() >= 3) {
            endGameUI.renderById(g, "coin3");
            endGameUI.renderById(g, "star3");
            g.drawString("X" + thirdPlace.getData().getCoins(), localWidth/2, localHeight/2);
            g.drawString("X" + thirdPlace.getData().getStars(), localWidth/2, localHeight/2);
        }
        if (game.getNumberOfPlayers() == 4) {
            g.drawString("4th place belongs to " + fourthPlace.getData().getName(),localHeight/2, localHeight/2);
            endGameUI.renderById(g, "coin4");
            endGameUI.renderById(g, "star4");
            g.drawString("X" + fourthPlace.getData().getCoins(), localWidth/2, localHeight/2);
            g.drawString("X" + fourthPlace.getData().getStars(), localWidth/2, localHeight/2);
        }
    }
}