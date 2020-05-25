package com.itcr.ce.datosparty.logic;

import java.util.Random;

public class Dice {

    public static int roll(){

        Random tileNumber;
        int randomNumber;

        tileNumber = new Random();

        randomNumber = tileNumber.nextInt(6)+1;

        return randomNumber;

    }
}
