package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.InfoPanel;
import ch.bfh.bti7301.superstartrek.graphics.MapPanel;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by florianauderset on 20.12.16.
 */

public class MapState extends State {

    private InfoPanel infoPanel;
    private MapPanel mapPanel;
    private BorderLayout layout = new BorderLayout();

    private Level currentLevel;

    private Font font;
    private Color fontColor;
    private Font titleFont;
    private Color titleColor;

    /**
     * overloaded constructor
     * @param stateMachine
     */
    public MapState(StateMachine stateMachine) {

        super(stateMachine);

        infoPanel = new InfoPanel(this, 1024, 88);
        mapPanel = new MapPanel(this, 1024, 680);

        getPanels().add(infoPanel);
        getPanels().add(mapPanel);

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

    }

    /**
     * Handles the state entering
     */
    public void enter() {
        // get data from current level
        GameState gs = (GameState) getStateMachine().getStates().get("game");
        currentLevel = gs.getCurrentLevel();

        // set layout and add different panels
        getGamePanel().setLayout(layout);
        getGamePanel().add(infoPanel, BorderLayout.PAGE_START);
        getGamePanel().add(mapPanel, BorderLayout.CENTER);

        // play backgroundmusic and toggle sound
        SoundBoard.BACKGROUND.play();
        SoundBoard.ERROR.play();

    }

    /**
     * Handles the state exiting
     */
    public void exit() {
        SoundBoard.BACKGROUND.pause();
        SoundBoard.ERROR.play();
    }

    /**
     * Handles the user input
     * @param e KeyEvent
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

    /**
     * Handles what's happening when a key is released
     * @param e KeyEvent
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
