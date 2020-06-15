package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.logic.Dice;

public class UsedMinigames {

    private static SinglyList<Integer> Duplicates = new SinglyList<>();;

    public void addGame(int  gameID) {
        Duplicates.add(gameID);
    }

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
