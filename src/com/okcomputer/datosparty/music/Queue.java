package com.okcomputer.datosparty.music;

import com.okcomputer.datosparty.dataStructures.SinglyList;

public class Queue {

    public String[] songList;
    static SinglyList<String[]> queue = new SinglyList<>();

    public String[] plazaMii;

    public static void songList(){

        queue.add(new String[]{"res/audio/background/Nintendo Wii - Mii Channel Theme.wav", "Mii Channel"});
        queue.add(new String[]{"res/audio/background/Nintendo 3DS Music - StreetPass Mii Plaza Theme 1.wav","Mii Plaza"});

    }

}
