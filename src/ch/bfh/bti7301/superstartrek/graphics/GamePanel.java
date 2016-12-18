package ch.bfh.bti7301.superstartrek.graphics;

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

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int SCALE = 1;
    public static final int GAMESIZE = 1;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private int gamesize = 4;

    private StateMachine stateMachine;

    public GamePanel(int size)
    {
        super();
        this.gamesize = size;
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
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        stateMachine = new StateMachine();
        stateMachine.initStates(gamesize);

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
        stateMachine.draw(g);
    }

    private void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
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
        stateMachine.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key)
    {
        stateMachine.keyReleased(key.getKeyCode());
    }

    public int getGamesize() {
        return gamesize;
    }

}