package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;

public class Board {

    private SinglyList<Box> phaseA = new SinglyList<>();
    private Box blueBox, greenBox, yellowBox, redBox;

    public Board() {

        phaseA.add(blueBox = new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(greenBox = new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(redBox = new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(yellowBox = new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(blueBox = new BlueBox(400,30+80,80,80));
        phaseA.add(greenBox = new GreenBox(400,30+80*2,80,80));


    }

    public SinglyList<Box> getPhaseA() {
        return phaseA;
    }
}
