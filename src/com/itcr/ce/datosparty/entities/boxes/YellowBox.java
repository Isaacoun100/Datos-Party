package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.*;

import java.awt.*;

/**
 * Type of box that activates and event to a player standing on it
 */
public class YellowBox extends Box {

    EventLogic eventLogic = new EventLogic();

    public YellowBox(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void tick() {

    }

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
            game.setCurrentEvent(game.eventStack.pop());
            switch (game.getCurrentEvent()) {
                case DUEL -> eventLogic.duel(player, game);
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
