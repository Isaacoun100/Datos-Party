package com.itcr.ce.datosparty.minigames;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.lists.LinkedList;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.states.*;
import com.itcr.ce.datosparty.states.State;

public class MiniGameBuilder {

    /**
     * This method initiates the mini game states, and adds them to the list of the main loop
     * @param gameList list where the mini games will be added
     * @param playerNumber number of players
     * @param handler handler obj
     * @param game main game obj
     */
    public static void build(LinkedList<State> gameList, int playerNumber, Handler handler, Game game){

        gameList.add(new DuelMiniGame(handler, playerNumber, game));
        gameList.add(new SpaceRunState(handler, playerNumber, game));
        gameList.add(new BullseyeState(handler, playerNumber, game));
        gameList.add(new ShootingStarState(handler, playerNumber, game));
        gameList.add(new BustedPaintballState(handler, playerNumber, game));
        gameList.add(new ClickerArtistState(handler, playerNumber, game));
        gameList.add(new SeventhMinigameState(handler, playerNumber, game));
        gameList.add(new MemoryState(handler, playerNumber, game));

    }
}
