package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.CircularDoublyList;
import com.itcr.ce.datosparty.logic.boxes.*;

import java.util.Random;

public class Board {

    public static final CircularDoublyList<Box> phaseA = new CircularDoublyList<>();

    public Board() {

        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));
        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));
        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));
        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));
        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));


    }

    public static void setStar() {
        Random numRandom = new Random();
        int starIndex = numRandom.nextInt(phaseA.getLength());
        phaseA.getNodeByIndex(starIndex).getData().setStarBox(true);
    }
}
