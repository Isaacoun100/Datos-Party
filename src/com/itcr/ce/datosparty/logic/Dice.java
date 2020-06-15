package com.itcr.ce.datosparty.logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class works as our randomizer for all that we need to randomize in the game, the minigames, the dices, the moves
 * the players, and much more!.
 */
public class Dice {

    /**
     * This class recieves the minimum and maximum value that is going to be randomized
     * @param min minimum value to choose from
     * @param max maximum value to choose from
     * @return the random value
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
