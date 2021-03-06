package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.*;
import ch.bfh.bti7301.superstartrek.misc.*;
import ch.bfh.bti7301.superstartrek.misc.MsgCharacter;
import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

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
    private InfoPanel infoPanel;

    private MessageGenerator msgGenerator;

    private BorderLayout layout = new BorderLayout();
    private Level[][] levels;
    private Level currentLevel;

    private ArrayList<SpaceObject> spaceobjects;
    private ArrayList<Background> backgrounds = new ArrayList<Background>();
    private int score = 0;
    private StarFleetShip player;

    private BufferedImage background;
    private Boolean initialized = false;

    private LevelStateMachine lsm;

    private int msgTimer = 0;
    private int missionTimer = 0;

    private Boolean missionTold = false;

    /**
     * overloaded constructor
      * @param stateMachine StateMachine object
     */
    public GameState(StateMachine stateMachine) {

        super(stateMachine);
        lsm = new LevelStateMachine(this);

        mainPanel = new MainPanel(this, 640, 480);
        weaponPanel = new WeaponPanel(this, 192, 480);
        statusPanel = new StatusPanel(this, 192, 480);
        messagePanel = new MessagePanel(this, 1024, 200);
        infoPanel = new InfoPanel(this, 1024, 88);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        weaponPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        /* add panels */
        getPanels().add(mainPanel);
        getPanels().add(weaponPanel);
        getPanels().add(statusPanel);
        getPanels().add(messagePanel);
        getPanels().add(infoPanel);

        /* Initialize variables defined on top of the class */
        backgrounds.add(new Background("background_black.jpg", 0.1));
        backgrounds.add(new Background("background_blue.jpg", 0.1));
        backgrounds.add(new Background("background_purple.jpg", 0.1));
        backgrounds.add(new Background("background_darkpurple.jpg", 0.1));

        msgGenerator = new MessageGenerator();

        /* initialize game */
        initGame();
    }

    public void initGame(){

        initlevels(GamePanel.GAMESIZE);

        /* Initialize game objects */
        player = new StarFleetShip(98, 75, ((640 / 2) - (98 / 2)), 480 / 3 * 2, 1, 0, 0, 100, 100);

        /* initialize spaceobjects with meteors, enemies and spacestations */
        spaceobjects = currentLevel.getCurrentquardant().getSpaceobjects();
    }

    private void initlevels(int size) {
        levels = new LevelGenerator(size).getLevels();
        currentLevel = levels[0][0];
    }

    /**
     * Handles the input
     */
    public void input() {
        /* check input of all spaceobjects */
        player.input();

        for (SpaceObject so : spaceobjects) {
            so.input();
        }
    }

    /**
     * Updates the GameState
     */
    public void update() {

        /* check if mission has already told */
        if (!missionTold){
            if (missionTimer < 35*GamePanel.FPS){
                missionTimer++;
            } else{
                missionTold = true;
            }
        }

        /* check if mission goal is reached */
        Boolean gameover = true;
        Quadrant[][] quadrants = currentLevel.getQuadrants();
        for (int i = 0; i < quadrants.length; i++){
            for (int j = 0; j < quadrants[i].length; j++){
                 for (SpaceObject so : quadrants[i][j].getSpaceobjects()){
                     if (so instanceof EnemyShip){
                         gameover = false;
                     }
                 }
            }
        }

        if (gameover || player.isDead()){
            getStateMachine().change("gameover");
        }

        /* Check collisions and update position */
        player.update();
        player.checkAttackCollisions(spaceobjects);
        for(SpaceObject so: spaceobjects){
            if(so instanceof SpaceStation && player.intersects(so)){
                player.setHealth(player.getHealthMax());
                player.setShield(player.getShieldMax());
            }
        }

       for (int i = 0; i < spaceobjects.size(); i++){
           SpaceObject so = spaceobjects.get(i);
           so.checkAttackCollisions(spaceobjects);

            if (so instanceof EnemyShip) {
                ((EnemyShip) so).update(player);
                if(((EnemyShip) so).isDead()){
                    score += 100;
                    spaceobjects.add(new Explosion(so.getX(), so.getY()));
                    spaceobjects.remove(so);
                }

                if (missionTold){
                    if (msgTimer < 10)
                    {
                        msgGenerator.createMessage(MsgCharacter.KLINGON, MessageType.ALERT, 5, "bortaS bIr jablu'DI' reH\nQaQqu' nay'!");
                    }
                    msgTimer++;
                }
            } else if (so instanceof Meteor) {
                so.update();
            } else if (so instanceof Explosion) {
                so.update();
                if(so.shouldRemove()){
                    spaceobjects.remove(so);
                    i--;
                }
            }else {
                so.update();
            }
        }

        // check if levels user leaves quadrant
        // check if player leaves right
        if (player.getX() >= 640) {
            if (currentLevel.getCurrentquardant().getQuadrantnr() % GamePanel.GAMESIZE == 0) {
                msgGenerator.createMessage(MsgCharacter.SPOCK, MessageType.NORMAL, 3, "This is not part of our\nmission, Captain!");
                player.setSpeed(0);
                player.setX(580);
            } else {
                currentLevel.getCurrentquardant().setVisited(true);
                lsm.changeQuadrant(currentLevel.getQuadrantByNr(currentLevel.getCurrentquardant().getQuadrantnr() + 1));
                player.setX(0);

                msgTimer = 0;
            }
        }

        // check if player leaves left
        if (player.getX() < -20) {
            if (currentLevel.getCurrentquardant().getQuadrantnr() % GamePanel.GAMESIZE == 1) {
                msgGenerator.createMessage(MsgCharacter.SPOCK, MessageType.NORMAL, 3, "This is not part of our\nmission, Captain!");
                player.setSpeed(0);
                player.setX(20);
            } else {
                currentLevel.getCurrentquardant().setVisited(true);
                lsm.changeQuadrant(currentLevel.getQuadrantByNr(currentLevel.getCurrentquardant().getQuadrantnr() -1));
                player.setX(640);

                msgTimer = 0;
            }
        }

        // check if player leaves top
        if (player.getY() < -50) {
            if (currentLevel.getCurrentquardant().getQuadrantnr() <= GamePanel.GAMESIZE) {
                msgGenerator.createMessage(MsgCharacter.SPOCK, MessageType.NORMAL, 3, "This is not part of our\nmission, Captain!");
                player.setSpeed(0);
                player.setY(30);
            } else {
                currentLevel.getCurrentquardant().setVisited(true);
                lsm.changeQuadrant(currentLevel.getQuadrantByNr(currentLevel.getCurrentquardant().getQuadrantnr() - GamePanel.GAMESIZE));
                player.setY(479);

                msgTimer = 0;
            }
        }

        // check if player leaves bottom
        if (player.getY() >= 480) {
            if (currentLevel.getCurrentquardant().getQuadrantnr() > (GamePanel.GAMESIZE * (GamePanel.GAMESIZE -1))) {
                msgGenerator.createMessage(MsgCharacter.SPOCK, MessageType.NORMAL, 3, "This is not part of\nour mission, Captain!");
                player.setSpeed(0);
                player.setY(460);
            } else {
                currentLevel.getCurrentquardant().setVisited(true);
                lsm.changeQuadrant(currentLevel.getQuadrantByNr(currentLevel.getCurrentquardant().getQuadrantnr() + GamePanel.GAMESIZE));
                player.setY(0);

                msgTimer = 0;
            }
        }

        /* Update scores etc if necessary */


        // update backgrounds
        for (Background bg : backgrounds) {
            bg.update(player);
        }
    }

    /**
     * Create mission message
     */
    public void tellMission(){
        msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.NORMAL, 30, "Captain, we have to\nneutralize all Klingons in\nthe Galaxy " + currentLevel.getName() + "\nTake care of the Enterprise Kirk!");
    }

    /**
     * Handles the state entering
     */
    public void enter() {
        /* do stuff when entering this state */
        initialized = true;

        spaceobjects = currentLevel.getCurrentquardant().getSpaceobjects();

        layout.setVgap(0);
        getGamePanel().setLayout(layout);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);
        getGamePanel().add(statusPanel, BorderLayout.LINE_START);
        getGamePanel().add(weaponPanel, BorderLayout.LINE_END);
        getGamePanel().add(infoPanel, BorderLayout.PAGE_START);
        getGamePanel().add(messagePanel, BorderLayout.PAGE_END);

        SoundBoard.BACKGROUND.play();

        tellMission();
    }

    /**
     * Handles the exiting of the State
     */
    public void exit() {
        /* do stuff when exiting this state */
        SoundBoard.BACKGROUND.pause();
    }

    /**
     * Handles the user input
     * @param e KeyEvent
     */
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

        if (key == KeyEvent.VK_SPACE) {

            if(player.shieldUp()){
                msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.ALERT, 3, "Lower the shields to\nuse the Phaser, Captain!");
            }
            else {
                player.fire(0);
            }
        }

        if (key == KeyEvent.VK_S) {
            if (player.getShield() > 0){
                player.toggleShield();
                SoundBoard.BUTTON2.play();
                if (player.shieldUp()){
                    msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.NORMAL, 3, "Shields up!");
                }
                else{
                    msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.ALERT, 3, "Shields down!");
                }
            }
            else{
                msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.ALERT, 3, "Shields severely damaged,\nCaptain!");
            }

        }

        if (key == KeyEvent.VK_G) {

            if (player.getWeapons().get(1).getCapacity() > 0){
                if(player.shieldUp()){
                    msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.ALERT, 3, "We can't shoot through the\nshield, Captain!");
                }
                else {
                    player.fire(1);
                }
            }
            else{
                msgGenerator.createMessage(MsgCharacter.SCOTT, MessageType.NORMAL, 3, "We're out of grenades,\nCaptain!");
                SoundBoard.NOTIFICATION.play();
            }

        }

        if (key == KeyEvent.VK_UP) {
            player.speedUp();
        }

        if (key == KeyEvent.VK_DOWN) {
            player.slowDown();
        }

        if (key == KeyEvent.VK_LEFT) {
            player.turnLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
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

    public Level[][] getLevels() {
        return levels;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public ArrayList<SpaceObject> getSpaceobjects() {
        return spaceobjects;
    }

    public void setSpaceobjects(ArrayList<SpaceObject> spaceobjects) {
        this.spaceobjects = spaceobjects;
    }

    public ArrayList<Background> getBackgrounds() {
        return backgrounds;
    }

    public StarFleetShip getPlayer() {
        return player;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public Boolean isInitialized() {
        return initialized;
    }

    public MessageGenerator getMsg() {
        return msgGenerator;
    }

    public void setMsg(MessageGenerator msg) {
        this.msgGenerator = msg;
    }

}
