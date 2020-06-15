package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.GameLoop;
import com.itcr.ce.datosparty.Handler;
import com.itcr.ce.datosparty.gfx.Assets;
import com.itcr.ce.datosparty.userInterface.*;

import java.awt.*;

/**
 * In this method the players select the number of rounds they want to play
 */
public class SelectRoundState extends State {

    private final UIManager selectRoundUI;
    public static int round;

    /**
     * This method returns the number of rounds the players wanned to play once the player clicks on the button
     * @return the number of rounds the players want to play
     */
    public static int getRound() {
        return round;
    }

    /**
     * This is the constructor loads the buttons that asks the player how many rounds they want to play, it is
     * initialized in the constructor because this state is called in the game just once.
     * @param handler
     */
    public SelectRoundState(Handler handler) {
        super(handler);

        selectRoundUI = new UIManager(handler);

        selectRoundUI.addObject(new UIBackground(Assets.roundWallpaper, "wp"));

        selectRoundUI.addObject(new UIImage(29,5,12*4,6*4,Assets.roundTitle[0],"roundTitle"));

        selectRoundUI.addObject(new UIImageButton(4, 40, 7*4, 2*4, Assets.noviceButton,"noviceBtn", () -> {

            round=5;
            State.setState(GameLoop.selectPlayerState);

        }));

        selectRoundUI.addObject(new UIImageButton(37, 40, 7*4, 2*4, Assets.proButton,"proBtn", () -> {

            round=10;
            State.setState(GameLoop.selectPlayerState);

        }));

        selectRoundUI.addObject(new UIImageButton(69, 40, 7*4, 2*4, Assets.eliteButton,"eliteBtn", () -> {

            round=20;
            State.setState(GameLoop.selectPlayerState);

        }));


    }

    /**
     * The tick initializes once the state is called, the main difference with the constructor is that the constructor
     * runs when the program itself starts but the tick each time the state is called
     */
    @Override
    public void tick() {
        handler.getMouseManager().setUiManager(selectRoundUI);
        selectRoundUI.tick();
    }

    /**
     * Render method runs constantly in a loop once the game is started and finishes the moment the game also finishes,
     * this method also is the method in charge of rendering the graphics
     * @param g graphics parameter passed to GameLoop
     */
    @Override
    public void render(Graphics g) {
        selectRoundUI.renderAll(g);
    }
}
