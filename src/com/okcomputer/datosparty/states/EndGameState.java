package com.okcomputer.datosparty.states;

import com.okcomputer.datosparty.Handler;
import com.okcomputer.datosparty.gfx.Assets;
import com.okcomputer.datosparty.userInterface.*;

import java.awt.*;

public class EndGameState extends State {


    private final UIManager uiManager;

    /**
     * Main Constructor for the State
     *
     * @param handler handler element is the only parameter
     */
    public EndGameState(Handler handler) {
        super(handler);

        uiManager = new EndGameUI(handler);

        uiManager.addObject(new UIImageButton(20, 25, 3*3, 3*3, Assets.notOKComputerIcon, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("hola mundo");
            }
        }));

        uiManager.addObject(new UIImageButton(10, 15, 3*3, 3*3, Assets.boton1, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("Boton 1");
            }
        }));



    }

    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);


        g.drawImage(Assets.notOKComputerIcon[0] , 82,100,null);
        g.drawImage(Assets.notOKComputer[0] , 40,130,null);

    }
}
