package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Leaderboard {
    private static SinglyList<Player> leaderboard;

    public static void initLeaderBoard() {
        leaderboard = new SinglyList<>();
    }

    public static void updateLeaderBoard(){
        SinglyList<Player> unsortedList = Round.getPlayerOrder();
        SinglyNode<Player> temporal = unsortedList.getHead();

        while(temporal!=null){
            leaderboard.add(temporal.getData());
            temporal=temporal.getNext();
        }

    }

    public static SinglyList<Player> getLeaderboard(){
        setLeaderboard();
        return leaderboard;
    }

    private static void setLeaderboard(){

    }

    private static boolean isEmpty(SinglyList<Player> unsortedList){
        SinglyNode<Player> temporal;
        temporal = unsortedList.getHead();

        while(temporal!=null){
            if (temporal.getData().getStars()!=0){
                return false;
            }
            else if(temporal.getData().getCoins()!=0){
                return false;
            }
            temporal=temporal.getNext();
        }
        return true;
    }

}
