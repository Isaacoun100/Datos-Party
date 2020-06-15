package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.logic.Dice;

/**
 * This is the class that manages the mini games that have been used and the ones that haven't
 */
public class UsedMinigames {

    private static SinglyList<Integer> Duplicates = new SinglyList<>();;

    /**
     * This method adds a new mini game to the mini games list
     * @param gameID
     */
    public void addGame(int  gameID) {
        Duplicates.add(gameID);
    }

    /**
     * This method recieves and integer that correspond to the value we want to check if it has been on the game
     * before, if it has, then it runs the same method but with another value untill it reaches the unused mini game
     * @param dice
     */
    public void check(int dice) {

        if (Duplicates.getLength() >= 6) {
            Duplicates.clear();
        }

        if (search(dice)){
            this.addGame(dice);
            Minigame.playMiniGame(dice);
        }
        else{
            this.check(Dice.roll(1, 6));
        }
    }

    /**
     * This method checks if the given int is already on the list
     * @param dice the mini game id we want to search
     * @return a boolean true if it already exists on the list, false if not
     */
    public boolean search(int dice){

        SinglyNode<Integer> temporal = Duplicates.getHead();


        while(temporal!=null){
            if(temporal.getData() == dice){
                return false;
            }
            else{
                temporal = (SinglyNode<Integer>) temporal.getNext();
            }
        }

        return true;


    }

}
