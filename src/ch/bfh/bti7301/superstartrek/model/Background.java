package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by florianauderset on 27.12.16.
 */
public class Background {

    private BufferedImage background;

    private double x;
    private double y;

    private double dx = 0;
    private double dy = 0;

    private double moveScale;

    /**
     * The Background class is used to draw movable images to a Graphics2D instance.
     *
     * @param filename Filename of backgroundimage located in /images/Backgrounds
     * @param ms Multiplicator for background movement
     */
    public Background(String filename, double ms) {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Backgrounds/" + filename));
            moveScale = ms;
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* center background image */
        x = ((background.getWidth() / 2) - (GamePanel.WIDTH / 2 )) * -1;
        y = ((background.getHeight() / 2) - (GamePanel.HEIGHT / 2 )) * -1;
    }

    /**
     * Sets the background position
     *
     * @param x Position of the background on the x-axis
     * @param y Position of the background on the y-axis
     */
    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    /**
     * Sets the direction vector
     *
     * @param dx Horizontal direction of the background
     * @param dy Vertical direction of the background
     */
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Updates the background position
     */
    public void update() {
        if ((x > 0) && (x < 1024)){
            x += dy * -moveScale;
        }
        if ((y > 0) && (y < 1024)){
            y += dx * moveScale;
        }
    }

    /**
     * Updates the background position using the players data
     *
     * @param player StarFleetShip Object containing players direction and speed
     */
    public void update(StarFleetShip player) {
        if ((x > 0) || (x < 1024)){
            x += player.getDy() * -(player.getSpeed() * moveScale);
        }
        if ((y > 0) || (y < 1024)){
            y += player.getDx() * (player.getSpeed() * moveScale);
        }
    }

    /**
     * Draws the Background
     *
     * @param g The Graphics2D instance used to draw to the screen
     */
    public void draw(Graphics2D g) {
        g.drawImage(background, (int) x, (int) y, null);

        if (x < 0) {
            g.drawImage(background, (int) x + GamePanel.WIDTH, (int) y, null);
        }

        if (x > 0) {
            g.drawImage(background, (int) x - GamePanel.WIDTH, (int) y, null);
        }
    }

    /**
     * Returns the Background Image
     *
     * @return
     */
    public BufferedImage getBackground() {
        return background;
    }

    /**
     * Sets the Background Image
     *
     * @param background Background Image
     */
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    /**
     * Gets the Position on the X-Axis
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the Position on the X-Axis
     *
     * @param x Position of the background on the x-axis
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the Position on the Y-Axis
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the Position on the Y-Axis
     *
     * @param y Position of the background on the y-axis
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the X-Direction
     *
     * @return
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets the X-Direction
     *
     * @param dx Horizontal direction of the background
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Gets the Y-Direction
     *
     * @return
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the Y-Direction
     *
     * @param dy Vertical direction of the background
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Gets the MoveScale
     *
     * @return
     */
    public double getMoveScale() {
        return moveScale;
    }

    /**
     * Sets the MoveScale
     *
     * @param moveScale Multiplicator for background movement
     */
    public void setMoveScale(double moveScale) {
        this.moveScale = moveScale;
    }
}