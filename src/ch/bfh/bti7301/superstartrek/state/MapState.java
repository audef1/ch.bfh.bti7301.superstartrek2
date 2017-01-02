package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.InfoPanel;
import ch.bfh.bti7301.superstartrek.graphics.MapPanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;
import ch.bfh.bti7301.superstartrek.model.Level;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

    @Override
    public void enter() {
        // get data from current level
        GameState gs = (GameState) getStateMachine().getStates().get("game");
        currentLevel = gs.getCurrentLevel();

        // TODO: for testing
        currentLevel.getCurrentquardant().setCleared(true);
        currentLevel.getCurrentquardant().setVisited(true);

        // set layout and add different panels
        getGamePanel().setLayout(layout);
        getGamePanel().add(infoPanel, BorderLayout.PAGE_START);
        getGamePanel().add(mapPanel, BorderLayout.CENTER);
    }

    @Override
    public void exit() {

    }

    @Override
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
