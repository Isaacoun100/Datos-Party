package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.userInterface.UIImageButton;
import com.itcr.ce.datosparty.userInterface.UIManager;
import com.itcr.ce.datosparty.logic.DefineOrder;
import com.itcr.ce.datosparty.logic.Leaderboard;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;

import java.awt.*;

public class EndGameState extends State {

    private final UIManager uiManager;
    private final Font font;

    public EndGameState(Handler handler, Game game) {
        super(handler);
        uiManager = new UIManager(handler);
        font = new Font("Arial", Font.PLAIN,25);

        uiManager.addObject(new UIImageButton(18, 32, 7*2, 2*2, Assets.creditsButton,"creditsBtn",
                () -> {
                    game.setActive(false);
                    GameLoop.gameDependantStates.clear();
                    Round.getPlayerOrder().clear();
                    DefineOrder.getTemporalPlayerList().clear();
                    setState(GameLoop.creditsState); }));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        SinglyNode<Player> leaderboard = Leaderboard.getLeaderboard().getHead();
        uiManager.renderAll(g);
        g.setFont(font);
        int count=0;
        int y=20;

        while(count<Leaderboard.getLeaderboard().getLength()){
            g.drawString(count+1+" place belongs to "+leaderboard.getData().getName(),10,y);
            g.drawString("with  "+leaderboard.getData().getCoins()+" coins",10,y+100);
            g.drawString("and "+leaderboard.getData().getStars()+" stars",10,y+200);

            leaderboard= (SinglyNode<Player>) leaderboard.getNext();
            count++;
            y+=300;
        }
    }
}