package com.okcomputer.datosparty.logic;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.dataStructures.SinglyList;

public class GameStart {


    private final SinglyList<Box> board = new SinglyList<>();
    public Player player1;
    public BlueBox blueBox;

    public SinglyList<Box> getBoard() {
        return board;
    }


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Player getPlayer4() {
        return player4;
    }

    public void setPlayer4(Player player4) {
        this.player4 = player4;
    }

    public Player player2;
    public Player player3;
    public Player player4;
    private final SinglyList<Player> activePlayerList = new SinglyList<>();


    public GameStart(int numberOfPlayers, Handler handler) {

        board.add(blueBox = new BlueBox(100,100,2,2));
        board.add(blueBox = new BlueBox(200,200,2,2));


        if(numberOfPlayers == 2){
            player1 = new Player("Jonathan");
            player2 = new Player("Joseph");
            activePlayerList.add(player1);
            activePlayerList.add(player2);
        }
        else if(numberOfPlayers == 3){
            player1 = new Player("Jonathan");
            player2 = new Player("Joseph");
            player3 = new Player("Jotaro");
            activePlayerList.add(player1);
            activePlayerList.add(player2);
            activePlayerList.add(player3);
        }
        else {
            player1 = new Player("Jonathan");
            player2 = new Player("Joseph");
            player3 = new Player("Jotaro");
            player4 = new Player("Josuke");
            activePlayerList.add(player1);
            activePlayerList.add(player2);
            activePlayerList.add(player3);
            activePlayerList.add(player4);
        }
    }

    public SinglyList<Player> getActivePlayerList(){
        return activePlayerList;
    }
}
