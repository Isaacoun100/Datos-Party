package com.itcr.ce.datosparty.minigames.minilogic;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.logic.Dice;

public class Minigame {

    public static void newMinigame(){
        UsedMinigames UMG = new UsedMinigames();
        UMG.initDuplicates();
        UMG.check(Dice.roll(1, 8));
    }

    public static void playMinigame(int gameID){

        switch (gameID) {
            case 1 -> GameLoop.setState(GameLoop.miniGameStates.get(0).getData());
            case 2 -> GameLoop.setState(GameLoop.miniGameStates.get(1).getData());
            case 3 -> GameLoop.setState(GameLoop.miniGameStates.get(2).getData());
            case 4 -> GameLoop.setState(GameLoop.miniGameStates.get(3).getData());
            case 5 -> GameLoop.setState(GameLoop.miniGameStates.get(4).getData());
            case 6 -> GameLoop.setState(GameLoop.miniGameStates.get(5).getData());
            case 7 -> GameLoop.setState(GameLoop.miniGameStates.get(6).getData());
            case 8 -> GameLoop.setState(GameLoop.miniGameStates.get(7).getData());
            default -> System.out.println("A non existing minigame was invoke with the code  : " + gameID);
        }
    }

}
