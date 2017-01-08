package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.misc.MessageGenerator;
import ch.bfh.bti7301.superstartrek.misc.MessageType;
import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Florian on 31.12.2016.
 * Bottom partial of the Cockpit HUD View.
 */
public class MessagePanel extends SubPanel {

    /**
     * Class Constructor
     *
     * @param state  The current state of the state machine.
     * @param width  Width of the Panel.
     * @param height Height of the Panel.
     */
    public MessagePanel(State state, int width, int height) {
        super(state, width, height);
        setBackground(new Background("cockpit_bottom.jpg", 0));
        getB().setPosition(0, 0);
    }

    /**
     * Draw to buffered image.
     */
    @Override
    public void draw() {
        Graphics2D g = getG();
        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        StarFleetShip p = gs.getPlayer();
        ArrayList<SpaceObject> spaceobjects = gs.getSpaceobjects();

        /* draw background */
        getB().draw(getG());

        /* draw speed */
        int speed = (int) p.getSpeed();
        int maxSpeed = (int) p.getMaxSpeed();
        int height = 115;
        int singleHeight = height / maxSpeed;

        for (int i = 0; i < maxSpeed; i++) {

            if (speed > i) {
                g.setColor(new Color(153, 0, 0));
                g.fillRect(684, 153 - (i * singleHeight), 26, singleHeight);
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(4));
                g.drawRect(684, 153 - (i * singleHeight), 26, singleHeight);
            }
        }

        /* draw minimap */
        int mapWidth = 233;
        int mapHeight = 145;
        int xMapStart = 760;
        int yMapStart = 25;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.GREEN);
        g.drawRect(xMapStart, yMapStart, mapWidth, mapHeight);

        for (SpaceObject so : spaceobjects) {
            if (so instanceof EnemyShip) {
                if (xMapStart + (int) (so.getX() / 2.66) >= xMapStart && xMapStart + (int) (so.getX() / 2.66) <= xMapStart + mapWidth && yMapStart + (int) (so.getY() / 3.3) >= yMapStart && yMapStart + (int) (so.getY() / 3.3) <= yMapStart + mapHeight - so.getHeight()) {
                    g.setColor(Color.RED);
                    g.fillOval(xMapStart + (int) (so.getX() / 2.66), yMapStart + (int) (so.getY() / 3.3), 10, 10);
                }
            }

            if (so instanceof SpaceStation) {
                g.setColor(Color.GREEN);
                g.fillOval(xMapStart + (int) (so.getX() / 2.66), yMapStart + (int) (so.getY() / 3.3), 15, 15);
            }
        }

        MessageGenerator m = gs.getMsg();

        m.update();
        drawMessage(m.msg, m.img, m.messageType);
    }

    /**
     * Draw the message and picture onto the panel.
     *
     * @param message The to displayed message.
     * @param image   Whose picture should be presented.
     */
    private void drawMessage(String message, BufferedImage image, MessageType msgType) {

        Graphics2D g = getG();

        /* draw messageTerminal */
        int messageWidth = 405;
        int messageHeight = 130;
        int xMessageStart = 200;
        int yMessageStart = 30;

        g.setStroke(new BasicStroke(3));

        switch (msgType) {
            case ALERT:
                g.setColor(Color.RED);
                break;
            case NORMAL:
                g.setColor(Color.GREEN);
                break;
            default:
                g.setColor(Color.GREEN);
                break;
        }

        g.drawRect(xMessageStart, yMessageStart, messageWidth, messageHeight);

        /* draw picture box */
        int pictureWidth = 130;
        int pictureHeight = 130;
        int xPictureStart = 55;
        int yPictureStart = 30;

        g.setStroke(new BasicStroke(3));

        switch (msgType) {
            case ALERT:
                g.setColor(Color.RED);
                break;
            case NORMAL:
                g.setColor(Color.GREEN);
                break;
            default:
                g.setColor(Color.GREEN);
                break;
        }
        g.drawRect(xPictureStart, yPictureStart, pictureWidth, pictureHeight);

        int fontSize = 20;

        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.WHITE);

        /* draw messages with linebreaks */
        drawString(g, message, 210, 30);

        g.drawImage(image, 56, 31, this);

    }

    /**
     * Draw strings with linebreaks.
     *
     * @param g    Graphics2D instance.
     * @param text Message to draw.
     * @param x    Startposition of X.
     * @param y    Starposition of y.
     */
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}