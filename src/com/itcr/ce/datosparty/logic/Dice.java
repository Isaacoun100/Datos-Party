package com.itcr.ce.datosparty.logic;

import java.util.Random;

public class Dice {

    public static int roll(int min, int max){
        Random tileNumber;
        int randomNumber;
        if (max != min) {
            tileNumber = new Random();

            randomNumber = tileNumber.nextInt(max)+min;

        } else {
            randomNumber = max;
        }
        return randomNumber;
    }
}
