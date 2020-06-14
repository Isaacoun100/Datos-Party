package com.itcr.ce.datosparty.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    public static int roll(int min, int max){
        int randomNumber = 0;
        if (min < max) {
            randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        } else if (min == max){
            randomNumber = max;
        } else {
            System.out.println("Wrong order. Reallocating...");
            roll(max, min);
        }
        return randomNumber;
    }
}
