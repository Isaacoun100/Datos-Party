package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.logic.Event;
import com.itcr.ce.datosparty.logic.EventLogic;
import com.itcr.ce.datosparty.logic.Game;

import java.awt.*;

/**
 * Type of box that activates and event to a player standing on it
 */
public class YellowBox extends Box {

    EventLogic eventLogic = new EventLogic();

    /**
     *
     * This box triggers events, you can see the abstract class for a more detailed explanation of each parameter
     * @param x position
     * @param y position
     * @param width size
     * @param height size
     */
    public YellowBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * render method for boxes, where we provide the asset associated with it.
     * @param g java.awt graphics object.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.yellowBox,(int) x,(int) y, width, height, null);
    }

    /**
     * Chooses which event will the player experience.
     * @param player Current Player standing on box when its turn ends
     * @param game Object with all the data contained on current game
     */
    @Override
    public void boxAction(Player player, Game game) {
        if(player.getCurrentTurn()){
            Event currentEvent = game.eventStack.pop();
            game.setCurrentEvent(currentEvent);
            switch (currentEvent) {
                case DUEL -> eventLogic.randomDuel(player, game);
                case STEAL_COINS -> eventLogic.pauseEvent(game);
                case GIFT_COINS -> eventLogic.giftCoins(player, game);
                case LOSE_STAR -> eventLogic.loseStar(player, game);
                case WIN_2_STARS -> eventLogic.winTwoStars(player, game);
                case WIN_5_STARS -> eventLogic.winFiveStars(player, game);
                case STEAL_STAR -> eventLogic.stealStar(player, game);
                case TELEPORT -> eventLogic.teleport(player, game);
                case SWAP_PLAYERS -> eventLogic.swapPlayers(player, game);
                default -> System.out.println("Couldn't find event");
            }
        }
    }
}
