package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by buche on 01.12.2016.
 */
public class StateMachine implements State{
    /**
     * Contains all states of this state machine.
     */
    private final Map<String, State> states;
    /**
     * Current active state.
     */
    private State currentState;

    /**
     * Creates a state machine.
     */
    public StateMachine() {
        states = new HashMap<>();
        currentState = new MenuState(this); // initial state
        states.put(null, currentState);
    }

    /**
     * Adds a state with specified name.
     *
     * @param name  Name of the state
     * @param state The state to add
     */
    public void add(String name, State state) {
        states.put(name, state);
    }

    /**
     * Changes the current state.
     *
     * @param name Name of the desired state
     */
    public void change(String name) {
        currentState.exit();
        currentState = states.get(name);
        currentState.enter();
    }

    /**
     * Initiates the States
     *
     * @param size Size of the current Game
     */
    public void initStates(int size){
        add("menu", new MenuState(this));
        add("options", new OptionState(this));
        add("paused", new PausedState(this));
        add("game", new GameState(this));
        change("menu");
    }

    public void keyPressed(int k)
    {
        currentState.keyPressed(k);
    }

    public void keyReleased(int k)
    {
        currentState.keyReleased(k);
    }

    @Override
    public void input() {
        currentState.input();
    }

    @Override
    public void update() {
        currentState.update();
    }

    @Override
    public void draw(Graphics2D g) {
        currentState.draw(g);
    }

    @Override
    public void enter() {
        currentState.enter();
    }

    @Override
    public void exit() {
        currentState.exit();
    }

}
