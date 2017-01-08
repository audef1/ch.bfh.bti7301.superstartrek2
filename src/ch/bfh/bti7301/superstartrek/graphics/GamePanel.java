package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;
import ch.bfh.bti7301.superstartrek.state.StateMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by florianauderset on 16.12.16.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    /**
     * The width of the GamePanel
     */
    public static final int WIDTH = 1024;

    /**
     * The height of the GamePanel
     */
    public static final int HEIGHT = 768;

    /**
     * The scale of the gameobjects (mostly not used)
     */
    public static final int SCALE = 1;

    /**
     * The gamesize -> 4 = 4*4 Levels with 4*4 Quadrants each
     */
    public static final int GAMESIZE = 4;
    /**
     * Frames per Seconds
     */
    public static final int FPS = 60;
    /**
     * The gamethread
     */
    private Thread thread;
    /**
     * Game running...
     */
    private boolean running;
    /**
     * Updateinterval for FPS / second
     */
    private long targetTime = 1000 / FPS;

    /**
     * The game state machine
     */
    private StateMachine stateMachine;

    /**
     * The Gamepanel provides the game loop.
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();

        // initialize all sounds
        SoundBoard.init();
    }

    /**
     * Create / start thread if not done already, notify JPanel.
     */
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    /**
     * Initialize game and set it to running.
     */
    private void init() {
        stateMachine = new StateMachine(this);
        stateMachine.initStates(GAMESIZE);

        /* Initializing done, set running to true */
        running = true;
    }

    /**
     * Initialize and start game loop when starting thread.
     */
    public void run() {
        init();
        gameLoop();
    }

    /**
     * Update all the things in the state machine.
     */
    private void update() {
        stateMachine.update();
    }

    /**
     * Draw all the things in the state machine.
     */
    private void draw() {
        stateMachine.draw();
    }

    /**
     * Draw all the things in the state machine to the screen.
     */
    private void drawToScreen() {
        stateMachine.drawToScreen();
    }

    /**
     * The game loop.
     */
    private void gameLoop() {

        long start;
        long elapsed;
        long wait;

        while (running) {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();

            // revalidate so that every part of the layout is updated
            revalidate();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0) {
                wait = 5;
            }
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle keyboard inputs.
     */
    public void keyTyped(KeyEvent key) {
    }

    /**
     * Handle keyboard inputs.
     */
    public void keyPressed(KeyEvent key) {
        stateMachine.keyPressed(key);
    }

    /**
     * Handle keyboard inputs.
     */
    public void keyReleased(KeyEvent key) {
        stateMachine.keyReleased(key);
    }

}