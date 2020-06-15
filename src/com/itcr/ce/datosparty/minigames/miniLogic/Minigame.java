package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.Dice;

/**
 * This class handles the order in which the mini games appears to the user so that in just 6 rounds the player
 * can see all mini games.
 */
public class Minigame {

    /**
     * This method is called in the moment you need a mini game, it will initialize the UsedMiniGames to check what
     * mini games are available, then randomize between them and start the unseen state
     */
    public static void newMinigame(){
        UsedMinigames usedMinigames = new UsedMinigames();
        usedMinigames.check(Dice.roll(1, 6));
    }

    /**
     * Given a value from 1 to 6 it will call the state that was assigned to that number, if the number is not in the
     * list it will display a print message
     * @param gameID
     */
    public static void playMiniGame(int gameID){

        switch (gameID) {
            case 1 -> GameLoop.setState(GameLoop.gameDependantStates.get(1).getData());
            case 2 -> GameLoop.setState(GameLoop.gameDependantStates.get(2).getData());
            case 3 -> GameLoop.setState(GameLoop.gameDependantStates.get(3).getData());
            case 4 -> GameLoop.setState(GameLoop.gameDependantStates.get(4).getData());
            case 5 -> GameLoop.setState(GameLoop.gameDependantStates.get(5).getData());
            case 6 -> GameLoop.setState(GameLoop.gameDependantStates.get(7).getData());
            default -> System.out.println("A non existing mini game was invoke with the code  : " + gameID);
        }
    }



}
