package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Florian on 18.12.2016.
 */
public class OptionState implements State {

    private StateMachine statemachine;

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Font titleFont;
    private Color titleColor;

    private OptionState(StateMachine statemachine) {

        this.statemachine = statemachine;

    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

}