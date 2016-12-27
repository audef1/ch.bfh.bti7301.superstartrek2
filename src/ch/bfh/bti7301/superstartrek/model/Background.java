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

    public Background(String filename, double ms) {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Backgrounds/" + filename));
            moveScale = ms;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // position background in the center of the viewpoint
        x = ((background.getWidth() / 2) - (GamePanel.WIDTH / 2 )) * -1;
        y = ((background.getHeight() / 2) - (GamePanel.HEIGHT / 2 )) * -1;
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        if ((x > 0) || (x < 1024)){
            x += dy * -moveScale;
        }
        if ((y > 0) || (y < 1024)){
            y += dx * moveScale;
        }
    }

    public void update(SpaceObject so) {
        if ((x > 0) || (x < 1024)){
            x += so.getDy() * -moveScale;
        }
        if ((y > 0) || (y < 1024)){
            y += so.getDx() * moveScale;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(background, (int) x, (int) y, null);

        if (x < 0) {
            g.drawImage(background, (int) x + GamePanel.WIDTH, (int) y, null);
        }

        if (x > 0) {
            g.drawImage(background, (int) x - GamePanel.WIDTH, (int) y, null);
        }
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getMoveScale() {
        return moveScale;
    }

    public void setMoveScale(double moveScale) {
        this.moveScale = moveScale;
    }
}