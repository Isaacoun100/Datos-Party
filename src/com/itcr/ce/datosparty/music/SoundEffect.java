package com.itcr.ce.datosparty.music;

/**
 * This class contains all of the clip sounds we need during the game
 */
public class SoundEffect {

    /**
     * It plays a dice rolling sound using the MusicPlayer class
     */
    public static void DiceRoll(){
        MusicPlayer.playClip("res/audio/effects/Dice Roll Sound _ Non Copyrighted Sound Effects.wav",
                                "Dice Rolling");

    }

    /**
     * It plays a pop sound using the MusicPlayer class
     */
    public static void PopSound(){
        MusicPlayer.playClip("res/audio/effects/PopSound.wav",
                                "Pop sound");

    }

}
