package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Round {

    private static SinglyList<Player> playerOrder;
    private static int numRound = 0;

    public static void initRound() {
        playerOrder = new SinglyList<>();
    }

    public void translate(SinglyList<TemporalPlayer> sortedList){
    public static void translate(SinglyList<TemporalPlayer> sortedList){
        SinglyNode<TemporalPlayer> temp = sortedList.getHead();

        while(temp!=null){
            addPlayer(temp.getData().getId());
            temp=temp.getNext();
        }

        showList();

    }

    public static void showList(){

        SinglyNode<Player> showPlayer = playerOrder.getHead();

        while (showPlayer!=null){
            System.out.println(showPlayer.getData().getName());
            showPlayer=showPlayer.getNext();
        }

    }

    public static void addPlayer(String name){
        playerOrder.add(new Player(name,0,0));
    }

    public static SinglyList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public static int getNumRound() {
        return numRound;
    }

    public static void setNumRound(int numRound) {
        Round.numRound = numRound;
    }
}
