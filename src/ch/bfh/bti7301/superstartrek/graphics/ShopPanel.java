package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.MapState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Filip on 09.01.2017.
 * Map View.
 */
public class ShopPanel extends SubPanel {

    /**
     * Class Constructor
     *
     * @param state  The current state of the state machine.
     * @param width  Width of the Panel.
     * @param height Height of the Panel.
     */
    public ShopPanel(State state, int width, int height) {
        super(state, width, height);
        setBackground(new Background("cockpit_map.jpg", 0));
        getB().setPosition(0, 0);
    }

    /**
     * Draw to buffered image.
     */
    @Override
    public void draw() {
        Graphics2D g = getG();
        // draw background
        getB().draw(getG());

        // draw quadrant info
        Font font = new Font("Arial", Font.PLAIN, 20);

        g.setFont(font);
        g.setColor(new Color(238,221,130));

        g.fillRect(51, 235, 426, 140);
        g.setColor(new Color(230, 230, 230));
        g.setStroke(new BasicStroke(4));
        g.drawRect(51, 235, 426, 140);
        /* draw other stuff here */

    }
}