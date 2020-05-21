package com.okcomputer.datosparty.logic.boxes;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.entities.Entity;
import com.okcomputer.datosparty.logic.Board;
import com.okcomputer.datosparty.logic.Player;

public abstract class Box extends Entity {

    private SinglyList<Player> playerList = new SinglyList<>();
    private boolean starBox = false;


    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void buyStar(Player player) {
        if (starBox) {
            System.out.println("Would you like to buy a star?");
            // Choose yes/no
            System.out.println("Are you sure? It's 20 coins.");
            //Choose yes/no
            if (player.getCoins() >= 20) {
                player.setCoins(player.getCoins() - 20);
                player.setStars(player.getStars() + 1);
                this.starBox = false;
                Board.setStar();
            } else {
                System.out.println("You don't have enough money, though...");
            }
        }
    }

    public SinglyList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(SinglyList<Player> playerList) {
        this.playerList = playerList;
    }

    public boolean isStarBox() {
        return starBox;
    }

    public void setStarBox(boolean starBox) {
        this.starBox = starBox;
    }
}
