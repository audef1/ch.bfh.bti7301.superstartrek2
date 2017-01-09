package ch.bfh.bti7301.superstartrek.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by florianauderset on 21.12.16.
 */
public class Meteor extends SpaceObject {

    boolean isDebug;

    public Meteor(int width, int height, int x, int y, int dx, int dy, double speed) {

        super(width, height, x, y, dx, dy, speed);

        setSprites(new ArrayList<BufferedImage[]>());
        int random = (int) (Math.random() * (8 - 1) + 1);
        BufferedImage sprite = loadSprite(random);
        this.setPolygon(random);
        setCwidth(sprite.getWidth());
        setCheight(sprite.getHeight());
        BufferedImage[] bi = new BufferedImage[1];
        bi[0] = sprite;
        sprites.add(bi);

        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    /**
     * Loads random meteor sprite
     * @param randomNumber which meteor sprite should be loaded
     * @return loaded sprite
     */
    private BufferedImage loadSprite(int randomNumber){
        BufferedImage sprite = null;

        switch (randomNumber) {
            case 1:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big1.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big2.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big3.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big4.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big1.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big2.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 7:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big3.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 8:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big4.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big3.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return sprite;
    }

    /**
     * Sets the Collisionbox to the shape of the image.
     * Polygon has to be generated manually.
     */
    public void setPolygon(int randomNumber){

        switch (randomNumber) {
            case 1:
                int xPoly1[] = {0,18,72,100,86,58,26};
                int yPoly1[] = {48,0,0,38,72,68,82};
                shape = new Polygon(xPoly1, yPoly1, xPoly1.length);
                break;
            case 2:
                int xPoly2[] = {0,18,68,118,106,54,34,4};
                int yPoly2[] = {42,6,0,18,66,80,96,74};
                shape = new Polygon(xPoly2, yPoly2, xPoly2.length);
                break;
            case 3:
                int xPoly3[] = {2,34,72,86,62,18,0};
                int yPoly3[] = {18,0,8,40,80,76,54};
                shape = new Polygon(xPoly3, yPoly3, xPoly3.length);
                break;
            case 4:
                int xPoly4[] = {0,14,64,96,76,26};
                int yPoly4[] = {54,10,0,34,88,92};
                shape = new Polygon(xPoly4, yPoly4, xPoly4.length);
                break;
            case 5:
                int xPoly5[] = {0,18,72,100,86,58,26};
                int yPoly5[] = {48,0,0,38,72,68,82};
                shape = new Polygon(xPoly5, yPoly5, xPoly5.length);
                break;
            case 6:
                int xPoly6[] = {0,18,68,118,106,54,34,4};
                int yPoly6[] = {42,6,0,18,66,80,96,74};
                shape = new Polygon(xPoly6, yPoly6, xPoly6.length);
                break;
            case 7:
                int xPoly7[] = {2,34,72,86,62,18,0};
                int yPoly7[] = {18,0,8,40,80,76,54};
                shape = new Polygon(xPoly7, yPoly7, xPoly7.length);
                break;
            case 8:
                int xPoly8[] = {0,14,64,96,76,26};
                int yPoly8[] = {54,10,0,34,88,92};
                shape = new Polygon(xPoly8, yPoly8, xPoly8.length);
                break;
            default:
                int xPoly[] = {0,18,72,100,86,58,26};
                int yPoly[] = {48,0,0,38,72,68,82};
                shape = new Polygon(xPoly, yPoly, xPoly.length);
                break;
        }
    }

}
