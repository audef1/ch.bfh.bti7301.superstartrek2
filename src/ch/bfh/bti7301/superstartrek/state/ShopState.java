package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by filip on 07.01.2017.
 */
public class ShopState extends State {
    private SubPanel mainPanel;
    private BorderLayout layout = new BorderLayout();

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Color fontColor;
    private Font titleFont;
    private Color titleColor;

    /**
     * overloaded constructor
     * @param stateMachine StateMachine object
     */
    public ShopState(StateMachine stateMachine) {
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

    /**
     * Draws the font
     */
    public void draw() {

        // set background
        Graphics2D g = mainPanel.getG();
        g.setBackground(Color.BLACK);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        String paused = "GAME PAUSED";
        g.drawString(paused, ((GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(paused)/2)), ((GamePanel.HEIGHT/2)-(font.getSize()/2)));
    }

    /**
     * Handles entering the state
     */
    public void enter() {
        getGamePanel().setLayout(layout);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void exit() {

    }

    /**
     * Handles the user input
     * @param e KeyEvent object
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_P) {
            getStateMachine().change("game");
        }

        if (key == KeyEvent.VK_ESCAPE) {
            getStateMachine().change("menu");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
