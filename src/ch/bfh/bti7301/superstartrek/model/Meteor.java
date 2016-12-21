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


    public Meteor(int width, int height, int x, int y, int dx, int dy, double speed){

        // TODO: different meteortypes according its sprite
        //super(width, height, x, y, dx, dy, speed);
        super(89, 82, x, y, dx, dy, speed);

        setSprites(new ArrayList<BufferedImage[]>());
        try {
            BufferedImage sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/Meteors/meteorBrown_big3.png").getFile()));
            BufferedImage[] bi = new BufferedImage[1];
            bi[0] = sprite;
            sprites.add(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g){
        //do some stuff that has to be done before drawing
        g.drawImage(
                sprites.get(0)[0],
                (int) x,
                (int) y,
                null
        );
    }
}
