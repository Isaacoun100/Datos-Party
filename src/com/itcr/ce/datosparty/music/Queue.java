package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;

public class Queue {

    static SinglyList<String> queue = new SinglyList<>();

    public static void songList(){

        queue.add("res/audio/background/Last Train Home.wav");
        queue.add("res/audio/background/Synthwave Crusaders (Jotaros Theme synthwave 80s remix) by Astrophysics.wav");
        queue.add("res/audio/background/Cowboy Bebop OST 1 - Piano Black.wav");
        queue.add("res/audio/background/Nintendo Wii - Mii Channel Theme.wav");
        queue.add("res/audio/background/Nintendo 3DS Music - StreetPass Mii Plaza Theme 1.wav");

    }

}
