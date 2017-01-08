package ch.bfh.bti7301.superstartrek.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Florian on 08.01.2017.
 */
public class Explosion extends SpaceObject {

    private int explosiontimer = 0;
    private Boolean toDelete = false;

    public Explosion(double x, double y) {

        super(90, 90, x, y, 0, 0, 0);

        setSprites(new ArrayList<BufferedImage[]>());
        try {
            BufferedImage sprite0 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion0.png").getFile()));
            BufferedImage sprite1 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion1.png").getFile()));
            BufferedImage sprite2 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion2.png").getFile()));
            BufferedImage sprite3 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion3.png").getFile()));
            BufferedImage sprite4 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion4.png").getFile()));
            BufferedImage sprite5 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion5.png").getFile()));
            BufferedImage sprite6 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion6.png").getFile()));
            BufferedImage sprite7 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion7.png").getFile()));
            BufferedImage sprite8 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion8.png").getFile()));
            BufferedImage sprite9 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion9.png").getFile()));
            BufferedImage sprite10 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion10.png").getFile()));
            BufferedImage sprite11 = ImageIO.read(new File(getClass().getClassLoader().getResource("/images/PNC/Explosion/explosion11.png").getFile()));
            BufferedImage[] bi = new BufferedImage[12];
            bi[0] = sprite0;
            bi[1] = sprite1;
            bi[2] = sprite2;
            bi[3] = sprite3;
            bi[4] = sprite4;
            bi[5] = sprite5;
            bi[6] = sprite6;
            bi[7] = sprite7;
            bi[8] = sprite8;
            bi[9] = sprite9;
            bi[10] = sprite10;
            bi[11] = sprite11;
            sprites.add(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(){
        /* update the timer */
        explosiontimer++;

        if (explosiontimer > sprites.get(0).length * 7){
            toDelete = true;
        }
    }

    @Override
    public void draw(Graphics2D g){
        g.drawImage(getSprites().get(0)[explosiontimer/7], (int) x, (int) y, null);
    }

    public Boolean toDelete() {
        return toDelete;
    }

}
