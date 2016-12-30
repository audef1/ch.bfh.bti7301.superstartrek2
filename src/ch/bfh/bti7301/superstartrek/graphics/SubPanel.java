package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Florian on 30.12.2016.
 */
public class SubPanel extends JPanel {

    private int width;
    private int height;

    private State state;
    private BufferedImage image;
    private Graphics2D g;

    public SubPanel(State state, int width, int height){
        this.state = state;
        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        setPreferredSize(new Dimension(width, height));
    }

    public void draw()
    {
        state.draw();
    }

    public void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
    }

    public Graphics2D getG() {
        return g;
    }

}
