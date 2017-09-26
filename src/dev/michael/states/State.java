package dev.michael.states;

import dev.michael.tilegame.Game;
import dev.michael.tilegame.Handler;

import java.awt.*;

public abstract class State {

    // STATE MANAGER
    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    // CLASS
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
