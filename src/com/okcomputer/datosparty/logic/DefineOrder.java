package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class DefineOrder {

    private static SinglyList<TemporalPlayer> diceThrowList;
    TemporalPlayer newPlayer;

    public static void initTemporal(){
        diceThrowList = new SinglyList<>();
    }

    public void addNewTemporal(TemporalPlayer newTemporal){
        diceThrowList.add(newTemporal);
    }

    public SinglyList<TemporalPlayer> recursiveAdd(int playerCount){

        while(playerCount>0){
            newPlayer = new TemporalPlayer("Player " + playerCount, Dice.roll());
            System.out.println("[ "+ newPlayer.getId() +" , "+ newPlayer.getDiceValue() +" ]");
            this.addNewTemporal(newPlayer);
            playerCount--;
        }
        return diceThrowList;

    }

    private static void swap(SinglyNode<Player> node1, SinglyNode<Player> node2) {
        Player data1 = node1.getData();
        Player data2 =  node2.getData();
        node1.setData(data2);
        node2.setData(data1);
    }

    public SinglyList<TemporalPlayer> searchDraw(){

        SinglyNode<TemporalPlayer> temp = diceThrowList.getHead();
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
            search= diceThrowList.getHead();
        }

        return drawPlayers;

    }

    public void newOrder(SinglyList<TemporalPlayer> oldList){

    }


}
