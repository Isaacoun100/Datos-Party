package com.itcr.ce.datosparty.logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Used when a random number on a determined range is needed.
 */
public class Dice {

    /**
     * Gives a random number inside the range provided
     * @param min minimum value on range
     * @param max maximum value on range
     * @return random number contained on range
     */
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
