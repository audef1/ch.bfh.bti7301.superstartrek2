package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.*;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by florianauderset on 20.12.16.
 */

public class ShopState extends State {

    private ShopPanel shopPanel;
    private BorderLayout layout = new BorderLayout();

    private Level currentLevel;

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

        shopPanel = new ShopPanel(this, 1024, 680);

        //getPanels().add(infoPanel);
        getPanels().add(shopPanel);

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

    }

    /**
     * Handles entering the state
     */
    public void enter() {
        // get data from current level
        GameState gs = (GameState) getStateMachine().getStates().get("game");
        currentLevel = gs.getCurrentLevel();

        // TODO: for testing
        currentLevel.getCurrentquardant().setCleared(true);
        currentLevel.getCurrentquardant().setVisited(true);

        // set layout and add different panels
        getGamePanel().setLayout(layout);
        getGamePanel().add(shopPanel, BorderLayout.PAGE_START);
        //getGamePanel().add(mapPanel, BorderLayout.CENTER);

        // play backgroundmusic and toggle sound
        SoundBoard.BACKGROUND.play();
        SoundBoard.ERROR.play();

    }

    @Override
    public void exit() {
        SoundBoard.BACKGROUND.pause();
        SoundBoard.ERROR.play();
    }

    /**
     * Handles the user input
     * @param e KeyEvent object
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_M) {
            getStateMachine().change("game");
        }

        if (key == KeyEvent.VK_ESCAPE) {
            getStateMachine().change("menu");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }


}
