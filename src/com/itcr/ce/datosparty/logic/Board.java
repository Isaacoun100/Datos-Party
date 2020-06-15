package com.itcr.ce.datosparty.logic;

import com.itcr.ce.datosparty.dataStructures.lists.*;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;
import com.itcr.ce.datosparty.entities.boxes.*;
import com.itcr.ce.datosparty.utilities.MapCoordinates;

/**
 * this class is the board where the game is played, it has 5 different lists, with different parameters each
 */
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

        mainCircuit.get(11).getData().setBoxID("phaseA");
        mainCircuit.get(11).getData().setCrossRoads(true);
        mainCircuit.get(15).getData().setBoxID("phaseB");
        mainCircuit.get(15).getData().setCrossRoads(true);
        mainCircuit.get(12).getData().setBoxID("phaseC1");
        mainCircuit.get(12).getData().setCrossRoads(true);
        mainCircuit.get(33).getData().setBoxID("phaseC2");
        mainCircuit.get(33).getData().setCrossRoads(true);
        phaseD.get(1).getData().setBoxID("phaseD");
        phaseD.get(1).getData().setCrossRoads(true);

    }

    /**
     * This method picks the color of the boxes being placed, by choosing between 3 box colors: red, blue and green
     * @param xPos int xPosition of the box being placed
     * @param yPos int yPosition of the box being placed
     * @param phase the phase where the box is currently being placed
     */
    private void pickBox(int xPos, int yPos, LinkedList<Box> phase) {
        switch (Dice.roll(1, 3)) {
            case 1 -> phase.add(new BlueBox(xPos, yPos, 80, 80));
            case 2 -> phase.add(new GreenBox(xPos, yPos, 80, 80));
            case 3 -> phase.add(new RedBox(xPos, yPos, 80, 80));
            default -> System.out.println("Something went terribly wrong");
        }
    }

    /**
     * This method builds a path with a supplied coordinates list, it will pass each value on the list and then choose a
     * random number between 3 and 6 to place the first yellow box, which then are placed every 5 boxes
     * @param coordinateList an array consisting of x and y values for all boxes
     * @param phase the phase that is currently being built
     * @param yellowRoad a boolean that if set to true, will set all boxes in a phase to yellow
     */
    private void buildPath(SinglyList<SinglyList<Integer>> coordinateList, LinkedList<Box> phase, boolean yellowRoad) {
        SinglyNode<SinglyList<Integer>> current = coordinateList.getHead();
        int firstYellow = Dice.roll(3, 6);
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

    /**
     * Builds the Main circuit using a coordinate list, and the main circuit list.
     */
    public void buildMainCircuit(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getMainCircuitCoordinates();
        buildPath(coordinateList, mainCircuit, false);
    }

    /**
     * Builds the phaseA  using a coordinate list, and the phaseA list.
     */
    public void buildPhaseA() {
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseACoordinates();
        buildPath(coordinateList, phaseA, false);
    }

    /**
     * Builds the phaseB  using a coordinate list, and the phaseB list. yellowRoad is set to true
     */
    public void buildPhaseB(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseBCoordinates();
        buildPath(coordinateList, phaseB, true);
    }

    /**
     * Builds the phaseC  using a coordinate list, and the phaseC list.
     */
    public void buildPhaseC(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseCCoordinates();
        buildPath(coordinateList, phaseC, false);
    }

    /**
     * Builds the phaseD  using a coordinate list, and the phaseD list. yellowRoad is set to true
     */
    public void phaseDBuilder(){
        SinglyList<SinglyList<Integer>> coordinateList = MapCoordinates.getPhaseDCoordinates();
        buildPath(coordinateList, phaseD, true);
    }

}
