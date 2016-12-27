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


    public Meteor(int width, int height, int x, int y, int dx, int dy, double speed) {

        // TODO: different meteortypes according its sprite
        //super(width, height, x, y, dx, dy, speed);
        super(89, 82, x, y, dx, dy, speed);

        setSprites(new ArrayList<BufferedImage[]>());
        BufferedImage sprite;
        sprite = loadSprite( (int) (Math.random() * (8 - 1) + 1));
        BufferedImage[] bi = new BufferedImage[1];
        bi[0] = sprite;
        sprites.add(bi);
    }

    public void draw(Graphics2D g) {
        //do some stuff that has to be done before drawing
        g.drawImage(
                sprites.get(0)[0],
                (int) x,
                (int) y,
                null
        );
    }

    /**
     * Loads a random Meteor
     * @param randomNumber Based on this number, a different image will be loaded
     * @return loaded image
     */
    private BufferedImage loadSprite(int randomNumber) {
        try {
            BufferedImage sprite;
            switch (randomNumber){
                case 1:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big1.png").getFile()));
                    break;
                case 2:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big2.png").getFile()));
                    break;
                case 3:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big3.png").getFile()));
                    break;
                case 4:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big4.png").getFile()));
                    break;
                case 5:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big1.png").getFile()));
                    break;
                case 6:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big2.png").getFile()));
                    break;
                case 7:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big3.png").getFile()));
                    break;
                case 8:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorGrey_big4.png").getFile()));
                    break;
                default:
                    sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big3.png").getFile()));
                    break;
            }

            return sprite;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
