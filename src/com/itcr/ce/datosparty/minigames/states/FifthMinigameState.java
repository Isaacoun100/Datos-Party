package com.itcr.ce.datosparty.minigames.states;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.states.State;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;

import java.awt.*;

public class FifthMinigameState extends State {
    private final UIManager miniRPGUI;
    private final Player player1;
    private final Player player2;
    private Player player3;
    private Player player4;
    private CircularList<Player> currentPlayers;

    public FifthMinigameState(Handler handler, int numPlayers, Game game) {
        super(handler);

        miniRPGUI = new UIManager(handler);

        player1 = game.getPlayerList().get(0).getData();
        player2 = game.getPlayerList().get(1).getData();

        player1.setHp(20);
        player2.setHp(20);

        currentPlayers.add(player1);
        currentPlayers.add(player2);


        miniRPGUI.addObject(new UIImageButton(1, 1, 1, 2, Assets.stealCoins1,"player1Btn", () -> System.out.println("1")));
        miniRPGUI.addObject(new UIImageButton(30, 1, 1, 2*2, Assets.stealCoins2,"player2Btn", () -> System.out.println("2")));

        if(numPlayers>=3){
            miniRPGUI.addObject(new UIImageButton(1, 30, 1, 2*2, Assets.stealCoins3,"player3Btn", () -> System.out.println("3")));
            player3 = game.getPlayerList().get(2).getData();
            player3.setHp(20);
            currentPlayers.add(player3);

        }

        if(numPlayers == 4){
            miniRPGUI.addObject(new UIImageButton(30, 30, 1, 2*2, Assets.stealCoins4,"player4Btn", () -> System.out.println("4")));
            player4 = game.getPlayerList().get(3).getData();
            player4.setHp(20);
            currentPlayers.add(player4);
        }

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(miniRPGUI);
        miniRPGUI.tick();
    }

    @Override
    public void render(Graphics g) {
        miniRPGUI.renderAll(g);
    }

}
