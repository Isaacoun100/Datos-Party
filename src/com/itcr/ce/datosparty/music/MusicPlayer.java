package com.itcr.ce.datosparty.music;

import javax.sound.sampled.*;
import java.io.File;

/**
 * This class plays all of the music that is going to be played during the game
 */
public class MusicPlayer {

    private static double gameMusicLevel = 1.0, effectMusicLevel = 1.0;
    private static Clip clip, track;

    /**
     * This method is dedicated to playing songs, this approach is made to always have 2 threads running in the
     * background, one is for the background music, and the other one is for sound effects, that way the threads
     * don't kill each other.
     * @param musicLocation
     */
    public static void playSong(String musicLocation){

        try{

            File musicPath = new File(musicLocation);

            if (musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                track = AudioSystem.getClip();
                track.open(audioInput);
                track.start();


            }

            else{ System.out.println("File does not exist"); }

        }

        catch(Exception ex){ ex.printStackTrace();}

    }

    /**
     * This method is dedicated to small clips and sound effects, this approach is made to always have 2 threads
     * running in the background, one is for the background music, and the other one is for sound effects, that way the
     * threads don't kill each other.
     * @param musicLocation
     */
    public static void playClip(String musicLocation, String songName){

        try{
            File clipPath = new File(musicLocation);

            if (clipPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(clipPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                System.out.println(songName);


            }

            else{ System.out.println("File does not exist"); }

        }

        catch(Exception ex){ ex.printStackTrace();}

    }

    /**
     * This method turns the volume up for the background music
     */
    public static void musicVolumeUp(){

        if (gameMusicLevel>=1.0){ System.out.println("Max Volume Reached"); }

        else{

            FloatControl gameControl = (FloatControl) track.getControl(FloatControl.Type.MASTER_GAIN);
            gameMusicLevel+=0.05;
            float dB = (float) (Math.log(gameMusicLevel) / Math.log(10.0) * 20.0);
            gameControl.setValue(dB);

        }


    }

    /**
     * This method turns the down up for the background music
     */
    public static void musicVolumeDown(){

        if (gameMusicLevel<=0.0) { System.out.println("Min Volume Reached");}

        else{

            FloatControl gameControl = (FloatControl) track.getControl(FloatControl.Type.MASTER_GAIN);
            gameMusicLevel-=0.05;
            float dB = (float) (Math.log(gameMusicLevel) / Math.log(10.0) * 20.0);
            gameControl.setValue(dB);

        }


    }

    /**
     * This method turns the volume up for the clips and sound effects
     */
    public static void clipVolumeUp(){

        if (effectMusicLevel>=1.0){ System.out.println("Max Volume Reached"); }

        else{

            FloatControl effectControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            effectMusicLevel+=0.05;
            float dB = (float) (Math.log(effectMusicLevel) / Math.log(10.0) * 20.0);
            effectControl.setValue(dB);

        }


    }

    /**
     * This method turns the down up for the clips and sound effects
     */
    public static void clipVolumeDown(){

        if (effectMusicLevel<=0.0) {
            System.out.println("Min Volume Reached");
        }

        else{

            FloatControl effectControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            effectMusicLevel-=0.05;
            float dB = (float) (Math.log(effectMusicLevel) / Math.log(10.0) * 20.0);
            effectControl.setValue(dB);

        }


    }

    /**
     * This method is very important to have a better music loop with lower cpu consumption, it retrieves the amount
     * of microseconds the song lasts to sleep the thread and wait for the next song
     * @return
     */
    public static long songLength(){ return track.getMicrosecondLength();}



}
