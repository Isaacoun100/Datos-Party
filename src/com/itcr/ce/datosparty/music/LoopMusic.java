package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;

public class LoopMusic extends Thread{

    @Override
    public void run() {

        Queue.songList();
        SinglyList<String[]> songList = Queue.queue;

        int length, i=0;
        long size;
        length = (songList.getLength())-1;

        while(i<=length){

            String[] newSong = songList.get(i).getData();
            MusicPlayer.playSong(newSong[0],newSong[1]);
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

