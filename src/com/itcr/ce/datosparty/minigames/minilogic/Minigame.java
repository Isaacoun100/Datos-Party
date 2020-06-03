package com.itcr.ce.datosparty.minigames.minilogic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.Dice;

public class Minigame {

    public static void newMinigame(){
        UsedMinigames UMG = new UsedMinigames();
        UMG.initDuplicates();
        UMG.check(Dice.roll(8,1));
    }

    public static void playMinigame(int gameID){

        switch (gameID) {
            case 1 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(0).getData());
            case 2 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(1).getData());
            case 3 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(2).getData());
            case 4 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(3).getData());
            case 5 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(4).getData());
            case 6 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(5).getData());
            case 7 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(6).getData());
            case 8 -> GameLoop.setState(GameLoop.miniGameStates.getNodeByIndex(7).getData());
            default -> System.out.println("A non existing minigame was invoke with the code  : " + gameID);
        }
    }

}
