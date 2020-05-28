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

        switch (gameID){
            case 1:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 2:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 3:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 4:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 5:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 6:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 7:
                GameLoop.setState(GameLoop.creditsState);
                break;
            case 8:
                GameLoop.setState(GameLoop.creditsState);
                break;
            default:
                System.out.println("A non existing minigame was invoke with the code  : "+gameID);
        }
    }

}
