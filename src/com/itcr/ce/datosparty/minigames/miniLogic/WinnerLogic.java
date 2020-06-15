package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

/**
 * This class manages the winning logic, connects with the state and returns the players to the board
 */
public class WinnerLogic extends MiniGameLogic{

    /**
     * Constructor for the WinnerLogic class, this method receives the game from the mini games
     * @param game
     */
    public WinnerLogic(Game game) {
        super(game);
    }

    /**
     * Given a following player, the method will reward that player and resume the game to where it was
     * @param player
     */
    @Override
    public void winGame(Player player) {
        player.addCoins(10);
        game.resumeGame();
    }


}
