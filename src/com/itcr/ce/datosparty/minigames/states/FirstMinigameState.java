package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.minigames.miniLogic.FirstMiniGameLogic;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FirstMinigameState extends State {
    private UIManager uiManager;
    int numPlayers;

    public FirstMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        uiManager = new UIManager(handler);
        FirstMiniGameLogic currentGame = new FirstMiniGameLogic(game);

        uiManager.addObject(new UIImageButton(1, 1, 7*2, 2*2, Assets.player1Button, "player1Btn", () -> {
            System.out.println("1");
            Player player1 = Round.getPlayerOrder().get(0).getData();
            currentGame.winGame(player1);
            GameLoop.setState(GameLoop.gameDependantStates.get(8).getData());
        }));

        if(numPlayers>=2){
            uiManager.addObject(new UIImageButton(30, 1, 7*2, 2*2, Assets.player2Button,"player2Btn", () -> {
                System.out.println("2");
                Player player2 = Round.getPlayerOrder().get(1).getData();
                currentGame.winGame(player2);
                GameLoop.setState(GameLoop.gameDependantStates.get(8).getData());
            }));
        }

        if(numPlayers>=3){
            uiManager.addObject(new UIImageButton(1, 30, 7*2, 2*2, Assets.player3Button,"player3Btn", () -> {
                System.out.println("3");
                Player player3 = Round.getPlayerOrder().get(2).getData();
                currentGame.winGame(player3);
                GameLoop.setState(GameLoop.gameDependantStates.get(8).getData());
            }));
        }

        if(numPlayers>=4){
            uiManager.addObject(new UIImageButton(30, 30, 7*2, 2*2, Assets.player4Button,"player4Btn", () -> {
                System.out.println("4");
                Player player4 = Round.getPlayerOrder().get(3).getData();
                currentGame.winGame(player4);
                GameLoop.setState(GameLoop.gameDependantStates.get(8).getData());
            }));
        }

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
