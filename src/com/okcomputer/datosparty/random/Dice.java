package com.okcomputer.datosparty.random;

import java.util.Random;

public class Dice {

    public static int NumberSpace(){

        Random tileNumber;
        int randomNumber;

        tileNumber = new Random();

        randomNumber = tileNumber.nextInt(5)+1;

        return randomNumber;

    }
}
