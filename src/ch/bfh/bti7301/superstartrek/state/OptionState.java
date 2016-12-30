package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Florian on 18.12.2016.
 */
public class OptionState extends State {

    private SubPanel mainPanel;
    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Font titleFont;
    private Color titleColor;

    public OptionState(StateMachine stateMachine) {

        super(stateMachine);

        mainPanel = new SubPanel(this, GamePanel.WIDTH, GamePanel.HEIGHT);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
        getPanels().add(mainPanel);
    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

}