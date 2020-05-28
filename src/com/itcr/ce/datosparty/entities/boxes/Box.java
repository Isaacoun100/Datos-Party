package com.itcr.ce.datosparty.entities.boxes;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.entities.Entity;
import com.itcr.ce.datosparty.logic.Board;
import com.itcr.ce.datosparty.entities.Player;

import java.util.Scanner;

public abstract class Box extends Entity {

    private SinglyList<Player> playerList = new SinglyList<>();
    private boolean starBox = false;

    public Box(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void checkStar(Player player) {
        if (starBox) {
            buyStar(player);
        }
    }

    private void buyStar(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to buy a star?");
        // Choose yes/no
        while (scanner.hasNext()) {
            if (scanner.next().equals("Yes")) {
                System.out.println("Are you sure? It's 10 coins.");
                // Choose yes/no
                if (scanner.next().equals("Yes")) {
                    if (player.getCoins() >= 10) {
                        player.setCoins(-10);
                        player.setStars(1);
                        this.starBox = false;
                        Board.setStar();
                    } else {
                        System.out.println("You don't have enough money, though...");
                    }
                } else if (scanner.next().equals("No")) {
                    System.out.println("Alright...");
                } else {
                    System.out.println("What? I'm gonna ask again...");
                    buyStar(player);
                }
            } else if (scanner.next().equals("No")) {
                System.out.println("Alright...");
            } else {
                System.out.println("What? I'm gonna ask again...");
                buyStar(player);
            }
        }
            if (scanner.next().equals("Yes"))
        System.out.println("Are you sure? It's 10 coins.");
        //Choose yes/no
        if (player.getCoins() >= 10) {
            player.setCoins(-10);
            player.setStars(1);
            this.starBox = false;
            Board.setStar();
        } else {
            System.out.println("You don't have enough money, though...");
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

    public abstract void boxAction(Player player);
}
