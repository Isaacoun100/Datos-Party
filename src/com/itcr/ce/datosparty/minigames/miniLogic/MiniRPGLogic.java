package com.itcr.ce.datosparty.minigames.miniLogic;

import com.itcr.ce.datosparty.dataStructures.lists.CircularList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.Player;
import com.itcr.ce.datosparty.logic.Round;
import com.itcr.ce.datosparty.logic.Turn;
import com.itcr.ce.datosparty.minigames.MiniGameBuilder;

public class MiniRPGLogic extends Thread{

    public MiniRPGLogic(CircularList<Player> playerList){

    }

//    public void run() {
//        while(currentRound != Round.getMaxRound()){
//            try {
//                playMiniRound(this);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Minigame.playMinigame(1);
//            try {
//                pauseMiniGame();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void playMiniRound(MiniRPGLogic miniRPGLogic) {
//        currentTurn = (SinglyNode<Player>) currentTurn.getNext();
//        Turn.setPlayersTurn(currentTurn);
//        game.pauseGame();
//        playMiniTurn(miniRPGLogic, player2);
//
//        if(numberOfPlayers >= 3){
//            Player player3 = game.getPlayerList().get(2).getData();
//            currentTurn = (SinglyNode<Player>) currentTurn.getNext();
//            Turn.setPlayersTurn(currentTurn);
//            game.pauseGame();
//            Turn.playTurn(game, player3);
//        }
//        if(numberOfPlayers == 4){
//            Player player4 = game.getPlayerList().get(3).getData();
//            currentTurn = (SinglyNode<Player>) currentTurn.getNext();
//            Turn.setPlayersTurn(currentTurn);
//            game.pauseGame();
//            Turn.playTurn(game, player4);
//        }
//    }

    private void playMiniTurn(MiniRPGLogic logic){

    }

    private synchronized void pauseMiniGame() throws InterruptedException {
        this.wait();
    }

    public synchronized void resumeGame() {
        this.notify();
    }

    public boolean checker(Player player1,Player player2,Player player3,Player player4){
        return true;
    }
}
