package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by florianauderset on 20.12.16.
 */

public class MapState implements State {

    private final StateMachine statemachine;

    private Font font;
    private Color fontColor;
    private Font titleFont;
    private Color titleColor;

    public MapState(StateMachine statemachine) {

        this.statemachine = statemachine;

        // add star trek font
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getClassLoader().getResource("fonts/finalfrontierold.ttf").getFile())));
        } catch (IOException |FontFormatException e) {
            e.printStackTrace();
        }

        // init menu fonts and colors
        titleColor = new Color(238,221,130);
        titleFont = new Font("Final Frontier Old Style", Font.PLAIN, 75);

        fontColor = new Color(255,255,255);
        font = new Font("Arial", Font.PLAIN, 30);

    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        // set background
        g.setBackground(Color.BLACK);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("MAP", 20, 75);
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_M) {
            statemachine.change("game");
        }
    }

    @Override
    public void keyReleased(int k) {

    }

}
