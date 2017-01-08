package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Florian on 30.12.2016.
 * The base panel for each other panel within the GamePanel.
 */
public class SubPanel extends JPanel {

    /**
     * Width of the Panel.
     */
    private int width;

    /**
     * Height of the Panel.
     */
    private int height;

    /**
     * The current state of the state machine.
     */
    private State state;

    /**
     * The background image.
     */
    private Background background;

    /**
     * The buffered image on which is gonna be drawn.
     */
    private BufferedImage image;

    /**
     * The graphics2D instance.
     */
    private Graphics2D g;

    /**
     * Class Constructor
     *
     * @param state  The current state of the state machine.
     * @param width  Width of the Panel.
     * @param height Height of the Panel.
     */
    public SubPanel(State state, int width, int height) {
        this.state = state;
        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Draw to buffered image. (Override in Subclasses)
     */
    public void draw() {
        state.draw();
    }

    /**
     * Draw the composed buffered image to the screen.
     */
    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
    }

    /**
     * Gets the graphic2D instance.
     *
     * @return
     */
    public Graphics2D getG() {
        return g;
    }

    /**
     * Gets the background instance.
     *
     * @return
     */
    public Background getB() {
        return background;
    }


    /**
     * Sets the background.
     *
     * @param background Background instance.
     */
    public void setBackground(Background background) {
        this.background = background;
    }

    /**
     * Gets the state.
     *
     * @return
     */
    public State getState() {
        return state;
    }

}