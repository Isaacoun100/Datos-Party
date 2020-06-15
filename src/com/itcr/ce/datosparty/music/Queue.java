package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;

/**
 * This queue contains all of the songs that are going to be played during the gameplay
 */
public class Queue {

    static SinglyList<String> queue = new SinglyList<>();

    /**
     * This class adds all of the songs directories to the Queue so that can be easily accessed in the future
     */
    public static void songList(){

        queue.add("res/audio/background/Last Train Home.wav");
        queue.add("res/audio/background/Synthwave Crusaders (Jotaros Theme synthwave 80s remix) by Astrophysics.wav");
        queue.add("res/audio/background/Cowboy Bebop OST 1 - Piano Black.wav");
        queue.add("res/audio/background/Nintendo Wii - Mii Channel Theme.wav");
        queue.add("res/audio/background/Nintendo 3DS Music - StreetPass Mii Plaza Theme 1.wav");

    }

}
