package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import javax.swing.JOptionPane;

/**
 *This class contains the list of players with no specific order and will create a new list with the players randomly
 *sorted, when the user gives the name input they will be added to temporalPlayerList in the order they give their input
 * then, will receive a random value from 1 to 6 and will be sorted according to it.
 */
public class DefineOrder {

    private static SinglyList<TemporalPlayer> temporalPlayerList;
    private static SinglyList<Integer> diceRollList;
    TemporalPlayer newPlayer;
    String playerName;

    /**
     * This method returns the temporalPlayerList
     * @return temporalPlayerList
     */
    public static SinglyList<TemporalPlayer> getTemporalPlayerList() {
        return temporalPlayerList;
    }

    /**
     * This method initializes temporalPlayerList as a new SinglyList, this fixes some nullPointerException errors
     * because the list is initialized when is needed, this list contains a list of TemporalPlayers that have only
     * their name and an integer that is the value that player got from the dice roll
     */
    public static void initTemporal(){
        temporalPlayerList = new SinglyList<>();
    }

    /**
     * This method initializes diceRollList as a new SinglyList, this fixes some nullPointerException
     * errors cause the list is initialized when is needed, this list contains the list of values the user has taken
     * to avoid duplicity and a possible draw.
     */
    public static void initDice(){
        diceRollList = new SinglyList<>();
    }

    /**
     * This method as a new TemporalPlayer to the TemporalPlayerList
     * @param newTemporal the one that is going to be added to the list
     */
    public void addNewTemporal(TemporalPlayer newTemporal){
        temporalPlayerList.add(newTemporal);
    }

    /**
     * This class receives an integer value that indicates the number of players that are going to play, then, it asks
     * in order what their names are, then it gives them a random value that we, later, are going to use to use to sort
     * the players, important to emphasize that the player's name can't be over 8 characters.
     * @param playerCount
     */
    public void recursiveAdd(int playerCount){
        this.initDice();

        while(playerCount>0){

            playerName = JOptionPane.showInputDialog(null, "Player "+ playerCount + " name");

            if(playerName==null || playerName.length() == 0){

                playerName="Player "+playerCount;

            }
            if(playerName.length()<=8){
                newPlayer = new TemporalPlayer(playerName, lockDice());
                this.addNewTemporal(newPlayer);
                playerCount--;
            }
        }

    }

    /**
     * It receives an integer that correspond to a random 1 to 6 value that will be assigned to a the player, then it
     * checks whether or not another player has that same value assigned to him, if another player already has the value
     * then it will return a true, else false
     * @param dice
     * @return True if the value is already assigned to a player else false
     */
    public boolean compareDice(int dice){

        SinglyNode<Integer> value = diceRollList.getHead();

        while (value != null) {

            if (dice == value.getData()) {
                return true;
            }
            value = (SinglyNode<Integer>) value.getNext();
        }

        return false;

    }

    /**
     * This method return an integer that hasn't been taken by another player by comparing a given random value, if this
     * value already exists then it will roll the dice again and so on untill it reaches a value that hasn't been taken.
     * @return Integer value that hasn't been taken.
     */
    public int lockDice(){

        int dice = Dice.roll(1, 6);

        while (compareDice(dice)){
            dice = Dice.roll(1, 6);
        }

        diceRollList.add(dice);
        return dice;

    }

    /**
     * Once all players have their name and corresponding dice value, this method will sort all players on the list by
     * their dice value and sort them using a bubble sort, once all players are sorted it will give the list to the
     * Round list, which is the list that we're going to use on the game itself.
     */
    public void order() {
        int count=0;
        SinglyNode<TemporalPlayer> before = temporalPlayerList.getHead();
        SinglyNode<TemporalPlayer> after;

        while (count< temporalPlayerList.getLength()){
            while(before!=null){
                after = (SinglyNode<TemporalPlayer>) before.getNext();
                while (after!=null){
                    if (before.getData().getDiceValue()<after.getData().getDiceValue()){
                        temporalPlayerList.swap(before,after);
                    }
                    after = (SinglyNode<TemporalPlayer>) after.getNext();
                }
                before = (SinglyNode<TemporalPlayer>) before.getNext();
            }
            count++;
        }

        Round.initRound();
        Round.translate(temporalPlayerList);

    }


}
