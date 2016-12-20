package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.misc.LevelGenerator;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.SpaceObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

// import spaceobjects


/**
 * Created by florianauderset on 02.12.16.
 */

public class GameState implements State {

    private final StateMachine statemachine;
    private Level[][] levels;
    private Level currentLevel;
    private ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
    private int score = 0;

    private BufferedImage background;

    /* private variables - ex. score */

    public GameState(StateMachine statemachine) {

        this.statemachine = statemachine;
        initlevels(GamePanel.GAMESIZE);

        /* Initialize variables defined on top of the class */
        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Backgrounds/background_black.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* Initialize game objects */
    }

    private void initlevels(int size) {
        levels = new LevelGenerator(size).getLevels();
        currentLevel = levels[0][0];
    }

    @Override
    public void input() {
        /* check input of all spaceobjects - specificspaceobject.input() */
        for(SpaceObject so : spaceobjects){
            so.input();
        }
    }

    @Override
    public void update() {
        /* Update position */
        for(SpaceObject so : spaceobjects){
            //check collisions - so.intersects(everyotherpossiblespaceobject);
        }
        /* Check for collisions */
        for(SpaceObject so : spaceobjects){
            //check collisions - so.intersects(everyotherpossiblespaceobject);
        }

        /* Update scores etc if necessary */

    }

    @Override
    public void draw(Graphics2D g) {

        g.drawImage(background, 0, 0, null);

        /* draw all specific spaceobjects */
        for (SpaceObject so : spaceobjects) {
            //draw elements - so.draw(g);
        }
        System.out.println("game running... - rendering...");
    }

    @Override
    public void enter() {
        /* do stuff when entering this state */

    }

    @Override
    public void exit() {
        /* do stuff when exiting this state */
    }

    @Override
    public void keyPressed(int k) {
        /* do something with the input */

        if (k == KeyEvent.VK_P) {
           statemachine.change("paused");
        }

        if (k == KeyEvent.VK_M) {
            statemachine.change("map");
        }
    }

    @Override
    public void keyReleased(int k) {
        /* do something with the input */
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}