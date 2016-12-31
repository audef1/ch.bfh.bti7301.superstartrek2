package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.*;
import ch.bfh.bti7301.superstartrek.misc.LevelGenerator;
import ch.bfh.bti7301.superstartrek.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by florianauderset on 02.12.16.
 */

public class GameState extends State {

    private SubPanel mainPanel;
    private WeaponPanel weaponPanel;
    private StatusPanel statusPanel;
    private MessagePanel messagePanel;
    private InfoPanel levelinfoPanel;

    private BorderLayout layout = new BorderLayout();
    private Level[][] levels;
    private Level currentLevel;
    private ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
    private ArrayList<Background> backgrounds = new ArrayList<Background>();
    private int score = 0;
    private StarFleetShip player;

    private BufferedImage background;

    /* private variables - ex. score */

    public GameState(StateMachine stateMachine) {

        super(stateMachine);

        mainPanel = new SubPanel(this, 640, 480);
        weaponPanel = new WeaponPanel(this, 192, 480);
        statusPanel = new StatusPanel(this, 192, 480);
        messagePanel = new MessagePanel(this, 1024, 200);
        levelinfoPanel = new InfoPanel(this, 1024, 88);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        weaponPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        levelinfoPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        getPanels().add(mainPanel);
        getPanels().add(weaponPanel);
        getPanels().add(statusPanel);
        getPanels().add(messagePanel);
        getPanels().add(levelinfoPanel);

        initlevels(GamePanel.GAMESIZE);

        /* Initialize variables defined on top of the class */
        backgrounds.add(new Background("background_black.jpg", 0.4));
        backgrounds.add(new Background("background_blue.jpg", 0.4));
        backgrounds.add(new Background("background_purple.jpg", 0.4));
        backgrounds.add(new Background("background_darkpurple.jpg", 0.4));

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

       /* addKeyListener(new TAdapter());*/
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
        /* Check colliosions and update position */
        for(SpaceObject so : spaceobjects){
            //so.intersects(everyotherpossiblespaceobject);
            if(so instanceof EnemyShip){
                //so.update(player);
                so.update();
            }else{
                so.update();
            }
        }

        /* Update scores etc if necessary */
        // update backgrounds
        for (Background bg : backgrounds){
            bg.update(player);
        }
    }

    @Override
    public void draw() {

        /* draw level background */
        backgrounds.get(currentLevel.getCurrentquardant().getQuadrantnr() % 4).draw(mainPanel.getG());

        /* draw all specific spaceobjects */
        for (SpaceObject so : spaceobjects) {
            so.draw(mainPanel.getG());
        }
        //System.out.println("game running... - rendering...");
    }

    @Override
    public void enter() {
        /* do stuff when entering this state */
        layout.setVgap(0);
        getGamePanel().setLayout(layout);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
        getGamePanel().add(statusPanel, BorderLayout.LINE_START);
        getGamePanel().add(weaponPanel, BorderLayout.LINE_END);
        getGamePanel().add(levelinfoPanel, BorderLayout.PAGE_START);
        getGamePanel().add(messagePanel, BorderLayout.PAGE_END);
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
           getStateMachine().change("paused");
        }

        if (key == KeyEvent.VK_M) {
            getStateMachine().change("map");
        }

        if (key == KeyEvent.VK_ESCAPE) {
            getStateMachine().change("menu");
        }

        if (key == KeyEvent.VK_SPACE){
            player.fire();
        }

        if (key == KeyEvent.VK_UP){
            player.speedUp();
        }

        if (key == KeyEvent.VK_DOWN){
            player.slowDown();
        }

        if (key == KeyEvent.VK_LEFT){
            player.turnLeft();
        }

        if (key == KeyEvent.VK_RIGHT){
            player.turnRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}