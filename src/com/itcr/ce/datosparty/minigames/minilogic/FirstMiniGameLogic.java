package com.itcr.ce.datosparty.minigames.minilogic;

import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Game;

public class FirstMiniGameLogic extends MiniGameLogic{

    public FirstMiniGameLogic(Game game) {
        super(game);
    }

    @Override
    public void winGame(Player player) {
        player.addCoins(10);
        game.resumeGame();
    }


}
