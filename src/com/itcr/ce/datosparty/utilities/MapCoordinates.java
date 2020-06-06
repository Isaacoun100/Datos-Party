package com.itcr.ce.datosparty.utilities;

import com.itcr.ce.datosparty.GameLauncher;
import com.itcr.ce.datosparty.dataStructures.lists.SinglyList;
import com.itcr.ce.datosparty.dataStructures.nodes.SinglyNode;

public class MapCoordinates {

    private static final int width = GameLauncher.width;
    private static final int height = GameLauncher.height;
    private static final int firstBoxX = (width/(800/56))+50;
    private static final int firstBoxY = (height/(608/299))-20;

    public static SinglyList<SinglyList<Integer>> getMainCircuitCoordinates() {
        SinglyList<SinglyList<Integer>> MainCircuitCoordinates = new SinglyList<>();

        //Index 0
        MainCircuitCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = MainCircuitCoordinates.getHead();
        current.getData().add(firstBoxX);
        current.getData().add(firstBoxY);
        //Index 1
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+25);
        current.getData().add(firstBoxY+70);
        //Index 2
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+55);
        current.getData().add(firstBoxY+140);
        //Index 3
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+95);
        current.getData().add(firstBoxY+205);
        //Index 4
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+140);
        current.getData().add(firstBoxY+265);
        //Index 5
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+205);
        current.getData().add(firstBoxY+305);
        //Index 6
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+280);
        current.getData().add(firstBoxY+320);
        //Index 7
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+350);
        current.getData().add(firstBoxY+295);
        //Index 8
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+410);
        current.getData().add(firstBoxY+250);
        //Index 9
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+455);
        current.getData().add(firstBoxY+190);
        //Index 10
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+490);
        current.getData().add(firstBoxY+125);
        //Index 11
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+530); // Phase A connector
        current.getData().add(firstBoxY+65);
        //Index 12
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+595); // Phase C connector
        current.getData().add(firstBoxY+95);
        //Index 13
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+660);
        current.getData().add(firstBoxY+135);
        //Index 14
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+715);
        current.getData().add(firstBoxY+190);
        //Index 15
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+745); // Phase B connector
        current.getData().add(firstBoxY+255);
        //Index 16
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+780);
        current.getData().add(firstBoxY+320);
        //Index 17
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+850);
        current.getData().add(firstBoxY+350);
        //Index 18
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+925);
        current.getData().add(firstBoxY+360);
        //Index 19
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1000);
        current.getData().add(firstBoxY+370);
        //Index 20
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1075);
        current.getData().add(firstBoxY+360);
        //Index 21
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1125);
        current.getData().add(firstBoxY+300);
        //Index 22
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1165);
        current.getData().add(firstBoxY+235);
        //Index 23
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1170);
        current.getData().add(firstBoxY+160);
        //Index 24
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1160);
        current.getData().add(firstBoxY+85);
        //Index 25
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1145);
        current.getData().add(firstBoxY+10);
        //Index 26
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1165);
        current.getData().add(firstBoxY-65);
        //Index 27
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1175);
        current.getData().add(firstBoxY-140);
        //Index 28
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1120);
        current.getData().add(firstBoxY-195);
        //Index 29
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1065);
        current.getData().add(firstBoxY-245);
        //Index 30
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1075);
        current.getData().add(firstBoxY-315);
        //Index 31
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1065);
        current.getData().add(firstBoxY-385);
        //Index 32
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1030);
        current.getData().add(firstBoxY-450);
        //Index 33
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1010);
        current.getData().add(firstBoxY-520);
        //Index 34
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+950);
        current.getData().add(firstBoxY-565);
        //Index 35
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+875);
        current.getData().add(firstBoxY-580);
        //Index 36
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+800);
        current.getData().add(firstBoxY-590);
        //Index 37
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+765);
        current.getData().add(firstBoxY-525);
        //Index 38
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+725);
        current.getData().add(firstBoxY-460);
        //Index 39
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+690);
        current.getData().add(firstBoxY-395);
        //Index 40
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+655);
        current.getData().add(firstBoxY-330);
        //Index 41
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+620);
        current.getData().add(firstBoxY-265);
        //Index 42
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+585);
        current.getData().add(firstBoxY-200);
        //Index 43
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+550);
        current.getData().add(firstBoxY-135);
        //Index 44
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+475);
        current.getData().add(firstBoxY-130);
        //Index 45
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+405);
        current.getData().add(firstBoxY-155);
        //Index 46
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+325);
        current.getData().add(firstBoxY-160);
        //Index 47
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+270);
        current.getData().add(firstBoxY-215);
        //Index 48
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+215);
        current.getData().add(firstBoxY-270);
        //Index 49
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+145);
        current.getData().add(firstBoxY-260);
        //Index 50
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+80);
        current.getData().add(firstBoxY-220);
        //Index 51
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+25);
        current.getData().add(firstBoxY-155);
        //Index 52
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX);
        current.getData().add(firstBoxY-80);
        //
        return MainCircuitCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseACoordinates() {
        SinglyList<SinglyList<Integer>> phaseACoordinates = new SinglyList<>();
        //Index 0
        phaseACoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseACoordinates.getHead();
        current.getData().add(firstBoxX+515);
        current.getData().add(firstBoxY-5);
        //Index 1
        phaseACoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+465);
        current.getData().add(firstBoxY-60);
        //
        return phaseACoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseBCoordinates() {
        SinglyList<SinglyList<Integer>> phaseBCoordinates = new SinglyList<>();
        //Index 0
        phaseBCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseBCoordinates.getHead();
        current.getData().add(firstBoxX+815);
        current.getData().add(firstBoxY+220);
        //Index 1
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+885);
        current.getData().add(firstBoxY+190);
        //Index 2
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+955);
        current.getData().add(firstBoxY+175);
        //Index 3
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1030);
        current.getData().add(firstBoxY+165);
        //Index 4
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1100);
        current.getData().add(firstBoxY+130);
        //
        return phaseBCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseCCoordinates() {
        SinglyList<SinglyList<Integer>> phaseCCoordinates = new SinglyList<>();
        //Index 0
        phaseCCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseCCoordinates.getHead();
        current.getData().add(firstBoxX+645);
        current.getData().add(firstBoxY+30);
        //Index 1
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+695);
        current.getData().add(firstBoxY-35);
        //Index 2
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+745);
        current.getData().add(firstBoxY-95);
        //Index 3
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+795);
        current.getData().add(firstBoxY-155);
        //Index 4
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+845);
        current.getData().add(firstBoxY-215);
        //Index 5
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+885);
        current.getData().add(firstBoxY-285);
        //Index 6
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+925);
        current.getData().add(firstBoxY-355);
        //Index 7
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+965);
        current.getData().add(firstBoxY-425);
        //
        return phaseCCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseDCoordinates() {
        SinglyList<SinglyList<Integer>> phaseDCoordinates = new SinglyList<>();
        //Index 0
        phaseDCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseDCoordinates.getHead();
        current.getData().add(firstBoxX+300);
        current.getData().add(firstBoxY-350);
        //Index 1
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+350);
        current.getData().add(firstBoxY-295);
        //Index 2
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+415);
        current.getData().add(firstBoxY-265);
        //Index 3
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+485);
        current.getData().add(firstBoxY-255);
        //Index 4
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+525);
        current.getData().add(firstBoxY-320);
        //Index 5
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+565);
        current.getData().add(firstBoxY-385);
        //Index 6
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+595);
        current.getData().add(firstBoxY-460);
        //Index 7
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+540);
        current.getData().add(firstBoxY-510);
        //Index 8
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+475);
        current.getData().add(firstBoxY-540);
        //Index 9
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+405);
        current.getData().add(firstBoxY-555);
        //Index 10
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+370);
        current.getData().add(firstBoxY-485);
        //Index 11
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+335);
        current.getData().add(firstBoxY-415);
        //
        return phaseDCoordinates;
    }

}
