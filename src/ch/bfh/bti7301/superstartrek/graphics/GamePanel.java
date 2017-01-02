package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.misc.SpaceObjectFactory;
import ch.bfh.bti7301.superstartrek.state.StateMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by florianauderset on 16.12.16.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int SCALE = 1;
    public static final int GAMESIZE = 4;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private StateMachine stateMachine;

    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify()
    {
        super.addNotify();
        if (thread == null)
        {
            thread = new Thread(this);
            addKeyListener (this);
            thread.start();
        }
    }

    private void init()
    {
        stateMachine = new StateMachine(this);
        stateMachine.initStates(GAMESIZE);

        /* Initializing done, set running to true */
        running = true;
    }

    public void run()
    {
        init();
        gameLoop();
    }

    private void update()
    {
        stateMachine.update();
    }

    private void draw()
    {
        stateMachine.draw();
    }

    private void drawToScreen()
    {
        stateMachine.drawToScreen();
    }

    /**
     * The game loop.
     */
    private void gameLoop() {

        long start;
        long elapsed;
        long wait;

        while(running)
        {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();

            // revalidate so that every part of the layout is updated
            revalidate();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if(wait < 0)
            {
                wait = 5;
            }
            try
            {
                Thread.sleep(wait);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void keyTyped(KeyEvent key)
    {

    }

    public void keyPressed(KeyEvent key)
    {
        stateMachine.keyPressed(key);
    }

    public void keyReleased(KeyEvent key)
    {
        stateMachine.keyReleased(key);
    }

}