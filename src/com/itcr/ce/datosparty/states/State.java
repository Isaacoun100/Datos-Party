package com.itcr.ce.datosparty.states;

import com.itcr.ce.datosparty.Handler;

import java.awt.*;

/**
 * parent class that other states extend from
 */
public abstract class State {

    private static State currentState = null;

    /**
     * this sets the desired state to be visible and active
     * @param state State object
     */
    public static void setState(State state){
        currentState = state;
    }

    /**
     * getter for the state class
     * @return the current active state
     */
    public static State getState(){
        return currentState;
    }

    protected Handler handler;

    /**
     *  Main Constructor for the State
     * @param handler handler element is the only parameter
     */
    public State(Handler handler){
        this.handler = handler;
    }

    /**
     * Use this method for code that requires something to be checked each "tick"
     */
    public abstract void tick() throws InterruptedException;

    /**
     * Use this method to render any graphics in our state
     * @param g graphics parameter passed to gameloop
     */
    public abstract void render(Graphics g);

}


