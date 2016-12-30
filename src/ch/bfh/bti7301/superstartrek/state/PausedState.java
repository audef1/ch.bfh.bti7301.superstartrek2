package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Florian on 18.12.2016.
 */
public class PausedState extends State {

    private SubPanel mainPanel;
    private BorderLayout layout = new BorderLayout();

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Color fontColor;
    private Font titleFont;
    private Color titleColor;

    public PausedState(StateMachine stateMachine) {
        super(stateMachine);

        mainPanel = new SubPanel(this, GamePanel.WIDTH, GamePanel.HEIGHT);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
        getPanels().add(mainPanel);

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
    public void draw() {

        // set background
        mainPanel.getG().setBackground(Color.BLACK);

        // draw title
        mainPanel.getG().setColor(titleColor);
        mainPanel.getG().setFont(titleFont);
        mainPanel.getG().drawString("GAME PAUSED", 160, 250);
    }

    @Override
    public void enter() {
        getGamePanel().setLayout(layout);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void exit() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_P) {
            getStateMachine().change("game");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}