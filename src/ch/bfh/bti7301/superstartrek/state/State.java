package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by buche on 01.12.2016.
 */
public class State {

    private final StateMachine stateMachine;
    private GamePanel gamePanel;
    private ArrayList<SubPanel> panels = new ArrayList<SubPanel>();

    public State(StateMachine stateMachine){
        this.stateMachine = stateMachine;
        this.gamePanel = stateMachine.getGamePanel();
    }

    /**
     * Handles input of the state.
     */
    public void input(){

    }

    /**
     * Updates the state
     */
    public void update(){}

    /**
     * Draws the elements in the state
     */
    public void draw(){}

    /**
     * Gets executed when entering the state, useful for initialization.
     */
    public void enter(){}

    /**
     * Gets executed when leaving the state, useful for disposing.
     */
    public void exit(){}

    /**
     * Gets the pressed keys
     */
    public void keyPressed(KeyEvent e){}

    /**
     * Gets the released keys
     */
    public void keyReleased(KeyEvent e){}

    /**
     * Gets the subpanels
     */
    public ArrayList<SubPanel> getPanels(){
        return panels;
    }

    /**
     * Gets the StateMachine
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /**
     * Gets the GamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }


}
