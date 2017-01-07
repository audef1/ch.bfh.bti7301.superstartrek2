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
        BufferedImage sprite = loadSprite((int) (Math.random() * (8 - 1) + 1));
        setCwidth(sprite.getWidth());
        setCheight(sprite.getHeight());
        BufferedImage[] bi = new BufferedImage[1];
        bi[0] = sprite;
        sprites.add(bi);

        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    public void draw(Graphics2D g){
        //do some stuff that has to be done before drawing
        g.drawImage(
                sprites.get(0)[0],
                (int) x,
                (int) y,
                null
        );

        if(isDebug){
            g.drawRect((int)x,(int) y, getCwidth(), getCheight());
        }
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
}
