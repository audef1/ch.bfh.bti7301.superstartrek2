package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by florianauderset on 04.11.16.
 */
public class MenuBackground {

    private BufferedImage background;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public MenuBackground(String filename, double ms) {

        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
            moveScale = ms;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        x += dx;
        y += dy;

        // reset the backgroundposition if image is out of the screen
        if (Math.abs(x) == (background.getWidth()/2)){
            x = 0;
            y = 0;
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
}