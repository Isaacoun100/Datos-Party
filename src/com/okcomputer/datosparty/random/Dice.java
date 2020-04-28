package com.okcomputer.datosparty.random;

import java.util.Random;

// Name chaged form ThrowingDice to Dice
public class Dice {

    public static int NumberSpace(){

        Random tileNumber;
        int randomNumber;

        tileNumber = new Random();

        randomNumber = tileNumber.nextInt(6)+1;

        return randomNumber;

    }
}
