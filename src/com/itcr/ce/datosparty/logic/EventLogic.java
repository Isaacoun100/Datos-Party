package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.entities.Player;

public class EventLogic {

    public static void stealCoins(Player thief, Player victim) {
        System.out.println("STEAL_COINS");
        // Chooses random player
        // Should give player the option to steal whoever player he chooses to
        int randomCoins = Dice.roll(1, 10);
        if (victim.getCoins() < randomCoins) {
            randomCoins = thief.getCoins();
        }
        victim.addCoins(-randomCoins);
        thief.addCoins(randomCoins);
    }
}
