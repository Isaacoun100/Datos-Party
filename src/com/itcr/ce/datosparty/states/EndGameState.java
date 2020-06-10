package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
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
    private final Game game;


    public EndGameState(Handler handler, Game game) {
        super(handler);
        this.game = game;
        endGameUI = new UIManager(handler);
        font = Assets.upHeavett.deriveFont(Font.PLAIN,40);

        Animation star = new Animation(200, Assets.star);
        Animation coin = new Animation(200, Assets.coin);
        Animation firstPlaceAnim = new Animation(200, Assets.firstPlaceAnim);
        Animation fourthPlaceAnim = new Animation(200, Assets.lastPlaceAnim);
        Animation congratsMsg = new Animation(800, Assets.congratulationsTitle);


        endGameUI.addObject(new UIImageButton((float)width/2-45, (float)height/2+18, 7*2, 2*2, Assets.creditsButton,"creditsBtn",
                () -> {
                    game.setActive(false);
                    GameLoop.gameDependantStates.clear();
                    Round.getPlayerOrder().clear();
                    DefineOrder.getTemporalPlayerList().clear();
                    setState(GameLoop.creditsState); }));

        //General UI objects
        endGameUI.addObject(new UIImage((float)width/2-18,(float)height/2-2,4*6,3*6,Assets.endGamePodium2,"podium2"));
        endGameUI.addObject(new UIImage((float)width/2-18,(float)height/2-2,6*6,3*6,Assets.endGamePodium3,"podium3"));
        endGameUI.addObject(new UIAnimatedImage((float)width/2-33,(float)height/2-30,17*4,2*4,congratsMsg,"congratsMsg"));
        //First Place
        endGameUI.addObject(new UIAnimatedImage((float)width/2-7,(float)height/2-15, 8*2,8*2,firstPlaceAnim,"firstPlace"));
        endGameUI.addObject(new UIAnimatedImage((float)width/2-4,(float)height/2+1,4,4, star,"star1"));
        endGameUI.addObject(new UIAnimatedImage((float)width/2-4,(float)height/2+4,4,4, coin,"coin1"));
        //Second Place
        endGameUI.addObject(new UIImage((float)width/2-13,(float)height/2-8, 8,2*8,Assets.secondPLaceImg,"secondPlace"));
        endGameUI.addObject(new UIAnimatedImage((float)width/2-14,(float)height/2+5,4,4, star,"star2"));
        endGameUI.addObject(new UIAnimatedImage((float)width/2-14,(float)height/2+8,4,4, coin,"coin2"));
        //Third Place
        if (game.getNumberOfPlayers() >= 3) {
            endGameUI.addObject(new UIImage((float) width / 2+7, (float)height/2-5, 8, 2 * 8, Assets.thirdPlaceImg, "thirdPlace"));
            endGameUI.addObject(new UIAnimatedImage((float)width/2+6, (float)height/2+9, 4, 4, star, "star3"));
            endGameUI.addObject(new UIAnimatedImage((float)width/2+6, (float)height/2+12, 4, 4, coin, "coin3"));
        }
        //Fourth Place
        if (game.getNumberOfPlayers() == 4) {
            endGameUI.addObject(new UIAnimatedImage((float)width/2+12,(float)height/2+6, 2*8,2*8,fourthPlaceAnim,"fourthPlace"));
            endGameUI.addObject(new UIAnimatedImage((float)width/2+25,(float)height/2+12,4,4, star,"star4"));
            endGameUI.addObject(new UIAnimatedImage((float)width/2+25,(float)height/2+15,4,4, coin,"coin4"));
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
        endGameUI.renderById(g,"congratsMsg");
        endGameUI.renderById(g,"creditsBtn");
        if(game.getNumberOfPlayers()==2){
            endGameUI.renderById(g,"podium2");
        }else if (game.getNumberOfPlayers()>=3){
            endGameUI.renderById(g,"podium3");
        }

        //FirstPlace
        g.drawString(Leaderboard.getLeaderboard().get(0).getData().getName() + " is the Winner!", localWidth/2-15*16, localHeight/2-15*16);
        endGameUI.renderById(g, "coin1");
        endGameUI.renderById(g, "star1");
        endGameUI.renderById(g,"firstPlace");
        g.drawString("X" + Leaderboard.getLeaderboard().get(0).getData().getStars(), localWidth/2, localHeight/2 + 4*16);
        g.drawString("X" + Leaderboard.getLeaderboard().get(0).getData().getCoins(), localWidth/2, localHeight/2 + 7*16);

        //SecondPlace
        g.drawString("2nd place belongs to " + Leaderboard.getLeaderboard().get(1).getData().getName(), localWidth/2-45*16, localHeight/2-6*16);
        endGameUI.renderById(g, "coin2");
        endGameUI.renderById(g, "star2");
        endGameUI.renderById(g,"secondPlace");
        g.drawString("X" + Leaderboard.getLeaderboard().get(1).getData().getStars(), localWidth/2+16*-10, localHeight/2+8*16);
        g.drawString("X" + Leaderboard.getLeaderboard().get(1).getData().getCoins(), localWidth/2+16*-10, localHeight/2+11*16);

        if (game.getNumberOfPlayers() >= 3) {
            //ThirdPlace
            g.drawString("3rd place belongs to " + Leaderboard.getLeaderboard().get(2).getData().getName(), localWidth/2+7*16, localHeight/2-4*16);
            endGameUI.renderById(g, "coin3");
            endGameUI.renderById(g, "star3");
            endGameUI.renderById(g, "thirdPlace");
            g.drawString("X" + Leaderboard.getLeaderboard().get(2).getData().getStars(), localWidth/2+10*16, localHeight/2+12*16);
            g.drawString("X" + Leaderboard.getLeaderboard().get(2).getData().getCoins(), localWidth/2+10*16, localHeight/2+15*16);
        }
        if (game.getNumberOfPlayers() == 4) {
            //FourthPlace
            g.drawString("4th place belongs to " + Leaderboard.getLeaderboard().get(3).getData().getName(),localHeight/2+16*16, localHeight/2+22*16);
            endGameUI.renderById(g, "coin4");
            endGameUI.renderById(g, "star4");
            endGameUI.renderById(g,"fourthPlace");
            g.drawString("X" + Leaderboard.getLeaderboard().get(3).getData().getStars(), localWidth/2+29*16, localHeight/2+15*16);
            g.drawString("X" + Leaderboard.getLeaderboard().get(3).getData().getCoins(), localWidth/2+29*16, localHeight/2+18*16);
        }
    }
}