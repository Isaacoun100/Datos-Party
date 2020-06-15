package com.itcr.ce.datosparty.minigames.miniGameLogic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.logic.Dice;

/**
 * This class contains a list with all of the positions for the cards in the memory mini game.
 */
public class MemoryList {

    private static SinglyList<int[]> coords;

    /**
     * Returns the head of the SinglyList that we created
     * @return the head of the list
     */
    public static SinglyNode<int[]> getHead(){
        return coords.getHead();
    }

    /**
     * Giving the index of the node we need it returns the data in the node
     * @param node index value of the node
     * @return the data from the node
     */
    public static int[] getCoords(int node){
        return coords.get(node).getData();
    }

    /**
     * This method initializes coords as a new SinglyList, this fixes some nullPointerException errors
     * because the list is initialized when is needed, this list contains a list of int that contains all
     * of the x and y positions of the memory game.
     */
    public static void initCoords() {
        coords = new SinglyList<>();
    }

    /**
     * Takes all of the values of the already defined list and shuffles them to imitate the memory game better.
     */
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
