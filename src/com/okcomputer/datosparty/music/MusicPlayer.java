package com.okcomputer.datosparty.music;

import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {

    private static double gameMusicLevel = 1.0, effectMusicLevel = 1.0;
    private static Clip clip, track;

    public static void playSong(String musicLocation, String songName){

        try{

            File musicPath = new File(musicLocation);

            if (musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                track = AudioSystem.getClip();
                track.open(audioInput);
                track.start();

                System.out.println(songName);


            }

            else{ System.out.println("File does not exist"); }

        }

        catch(Exception ex){ ex.printStackTrace();}

    }

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

    public static void musicVolumeUp(){

        if (gameMusicLevel>=1.0){ System.out.println("Max Volume Reached"); }

        else{

            FloatControl gameControl = (FloatControl) track.getControl(FloatControl.Type.MASTER_GAIN);
            gameMusicLevel+=0.05;
            float dB = (float) (Math.log(gameMusicLevel) / Math.log(10.0) * 20.0);
            gameControl.setValue(dB);

        }


    }

    public static void musicVolumeDown(){

        if (gameMusicLevel<=0.0) { System.out.println("Min Volume Reached");}

        else{

            FloatControl gameControl = (FloatControl) track.getControl(FloatControl.Type.MASTER_GAIN);
            gameMusicLevel-=0.05;
            float dB = (float) (Math.log(gameMusicLevel) / Math.log(10.0) * 20.0);
            gameControl.setValue(dB);

        }


    }

    public static void clipVolumeUp(){

        if (effectMusicLevel>=1.0){ System.out.println("Max Volume Reached"); }

        else{

            FloatControl effectControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            effectMusicLevel+=0.05;
            float dB = (float) (Math.log(effectMusicLevel) / Math.log(10.0) * 20.0);
            effectControl.setValue(dB);

        }


    }

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

    public static void stopSong(){
        track.stop();
    }

    public static void resumeSong(){ track.start();}

    public static long songLength(){ return track.getMicrosecondLength();}



}
