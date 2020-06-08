package com.itcr.ce.datosparty.music;

import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

public class LoopMusic extends Thread{

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

