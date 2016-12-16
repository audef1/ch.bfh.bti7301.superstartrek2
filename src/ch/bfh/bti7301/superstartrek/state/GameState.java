package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.misc.LevelGenerator;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.SpaceObject;

import java.awt.*;
import java.util.ArrayList;

// import spaceobjects


/**
 * Created by florianauderset on 02.12.16.
 */

public class GameState implements State {

    private Level[][] levels;
    private Level currentLevel;
    private ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
    private int score = 0;

    /* private variables - ex. score */

    public GameState(int size) {

        initlevels(size);

        /* Initialize variables defined on top of the class */

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
        /* draw all specific spaceobjects */
        for (SpaceObject so : spaceobjects) {
            //draw elements - so.draw();
        }
        System.out.println("rendering...");
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