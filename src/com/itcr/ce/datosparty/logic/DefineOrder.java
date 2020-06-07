package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import javax.swing.JOptionPane;

public class DefineOrder {

    private static SinglyList<TemporalPlayer> TemporalPlayerList;
    private static SinglyList<Integer> diceThrowList;
    TemporalPlayer newPlayer;
    String playerName;

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

            playerName = JOptionPane.showInputDialog(null, "Player name");
            if(playerName==null || playerName==""){ playerName="Player "+playerCount;}
            newPlayer = new TemporalPlayer(playerName, lockDice());
            this.addNewTemporal(newPlayer);
            playerCount--;
        }

        this.displayPlayer(TemporalPlayerList);

    }

    public void displayPlayer(SinglyList<TemporalPlayer> displayList){

        SinglyNode<TemporalPlayer> temporal = displayList.getHead();

        while(temporal!=null){
            System.out.println("[ "+ temporal.getData().getId() +" , "+ temporal.getData().getDiceValue() +" ]");
            temporal = (SinglyNode<TemporalPlayer>) temporal.getNext();
        }

    }

    public boolean compareDice(int dice){

        SinglyNode<Integer> value = diceThrowList.getHead();

        while (value != null) {

            if (dice == value.getData()) {
                return true;
            }
            value = (SinglyNode<Integer>) value.getNext();
        }

        return false;

    }

    public int lockDice(){

        int dice = Dice.roll(1, 6);

        while (compareDice(dice)){
            dice = Dice.roll(1, 6);
            System.out.println("Why do they always send the poor");
        }

        diceThrowList.add(dice);
        return dice;

    }

    public void order() {
        int count=0;
        SinglyNode<TemporalPlayer> before = TemporalPlayerList.getHead();
        SinglyNode<TemporalPlayer> after;

        while (count< TemporalPlayerList.getLength()){
            while(before!=null){
                after = (SinglyNode<TemporalPlayer>) before.getNext();
                while (after!=null){
                    if (before.getData().getDiceValue()<after.getData().getDiceValue()){
                        TemporalPlayerList.swap(before,after);
                    }
                    after = (SinglyNode<TemporalPlayer>) after.getNext();
                }
                before = (SinglyNode<TemporalPlayer>) before.getNext();
            }
            count++;
        }

        this.displayPlayer(TemporalPlayerList);
        Round.initRound();
        Round.translate(TemporalPlayerList);

    }


}
