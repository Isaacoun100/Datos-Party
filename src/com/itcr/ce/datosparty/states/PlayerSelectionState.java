package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Leaderboard;
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

    public static Game getGame() {
        return game;
    }

    public PlayerSelectionState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        DefineOrder.initTemporal();

        uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player2Button,"player2Btn", () -> {
            setOrder.recursiveAdd(2);
            System.out.println("The order is:");
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        uiManager.addObject(new UIImageButton(35, 1, 7*2, 2*2, Assets.player3Button,"player3Btn", () -> {
            setOrder.recursiveAdd(3);
            System.out.println("The order is:");
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        uiManager.addObject(new UIImageButton(20, 30, 7*2, 2*2, Assets.player4Button,"player4Btn", () -> {
            setOrder.recursiveAdd(4);
            System.out.println("The order is:");
            setOrder.order();
            Leaderboard.initLeaderBoard();
            game = new Game(handler, SelectRoundState.getRound());
            game.start();
            State.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

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
