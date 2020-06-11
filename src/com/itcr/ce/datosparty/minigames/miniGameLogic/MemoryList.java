package com.itcr.ce.datosparty.minigames.miniGameLogic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.logic.Dice;

public class MemoryList {

    private static SinglyList<int[]> coords;

    public static SinglyNode<int[]> getHead(){
        return coords.getHead();
    }

    public static int[] getCoords(int node){
        return coords.get(node).getData();
    }

    public static void initCoords() {
        coords = new SinglyList<>();
    }

    public static void shuffle(){
        coords.clear();
        int count;
        count=1;

        for(int x=6;x<=78;x+=18){
            coords.add(new int[]{x,10});
            coords.add(new int[]{x,39});
        }

        while (count<=9){
            int randval = Dice.roll(0,9);

            while(randval==count){
                randval = Dice.roll(0,9);
            }

            coords.swap(coords.get(count),coords.get(randval));
            count++;
        }
    }
}
