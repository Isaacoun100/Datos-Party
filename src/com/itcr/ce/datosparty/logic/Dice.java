package com.itcr.ce.datosparty.logic;

import java.util.Random;

public class Dice {

    public static int roll(int max, int min){
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
