package com.itcr.ce.datosparty.logic;

import java.util.Random;

public class Dice {

    public static int roll(int max, int min){

        Random tileNumber;
        int randomNumber;

        tileNumber = new Random();

        randomNumber = tileNumber.nextInt(max)+min;

        return randomNumber;

    }
}
