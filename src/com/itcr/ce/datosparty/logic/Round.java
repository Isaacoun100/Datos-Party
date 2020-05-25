package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.SinglyList;
import com.itcr.ce.datosparty.dataStructures.SinglyNode;

public class Round {

    private static SinglyList<Player> playerOrder;
    private static int numPlayers;
    private static int numRound = 0;

    public static void roundInit(){
        playerOrder = new SinglyList<>();
    }

    public static void definePlayers(SinglyList<String> playerNames) {
        if (1 < playerNames.getLength() && playerNames.getLength() <= 4) {
            numPlayers = playerNames.getLength();
            playerOrder = new SinglyList<>();
            for (int i = 0; i < 4; i++) {
                playerOrder.add(new Player(playerNames.getNodeByIndex(i).getData()));
                if (playerNames.getNodeByIndex(i).getNext() == null) {
                    break;
                }
            }
        }
    }

    public void translate(SinglyList<TemporalPlayer> sortedList){
        SinglyNode<TemporalPlayer> temp = sortedList.getHead();

        while(temp!=null){
            this.addPlayer(temp.getData().getId());
            temp=temp.getNext();
        }

    }

    public static void addPlayer(String name){
        playerOrder.add(new Player(name));
    }

    public static void returnPlayers(){
        playerOrder.print();
    }

    public static SinglyList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public static int getNumRound() {
        return numRound;
    }

    public static void setNumRound(int numRound) {
        Round.numRound = numRound;
    }
}