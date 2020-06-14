package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;

/**
 * This class contains a list that has all the players sorted by their stars and coins
 */
public class Leaderboard {
    private static SinglyList<Player> leaderboard;

    /**
     * This method initializes LeaderBoard as a new SinglyList, this fixes some nullPointerException errors
     * because the list is initialized when is needed
     */
    public static void initLeaderBoard() {
        leaderboard = new SinglyList<>();
    }

    /**
     * This method clears the board every time is invoked, the it put all the players in the list acording to how they
     * were sorted in the game
     */
    private static void updateLeaderBoard(){
        SinglyNode<Player> temporal = Round.getPlayerOrder().getHead();
        leaderboard.clear();

        while(temporal!=null){
            leaderboard.add(temporal.getData());
            temporal = (SinglyNode<Player>) temporal.getNext();
        }
    }

    /**
     * When called, it will update the leaderboard, then sort it, and returns the leaderboard list.
     * @return SinglyList<Player> with the sorted players
     */
    public static SinglyList<Player> getLeaderboard(){
        updateLeaderBoard();
        setLeaderboard();
        return leaderboard;
    }

    /**
     * This method receives the players and then sort them according to their stars, if there's a draw the deciding
     * factor will be the coins.
     */
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

}
