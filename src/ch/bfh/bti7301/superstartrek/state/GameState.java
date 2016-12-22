package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.misc.LevelGenerator;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.Meteor;
import ch.bfh.bti7301.superstartrek.model.SpaceObject;
import ch.bfh.bti7301.superstartrek.model.StarFleetShip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Created by florianauderset on 02.12.16.
 */

public class GameState implements State, ActionListener {

    private final StateMachine statemachine;
    private Level[][] levels;
    private Level currentLevel;
    private ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
    private int score = 0;
    private StarFleetShip player;

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
        player = new StarFleetShip(30,30,1,1,1,1,1);

        // TODO: initialize spaceobjects with meteors, enemies and spacestations
        Meteor m1 = new Meteor(10,10,50,100,-1, 1, 0.1);
        Meteor m2 = new Meteor(10,10,100,200,1, -1, 0.2);
        Meteor m3 = new Meteor(10,10,150,250,1, 0, 0.3);

        spaceobjects.add(m1);
        spaceobjects.add(m2);
        spaceobjects.add(m3);
        spaceobjects.add(player);

        addKeyListener(new TAdapter());
    }

    public void actionPerformed(ActionEvent e) {

        player.move();
        update();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            this.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            this.keyPressed(e);
        }
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
            so.update();
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
            so.draw(g);
        }
        //System.out.println("game running... - rendering...");
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
    public void keyPressed(KeyEvent e) {
        /* do something with the input */

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_P) {
           statemachine.change("paused");
        }

        if (key == KeyEvent.VK_M) {
            statemachine.change("map");
        }

        if (key == KeyEvent.VK_ESCAPE) {
            statemachine.change("menu");
        }

        if (key == KeyEvent.VK_SPACE){
            player.fire();
        }

        if (key == KeyEvent.VK_UP){
            player.dy = -1;
        }

        if (key == KeyEvent.VK_DOWN){
            player.dy = 1;
        }

        if (key == KeyEvent.VK_LEFT){
            player.dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT){
            player.dx = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            player.dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            player.dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            player.dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            player.dy = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}