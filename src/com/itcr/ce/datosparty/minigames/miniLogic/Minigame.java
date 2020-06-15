package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.Dice;

public class Minigame {

    public static void newMinigame(){
        UsedMinigames usedMinigames = new UsedMinigames();
        usedMinigames.check(Dice.roll(1, 6));
    }

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
