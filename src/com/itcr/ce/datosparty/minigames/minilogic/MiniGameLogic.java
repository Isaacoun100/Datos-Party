package com.itcr.ce.datosparty.minigames.minilogic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

public abstract class MiniGameLogic {

    protected SinglyList<Player> playerList;
    protected Game game;

    public MiniGameLogic(Game game){
        this.game = game;
        this.playerList = game.getPlayerList();
    }

    public abstract void winGame(Player player);
}
