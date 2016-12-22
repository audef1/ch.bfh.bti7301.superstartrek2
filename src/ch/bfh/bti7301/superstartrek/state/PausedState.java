package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Florian on 18.12.2016.
 */
public class PausedState implements State {

    private final StateMachine statemachine;

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Color fontColor;
    private Font titleFont;
    private Color titleColor;

    public PausedState(StateMachine statemachine) {
        this.statemachine = statemachine;

        // init menu fonts and colors
        titleColor = new Color(238,221,130);
        titleFont = new Font("Final Frontier Old Style", Font.PLAIN, 75);

        fontColor = new Color(255,255,255);
        font = new Font("Arial", Font.PLAIN, 30);

    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // set background
        g.setBackground(Color.BLACK);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("GAME PAUSED", 160, 250);
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

        if (key == KeyEvent.VK_P) {
            statemachine.change("game");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}