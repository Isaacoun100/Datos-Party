package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.Handler;

public class Game extends Thread {

    Handler handler;

    int currentRound = 1;

    public Game(Handler handler, int numRound) {
        this.handler = handler;

        Round.initRound();
        Round.addPlayer("Gabo");
        Round.addPlayer("Jose");
        Round.addPlayer("Isaac");
        Round.setNumRound(numRound);
        Turn.setPlayersTurn(Round.getPlayerOrder().getHead());

        Round.getPlayerOrder().getNodeByIndex(0).getData().setPosition(Board.phaseA.getNodeByIndex(0));
        Round.getPlayerOrder().getNodeByIndex(1).getData().setPosition(Board.phaseA.getNodeByIndex(0));
        Round.getPlayerOrder().getNodeByIndex(2).getData().setPosition(Board.phaseA.getNodeByIndex(0));
    }

    @Override
    public void run() {

        while (currentRound <= Round.getNumRound()) {
            Turn.setPlayersTurn(Round.getPlayerOrder().getHead());
            if (currentRound == 2) {
                System.out.println("Estrella");
                Board.setStar();
            }
            while (Turn.getPlayersTurn() != null) {
                //Player
                System.out.println(Turn.getPlayersTurn().getData().getName());
                Turn.rollDice();
                Turn.movePlayer();
                System.out.println("Dice: " + Turn.getPlayersTurn().getData().getMovement());
                System.out.print("\n");
                Turn.nextPlayer();
            }
            currentRound++;

//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }

    private void checkStar(Player player) {
        Box playerBox = player.getPosition().getData();
        if (playerBox.isStarBox()) {
            buyStar(player);
        }



    }

    public void setStar(){
        Random numRandom = new Random();
        CircularDoublyList<Box> phaseA =  handler.getBoard().getPhaseA();
        int starIndex = numRandom.nextInt(phaseA.getLength());
        Box starBox = phaseA.getNodeByIndex(starIndex).getData();
        starBox.setStarBox(true);
        
    }

    private void buyStar(Player player) {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to buy a star?");
        // Choose yes/no
        System.out.println("Are you sure? It's 10 coins.");
        //Choose yes/no
        if (player.getCoins() >= 10) {
            player.setCoins(-10);
            player.setStars(1);
            player.getPosition().getData().setStarBox(false);
            setStar();
        } else {
            System.out.println("You don't have enough money, though...");
        }
//        while (scanner.hasNext()) {
//            if (scanner.next().equals("Yes")) {
//                System.out.println("Are you sure? It's 10 coins.");
//                // Choose yes/no
//                if (scanner.next().equals("Yes")) {
//                    if (player.getCoins() >= 10) {
//                        player.setCoins(-10);
//                        player.setStars(1);
//                        this.starBox = false;
//                        Board.setStar();
//                    } else {
//                        System.out.println("You don't have enough money, though...");
//                    }
//                } else if (scanner.next().equals("No")) {
//                    System.out.println("Alright...");
//                } else {
//                    System.out.println("What? I'm gonna ask again...");
//                    buyStar(player);
//                }
//            } else if (scanner.next().equals("No")) {
//                System.out.println("Alright...");
//            } else {
//                System.out.println("What? I'm gonna ask again...");
//                buyStar(player);
//            }
//        }
//            if (scanner.next().equals("Yes"))
//        System.out.println("Are you sure? It's 10 coins.");
//        //Choose yes/no
//        if (player.getCoins() >= 10) {
//            player.setCoins(-10);
//            player.setStars(1);
//            this.starBox = false;
//            Board.setStar();
//        } else {
//            System.out.println("You don't have enough money, though...");
//        }
    }
}