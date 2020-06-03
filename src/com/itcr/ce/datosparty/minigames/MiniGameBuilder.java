package com.itcr.ce.datosparty.minigames;

import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.dataStructures.LinkedList;
import com.itcr.ce.datosparty.logic.Game;
import com.itcr.ce.datosparty.minigames.states.*;
import com.itcr.ce.datosparty.states.State;

public class MiniGameBuilder {

    public static void build(LinkedList<State> gameList, int playerNumber, Handler handler, Game game){

        gameList.add(new FirstMinigameState(handler, playerNumber, game));
        gameList.add(new SecondMinigameState(handler, playerNumber, game));
        gameList.add(new ThirdMinigameState(handler, playerNumber, game));
        gameList.add(new FourthMinigameState(handler, playerNumber, game));
        gameList.add(new FifthMinigameState(handler, playerNumber, game));
        gameList.add(new SixthMinigameState(handler, playerNumber, game));
        gameList.add(new SeventhMinigameState(handler, playerNumber, game));
        gameList.add(new EighthMinigameState(handler, playerNumber, game));

    }
}
