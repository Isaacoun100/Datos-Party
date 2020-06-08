package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

public class Leaderboard {
    private static SinglyList<Player> leaderboard;

    public static void initLeaderBoard() {
        leaderboard = new SinglyList<>();
    }

    private static void updateLeaderBoard(){
        SinglyNode<Player> temporal = Round.getPlayerOrder().getHead();
        leaderboard.clear();

        while(temporal!=null){
            leaderboard.add(temporal.getData());
            temporal = (SinglyNode<Player>) temporal.getNext();
        }
    }

    public static SinglyList<Player> getLeaderboard(){
        updateLeaderBoard();
        setLeaderboard();
        return leaderboard;
    }

    private static void setLeaderboard(){
        SinglyNode<Player> temporal, search;
        temporal = leaderboard.getHead();

        while (temporal!=null){
            search=temporal;
            while(search!=null){

                if(temporal.getData().getStars()<search.getData().getStars()){
                    leaderboard.swap(temporal,search);
                }
                else if(temporal.getData().getStars()==search.getData().getStars()){
                    if(temporal.getData().getCoins()<search.getData().getCoins()){
                        leaderboard.swap(temporal,search);
                    }
                }
                search=(SinglyNode<Player>) search.getNext();
            }
            temporal = (SinglyNode<Player>) temporal.getNext();
        }

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
            temporal=(SinglyNode<Player>) temporal.getNext();
        }
        return true;
    }

}
