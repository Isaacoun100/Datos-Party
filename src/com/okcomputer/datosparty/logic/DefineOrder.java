package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;
import com.okcomputer.datosparty.dataStructures.SinglyNode;

public class DefineOrder {

    private static SinglyList<TemporalPlayer> diceTrowList;

    TemporalPlayer newPlayer;

    public void addNewTemporal(TemporalPlayer newTemporal){
        diceTrowList.add(newTemporal);
    }

    public SinglyList<TemporalPlayer> recursiveAdd(int cantPlayers){
        int count=0;

        while(count>=cantPlayers){
            newPlayer = new TemporalPlayer(count);
            this.addNewTemporal(newPlayer);
            count++;
        }

        return diceTrowList;

    }

    public SinglyList<TemporalPlayer> searchDraw(){

        SinglyNode<TemporalPlayer> temp = diceTrowList.getHead();
        SinglyNode<TemporalPlayer> search = temp;
        SinglyList<TemporalPlayer> drawPlayers = null;

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
