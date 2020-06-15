package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.gfx.Animation;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Leaderboard;
import com.itcr.ce.datosparty.userInterface.*;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.DefineOrder;

import java.awt.*;

/**
 * This class is the state in which the players select how many players want to play
 */
public class PlayerSelectionState extends State {

    private static Game game;
    private final UIManager PlayerSelectionUI;
    DefineOrder setOrder = new DefineOrder();
    private int width = GameLauncher.width/16;
    private int height = GameLauncher.height/16;

    /**
     * This method returns the game if it is needed for modifications.
     * @return game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * Bringa to the user 3 buttons, depending on how many players they want to play with, and ask them for the name
     * each player has to identify them in the board
     * @param handler
     */
    public PlayerSelectionState(Handler handler) {
        super(handler);

        PlayerSelectionUI = new UIManager(handler);
        DefineOrder.initTemporal();

        PlayerSelectionUI.addObject(new UIBackground(Assets.selectionWallpaper, "wp"));

        Animation twoPlayerAnim = new Animation(600, Assets.twoPlayerAnim);
        Animation threePlayerAnim = new Animation(400, Assets.threePlayerAnim);
        Animation fourPlayerAnim = new Animation(200,Assets.fourPlayerAnim);

        PlayerSelectionUI.addObject(new UIImage((float)width/2-40,(float)height/2-30,10*8,2*8,Assets.playerSelectionTitle,"SelectPlayerTitle"));

        PlayerSelectionUI.addObject(new UIImageButton((float)width/2-44, (float)height/2+10, 7*4, 2*4, Assets.player2Button,"player2Btn", () -> {
            setOrder.recursiveAdd(2);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        PlayerSelectionUI.addObject(new UIImageButton((float)width/2-14, (float)height/2+10, 7*4, 2*4, Assets.player3Button,"player3Btn", () -> {
            setOrder.recursiveAdd(3);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        PlayerSelectionUI.addObject(new UIImageButton((float)width/2+16, (float)height/2+10, 7*4, 2*4, Assets.player4Button,"player4Btn", () -> {
            setOrder.recursiveAdd(4);
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        PlayerSelectionUI.addObject(new UIAnimatedImage((float)width/2-38,(float)height/2-6,2*8,2*8,twoPlayerAnim,"twoPlayerAnim"));
        PlayerSelectionUI.addObject(new UIAnimatedImage((float)width/2-12,(float)height/2-6,3*8,2*8,threePlayerAnim,"threePlayerAnim"));
        PlayerSelectionUI.addObject(new UIAnimatedImage((float)width/2+14,(float)height/2-6,4*8,2*8,fourPlayerAnim,"fourPlayerAnim"));


    }

    /**
     * The tick initializes once the state is called, the main difference with the constructor is that the constructor
     * runs when the program itself starts but the tick each time the state is called
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(PlayerSelectionUI);
        PlayerSelectionUI.tick();
    }

    /**
     * Render method runs constantly in a loop once the game is started and finishes the moment the game also finishes,
     * this method also is the method in charge of rendering the graphics
     * @param g graphics parameter passed to GameLoop
     */
    @Override
    public void render(Graphics g) {
        PlayerSelectionUI.renderAll(g);
    }

}
