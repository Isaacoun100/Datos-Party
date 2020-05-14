package com.okcomputer.datosparty.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {

    public static void playSong(String musicLocation, String songName){

        try{

            File musicPath = new File(musicLocation);

            if (musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                System.out.println(songName);

            }

            else{ System.out.println("File does not exist"); }

        }

        catch(Exception ex){ ex.printStackTrace();}

    }

}
