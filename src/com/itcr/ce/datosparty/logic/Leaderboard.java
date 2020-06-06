package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
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
            temporal=temporal.getNext();
        }

    }

    public static SinglyList<Player> getLeaderboard(){
        updateLeaderBoard();
        setLeaderboard();
        showLeaderboard();
        return leaderboard;
    }

    private static void showLeaderboard(){
        SinglyNode<Player> temporal = leaderboard.getHead();
        int i =1;

        while(temporal!=null){

            System.out.println(i+ " place is to "+temporal.getData().getName());
            System.out.println( " with "+temporal.getData().getStars()+ " stars and "+
                                        temporal.getData().getCoins()+" coins");

            temporal=temporal.getNext();
            i++;
        }

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
                search=search.getNext();
            }
            temporal = temporal.getNext();
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
            temporal=temporal.getNext();
        }
        return true;
    }

}
