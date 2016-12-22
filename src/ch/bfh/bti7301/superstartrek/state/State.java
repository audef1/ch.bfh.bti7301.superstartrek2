package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by buche on 01.12.2016.
 */
public interface State {
    /**
     * Handles input of the state.
     */
    public void input();

    /**
     * Updates the state
     */
    public void update();

    /**
     * Draws the elements in the state
     */
    public void draw(Graphics2D g);

    /**
     * Gets executed when entering the state, useful for initialization.
     */
    public void enter();

    /**
     * Gets executed when leaving the state, useful for disposing.
     */
    public void exit();

    /**
     * Gets the pressed keys
     */
    public void keyPressed(KeyEvent e);

    /**
     * Gets the released keys
     */
    public void keyReleased(KeyEvent e);
}
