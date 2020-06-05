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

        //
        MainCircuitCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = MainCircuitCoordinates.getHead();
        current.getData().add(firstBoxX);
        current.getData().add(firstBoxY);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+25);
        current.getData().add(firstBoxY+70);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+55);
        current.getData().add(firstBoxY+140);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+95);
        current.getData().add(firstBoxY+205);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+140);
        current.getData().add(firstBoxY+265);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+205);
        current.getData().add(firstBoxY+305);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+280);
        current.getData().add(firstBoxY+320);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+350);
        current.getData().add(firstBoxY+295);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+410);
        current.getData().add(firstBoxY+250);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+455);
        current.getData().add(firstBoxY+190);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+490);
        current.getData().add(firstBoxY+125);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+530); // Phase A connector
        current.getData().add(firstBoxY+65);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+595); // Phase C connector
        current.getData().add(firstBoxY+95);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+660);
        current.getData().add(firstBoxY+135);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+715);
        current.getData().add(firstBoxY+190);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+745); // Phase B connector
        current.getData().add(firstBoxY+255);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+780);
        current.getData().add(firstBoxY+320);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+850);
        current.getData().add(firstBoxY+350);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+925);
        current.getData().add(firstBoxY+360);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1000);
        current.getData().add(firstBoxY+370);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1075);
        current.getData().add(firstBoxY+360);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1125);
        current.getData().add(firstBoxY+300);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1165);
        current.getData().add(firstBoxY+235);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1170);
        current.getData().add(firstBoxY+160);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1160);
        current.getData().add(firstBoxY+85);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1145);
        current.getData().add(firstBoxY+10);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1165);
        current.getData().add(firstBoxY-65);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1175);
        current.getData().add(firstBoxY-140);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1120);
        current.getData().add(firstBoxY-195);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1065);
        current.getData().add(firstBoxY-245);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1075);
        current.getData().add(firstBoxY-315);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1065);
        current.getData().add(firstBoxY-385);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1030);
        current.getData().add(firstBoxY-450);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1010);
        current.getData().add(firstBoxY-520);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+950);
        current.getData().add(firstBoxY-565);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+875);
        current.getData().add(firstBoxY-580);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+800);
        current.getData().add(firstBoxY-590);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+765);
        current.getData().add(firstBoxY-525);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+725);
        current.getData().add(firstBoxY-460);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+690);
        current.getData().add(firstBoxY-395);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+655);
        current.getData().add(firstBoxY-330);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+620);
        current.getData().add(firstBoxY-265);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+585);
        current.getData().add(firstBoxY-200);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+550);
        current.getData().add(firstBoxY-135);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+475);
        current.getData().add(firstBoxY-130);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+405);
        current.getData().add(firstBoxY-155);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+325);
        current.getData().add(firstBoxY-160);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+270);
        current.getData().add(firstBoxY-215);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+215);
        current.getData().add(firstBoxY-270);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+145);
        current.getData().add(firstBoxY-260);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+80);
        current.getData().add(firstBoxY-220);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+25);
        current.getData().add(firstBoxY-155);
        //
        MainCircuitCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX);
        current.getData().add(firstBoxY-80);
        //
        return MainCircuitCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseACoordinates() {
        SinglyList<SinglyList<Integer>> phaseACoordinates = new SinglyList<>();
        //
        phaseACoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseACoordinates.getHead();
        current.getData().add(firstBoxX+515);
        current.getData().add(firstBoxY-5);
        //
        phaseACoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+465);
        current.getData().add(firstBoxY-60);
        //
        return phaseACoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseBCoordinates() {
        SinglyList<SinglyList<Integer>> phaseBCoordinates = new SinglyList<>();
        //
        phaseBCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseBCoordinates.getHead();
        current.getData().add(firstBoxX+815);
        current.getData().add(firstBoxY+220);
        //
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+885);
        current.getData().add(firstBoxY+190);
        //
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+955);
        current.getData().add(firstBoxY+175);
        //
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1030);
        current.getData().add(firstBoxY+165);
        //
        phaseBCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+1100);
        current.getData().add(firstBoxY+130);
        //
        return phaseBCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseCCoordinates() {
        SinglyList<SinglyList<Integer>> phaseCCoordinates = new SinglyList<>();
        //
        phaseCCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseCCoordinates.getHead();
        current.getData().add(firstBoxX+645);
        current.getData().add(firstBoxY+30);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+695);
        current.getData().add(firstBoxY-35);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+745);
        current.getData().add(firstBoxY-95);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+795);
        current.getData().add(firstBoxY-155);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+845);
        current.getData().add(firstBoxY-215);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+885);
        current.getData().add(firstBoxY-285);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+925);
        current.getData().add(firstBoxY-355);
        //
        phaseCCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+965);
        current.getData().add(firstBoxY-425);
        //
        return phaseCCoordinates;
    }

    public static SinglyList<SinglyList<Integer>> getPhaseDCoordinates() {
        SinglyList<SinglyList<Integer>> phaseDCoordinates = new SinglyList<>();
        //
        phaseDCoordinates.add(new SinglyList<>());
        SinglyNode<SinglyList<Integer>> current = phaseDCoordinates.getHead();
        current.getData().add(firstBoxX+300);
        current.getData().add(firstBoxY-350);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+350);
        current.getData().add(firstBoxY-295);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+415);
        current.getData().add(firstBoxY-265);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+485);
        current.getData().add(firstBoxY-255);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+525);
        current.getData().add(firstBoxY-320);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+565);
        current.getData().add(firstBoxY-385);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+595);
        current.getData().add(firstBoxY-460);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+540);
        current.getData().add(firstBoxY-510);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+475);
        current.getData().add(firstBoxY-540);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+405);
        current.getData().add(firstBoxY-555);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+370);
        current.getData().add(firstBoxY-485);
        //
        phaseDCoordinates.add(new SinglyList<>());
        current = (SinglyNode<SinglyList<Integer>>) current.getNext();
        current.getData().add(firstBoxX+335);
        current.getData().add(firstBoxY-415);
        //
        return phaseDCoordinates;
    }

}
