package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.Handler;

public class Game extends Thread {

    Handler handler;

    int currentRound = 1,
            roundMAX;

    public Game(Handler handler, int numRound) {
        this.handler = handler;
        Round.initRound();
        Round.addPlayer("Gabo");
        Round.addPlayer("Jose");
        Round.addPlayer("Isaac");
        Round.setNumRound(numRound);
        Turn.setPlayersTurn(Round.getPlayerOrder().getHead());

        Round.getPlayerOrder().getNodeByIndex(0).getData().setPosition(Board.phaseA.getNodeByIndex(0));
        Round.getPlayerOrder().getNodeByIndex(1).getData().setPosition(Board.phaseA.getNodeByIndex(10));
        Round.getPlayerOrder().getNodeByIndex(2).getData().setPosition(Board.phaseA.getNodeByIndex(20));
    }

    @Override
    public void run() {

        while (currentRound <= Round.getNumRound()) {
            Turn.setPlayersTurn(Round.getPlayerOrder().getHead());
            while (Turn.getPlayersTurn() != null) {
                System.out.println(Turn.getPlayersTurn().getData().getName());
                Turn.rollDice();
                System.out.println(Turn.getPlayersTurn().getData().getMovement());
                Turn.nextPlayer();
            }
            if (currentRound == 2) {
                System.out.println("Estrella");
            }
            currentRound++;
        }
    }
}