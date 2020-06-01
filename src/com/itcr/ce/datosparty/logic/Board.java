package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.CircularDoublyList;
import com.itcr.ce.datosparty.entities.boxes.*;

public class Board {

    public final CircularDoublyList<Box> phaseA = new CircularDoublyList<>();

    public CircularDoublyList<Box> getPhaseA() {
        return phaseA;
    }

    public Board() {

        boardBuilder();
    }

    public void boardBuilder(){

        int xPos = 10;
        int yPos = 10;
        int boxColor;

        for(int i = 0; i < 40; i++){
            boxColor = Dice.roll(1, 4);
            System.out.println(boxColor);
            switch (Dice.roll(4, 1)){

                case 1:
                    phaseA.add(new BlueBox(xPos, yPos, 80, 80));
                    break;
                case 2:
                    phaseA.add(new GreenBox(xPos, yPos, 80, 80));
                    break;
                case 3:
                    phaseA.add(new RedBox(xPos, yPos, 80, 80));
                    break;
                case 4:
                    phaseA.add(new YellowBox(xPos, yPos, 80, 80));
                    break;

                default:
                    System.out.println("Something went terribly wrong");
            }

            if (i%5==0){
                xPos = xPos + 80;
                yPos = 10;
            }
            yPos = yPos + 80;
        }
    }

}
