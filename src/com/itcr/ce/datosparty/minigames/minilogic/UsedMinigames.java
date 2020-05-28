package com.itcr.ce.datosparty.minigames.minilogic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;
import com.itcr.ce.datosparty.logic.Dice;
import com.itcr.ce.datosparty.minigames.minilogic.Minigame;

public class UsedMinigames {

    private static SinglyList<Integer> Duplicates;

    public void addGame(int  gameID) {
        this.Duplicates.add(gameID);
    }

    public void initDuplicates(){
        Duplicates = new SinglyList<Integer>();
    }

    public void check(int dice) {

        if (search(dice)){
            if (Duplicates.getLength() >= 8) {
                this.Duplicates.clear();

            }

            else{
                System.out.println("Playing minigame #"+dice);
            }
            this.addGame(dice);
            Minigame.playMinigame(dice);
        }

        else{
            this.check(Dice.roll(8,1));
        }
    }

    public boolean search(int dice){

        SinglyNode<Integer> temporal = Duplicates.getHead();


        while(temporal!=null){
            if(temporal.getData().intValue()==dice){
                return false;
            }
            else{
                temporal=temporal.getNext();
            }
        }

        return true;


    }

}
