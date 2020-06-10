package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Leaderboard;
import com.itcr.ce.datosparty.userInterface.UIAnimatedImage;
import com.itcr.ce.datosparty.userInterface.UIImage;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.DefineOrder;

import java.awt.*;


public class PlayerSelectionState extends State {

    private static Game game;
    private final UIManager uiManager;
    DefineOrder setOrder = new DefineOrder();
    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;

    public static Game getGame() {
        return game;
    }

    public PlayerSelectionState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        DefineOrder.initTemporal();

        Animation twoPlayerAnim = new Animation(600, Assets.twoPlayerAnim);
        Animation threePlayerAnim = new Animation(400, Assets.threePlayerAnim);
        Animation fourPlayerAnim = new Animation(200,Assets.fourthPlayerAnim);

        uiManager.addObject(new UIImage((float)width/2-40,(float)height/2-30,10*8,2*8,Assets.playerSelectionTitle,"SelectPlayerTitle"));

        uiManager.addObject(new UIImageButton((float)width/2-44, (float)height/2+10, 7*4, 2*4, Assets.player2Button,"player2Btn", () -> {
            setOrder.recursiveAdd(2);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        uiManager.addObject(new UIImageButton((float)width/2-14, (float)height/2+10, 7*4, 2*4, Assets.player3Button,"player3Btn", () -> {
            setOrder.recursiveAdd(3);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        uiManager.addObject(new UIImageButton((float)width/2+16, (float)height/2+10, 7*4, 2*4, Assets.player4Button,"player4Btn", () -> {
            setOrder.recursiveAdd(4);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        uiManager.addObject(new UIAnimatedImage((float)width/2-38,(float)height/2-6,2*8,2*8,twoPlayerAnim,"twoPlayerAnim"));
        uiManager.addObject(new UIAnimatedImage((float)width/2-12,(float)height/2-6,3*8,2*8,threePlayerAnim,"threePlayerAnim"));
        uiManager.addObject(new UIAnimatedImage((float)width/2+14,(float)height/2-6,4*8,2*8,fourPlayerAnim,"fourPlayerAnim"));


    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.renderAll(g);
    }

}
