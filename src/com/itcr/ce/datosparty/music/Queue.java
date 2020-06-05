package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;

public class Queue {

    public String[] songList;
    static SinglyList<String[]> queue = new SinglyList<>();

    public String[] plazaMii;

    public static void songList(){

        queue.add(new String[]{"res/audio/background/Last Train Home.wav", "Last Train Home"});
        queue.add(new String[]{"res/audio/background/Cowboy Bebop OST 1 - Piano Black.wav", "Piano Black"});
        queue.add(new String[]{"res/audio/background/Nintendo Wii - Mii Channel Theme.wav", "Mii Channel"});
        queue.add(new String[]{"res/audio/background/Nintendo 3DS Music - StreetPass Mii Plaza Theme 1.wav","Mii Plaza"});

    }

}
