package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by buche on 01.12.2016.
 */
public class StateMachine {

    /**
     * Contains the GamePanel for layout changes.
     */
    private GamePanel gamePanel;

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
    public StateMachine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
        add("map", new MapState(this));
        add("game", new GameState(this));
        change("menu");
    }

    public void keyPressed(KeyEvent e)
    {
        currentState.keyPressed(e);
    }

    public void keyReleased(KeyEvent e)
    {
        currentState.keyReleased(e);
    }

    public void input() {
        currentState.input();
    }

    public void update() {
        currentState.update();
    }

    public void draw() {
        for (SubPanel subPanel : currentState.getPanels()){
            subPanel.draw();
        }
    }

    public void drawToScreen() {
        for (SubPanel subPanel : currentState.getPanels()){
            subPanel.drawToScreen();
        }
    }

    public void enter() {
        currentState.enter();
    }


    public void exit() {
        currentState.exit();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


}