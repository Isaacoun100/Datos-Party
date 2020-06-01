package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;

public class DefineOrder {

    private static SinglyList<TemporalPlayer> TemporalPlayerList;
    private static SinglyList<Integer> diceThrowList;
    TemporalPlayer newPlayer;

    public static void initTemporal(){
        TemporalPlayerList = new SinglyList<>();
    }

    public static void initDice(){
        diceThrowList = new SinglyList<>();
    }

    public void addNewTemporal(TemporalPlayer newTemporal){
        TemporalPlayerList.add(newTemporal);
    }

    public void recursiveAdd(int playerCount){
        this.initDice();

        while(playerCount>0){
            newPlayer = new TemporalPlayer("Player " + playerCount, lockDice());
            this.addNewTemporal(newPlayer);
            playerCount--;
        }

        this.displayPlayer(TemporalPlayerList);

    }

    public void displayPlayer(SinglyList<TemporalPlayer> displayList){

        SinglyNode<TemporalPlayer> temporal = displayList.getHead();

        while(temporal!=null){
            System.out.println("[ "+ temporal.getData().getId() +" , "+ temporal.getData().getDiceValue() +" ]");
            temporal=temporal.getNext();
        }

    }

    public boolean compareDice(int dice){

        SinglyNode<Integer> value = diceThrowList.getHead();

        while (value != null) {

            if (dice == value.getData()) {
                return true;
            }
            value = value.getNext();
        }

        return false;

    }

    public int lockDice(){

        int dice = Dice.roll(6,1);

        while (compareDice(dice)){
            dice = Dice.roll(6,1);
            System.out.println("Why do they always send the poor");
        }

        diceThrowList.add(dice);
        return dice;

    }

    public void order() {
        int count=0;
        SinglyNode<TemporalPlayer> before = TemporalPlayerList.getHead();
        SinglyNode<TemporalPlayer> after = before;

        while (count< TemporalPlayerList.getLength()){
            while(before!=null){
                after=before.getNext();
                while (after!=null){
                    if (before.getData().getDiceValue()<after.getData().getDiceValue()){
                        this.swap(before,after);
                    }
                    after=after.getNext();
                }
                before=before.getNext();
            }
            count++;
        }

        this.displayPlayer(TemporalPlayerList);
        Round.initRound();
        Round.translate(TemporalPlayerList);

    }

    private void swap(SinglyNode<TemporalPlayer> node1, SinglyNode<TemporalPlayer> node2) {
        TemporalPlayer data1 = node1.getData();
        TemporalPlayer data2 =  node2.getData();
        node1.setData(data2);
        node2.setData(data1);
    }


}
