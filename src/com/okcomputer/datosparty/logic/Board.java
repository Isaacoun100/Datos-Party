package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.dataStructures.SinglyList;

public class Board {

    private final SinglyList<Box> phaseA = new SinglyList<>();

    public Board() {

        phaseA.add(new BlueBox(400-(80*2)+80,30,80,80));
        phaseA.add(new GreenBox(400-(80*2),30+80,80,80));
        phaseA.add(new RedBox(400-(80*2),30+80*2,80,80));
        phaseA.add(new YellowBox(400-(80*2)+80,30+80*3,80,80));
        phaseA.add(new BlueBox(400,30+80,80,80));
        phaseA.add(new GreenBox(400,30+80*2,80,80));


    }

    public SinglyList<Box> getPhaseA() {
        return phaseA;
    }
}
