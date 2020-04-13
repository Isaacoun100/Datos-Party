package com.okcomputer.datosparty.random;

import java.util.Random;

public class TrowingDice {

    public int NumberSpace(){

        Random tileNumber;
        int randomNumber;

        tileNumber = new Random();

        randomNumber = tileNumber.nextInt(6);

        return randomNumber;

    }
}
