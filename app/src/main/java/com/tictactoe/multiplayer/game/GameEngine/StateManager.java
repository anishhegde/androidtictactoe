package com.tictactoe.multiplayer.game.GameEngine;

public class StateManager {

    public static String X_VALUE = "X";
    public static String O_VALUE = "O";
    private static StateManager stateManager = null;
    private String stateValue = O_VALUE;

    public State getState() {
        return state;
    }

    private State state = State.PLAY;

    enum State
    {
        PLAY, END;
    }

    public static StateManager getInstance() {
        if (stateManager == null) {
            stateManager = new StateManager();
        }
        return stateManager;
    }

    public void next() {
        if (stateValue.equalsIgnoreCase(O_VALUE)) {
            stateValue = X_VALUE;
        } else {
            stateValue = O_VALUE;
        }
    }

    public void play() {
        state = State.PLAY;
        stateValue = O_VALUE;
    }

    public void end() {
        state = State.END;
        stateValue = O_VALUE;
    }

    public String getStateValue() {
        return stateValue;
    }

}
