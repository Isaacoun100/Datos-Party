package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.*;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.boxes.*;
import com.itcr.ce.datosparty.utilities.MapCoordinates;

import java.util.List;

public class Board {

    public final CircularList<Box> mainCircuit = new CircularList<>();
    public final SinglyList<Box> phaseA = new SinglyList<>();
    public final SinglyList<Box> phaseB = new SinglyList<>();
    public final DoublyList<Box> phaseC = new DoublyList<>();
    public final CircularDoublyList<Box> phaseD = new CircularDoublyList<>();

    public CircularList<Box> getMainCircuit() {
        return mainCircuit;
    }
    public SinglyList<Box> getPhaseA() {
        return phaseA;
    }
    public SinglyList<Box> getPhaseB() {
        return phaseB;
    }
    public DoublyList<Box> getPhaseC() {
        return phaseC;
    }
    public CircularDoublyList<Box> getPhaseD() {
        return phaseD;
    }

    public Board() {
        buildMainCircuit();
        buildPhaseA();
        buildPhaseB();
        buildPhaseC();
        phaseDBuilder();
    }

    private void pickBox(int xPos, int yPos, LinkedList<Box> mainCircuit) {
        switch (Dice.roll(3, 1)) {
            case 1 -> mainCircuit.add(new BlueBox(xPos, yPos, 80, 80));
            case 2 -> mainCircuit.add(new GreenBox(xPos, yPos, 80, 80));
            case 3 -> mainCircuit.add(new RedBox(xPos, yPos, 80, 80));
            default -> System.out.println("Something went terribly wrong");
        }
    }

    private void buildPath(SinglyList<SinglyList<Integer>> coordinateList, LinkedList<Box> phase, boolean yellowRoad) {
        SinglyNode<SinglyList<Integer>> current = coordinateList.getHead();
        int firstYellow = Dice.roll(6,3);
        for(int i = 0; i < coordinateList.getLength(); i++){

            int xPos = current.getData().getHead().getData();
            int yPos = current.getData().getHead().getNext().getData();

            if(i == firstYellow | yellowRoad){
                phase.add((new YellowBox(xPos, yPos, 80, 80)));
                firstYellow += 5;
            }
            else {
                pickBox(xPos, yPos, phase);
            }
            current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        }
    }

    public void buildMainCircuit(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getMainCircuitCoordinates();
        buildPath(coordinateList, mainCircuit, false);
    }

    public void buildPhaseA() {
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseACoordinates();
        buildPath(coordinateList, phaseA, false);
    }

    public void buildPhaseB(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseBCoordinates();
        buildPath(coordinateList, phaseB, true);
    }

    public void buildPhaseC(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseCCoordinates();
        buildPath(coordinateList, phaseC, false);
    }

    public void phaseDBuilder(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseDCoordinates();
        buildPath(coordinateList, phaseD, true);
    }

}
