package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class DefineOrder {

    private static SinglyList<TemporalPlayer> diceTrowList;
    TemporalPlayer newPlayer;

    public static void initTemporal(){
        diceTrowList = new SinglyList<>();
    }

    public void addNewTemporal(TemporalPlayer newTemporal){
        diceTrowList.add(newTemporal);
    }

    public SinglyList<TemporalPlayer> recursiveAdd(int cantPlayers){

        while(cantPlayers>0){
            newPlayer = new TemporalPlayer(cantPlayers, Dice.roll());
            System.out.println("[ "+ newPlayer.getId() +" , "+ newPlayer.getDiceValue() +" ]");
            this.addNewTemporal(newPlayer);
            cantPlayers--;
        }
        return diceTrowList;

    }

    public SinglyList<TemporalPlayer> searchDraw(){

        SinglyNode<TemporalPlayer> temp = diceTrowList.getHead();
        SinglyNode<TemporalPlayer> search = temp;
        SinglyList<TemporalPlayer> drawPlayers = new SinglyList<>();

        while(temp!=null){
            while(search!=null){
                if(search!=temp){
                    if(search.getData().getDiceValue()==temp.getData().getDiceValue()){
                        drawPlayers.add(temp.getData());
                    }
                }
                search=search.getNext();
            }
            temp=temp.getNext();
            search=diceTrowList.getHead();
        }

        return drawPlayers;

    }

    public void newOrder(SinglyList<TemporalPlayer> oldList){

    }


}
