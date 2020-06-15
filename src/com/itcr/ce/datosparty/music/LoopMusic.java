package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;

/**
 * This is the background music loop class, runs in the background with the music in the Queue
 */
public class LoopMusic extends Thread{

    /**
     * Activates the thread that we're going to use so that the game and the music can run independently and if one
     * stops, the other one doesn't and avoid any thread related issue. This method plays the first song that he can
     * that appears in the Queue, the it waits for the song to finish, this way we avoid a constant loop, but a
     * controlled loop with little iterations.
     */
    @Override
    public void run() {

        Queue.songList();
        SinglyList<String> songList = Queue.queue;

        int length, i=0;
        long size;
        length = (songList.getLength())-1;

        while(i<=length){

            MusicPlayer.playSong(songList.get(i).getData());
            size=MusicPlayer.songLength();

            if(i==length){
                try { Thread.sleep(size/1000);}
                catch (InterruptedException e) { e.printStackTrace();}
                i=0;
            }
            else{
                try { Thread.sleep(size/1000);}
                catch (InterruptedException e) { e.printStackTrace();}
                i++;

            }

        }

    }
}

