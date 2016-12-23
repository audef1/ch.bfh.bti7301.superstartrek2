package ch.bfh.bti7301.superstartrek.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by filip on 02.12.2016.
 */
public class StarFleetShip extends SpaceShip {


    private ArrayList<Point> directions;
    private int directionPointer = 0;

    public StarFleetShip(int width, int height, int x, int y, int dx, int dy, double speed){
        super(width, height, x, y, dx, dy, speed);

        // init directions
        directions = new ArrayList<>();
        directions.add(new Point(0, 1));
        directions.add(new Point(-1, 1));
        directions.add(new Point(-1, 0));
        directions.add(new Point(-1, -1));
        directions.add(new Point(0, -1));
        directions.add(new Point(1, -1));
        directions.add(new Point(1, 0));
        directions.add(new Point(1, 1));

        try {
            BufferedImage sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/playerShip3_green.png").getFile()));
            BufferedImage[] bi = new BufferedImage[1];
            bi[0] = sprite;
            sprites.add(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fire(){
        System.out.println("peng");
    }

    public void turnLeft(){
        if (directionPointer == 0)
            directionPointer = directions.size() -1;
        else
            directionPointer --;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());
        System.out.println("turning left... x: " + this.getDx() + " y: " + this.getDy());

        // rotate sprite according direction
        rotate();
    }

    public void turnRight(){
        if (directionPointer == directions.size() -1)
            directionPointer = 0;
        else
            directionPointer ++;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());
        System.out.println("turning right... x: " + this.getDx() + " y: " + this.getDy());

        // rotate sprite according direction
        rotate();
    }

    private void rotate(){
        // rotate sprite according direction
        try {
            // always read original sprite to not wash out the image while transforming
            BufferedImage sprite = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PNG/playerShip3_green.png").getFile()));
            BufferedImage[] bi = new BufferedImage[1];
            bi[0] = rotateSprite(sprite, dx, dy);
            sprites.set(0, bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage rotateSprite(BufferedImage sprite, int dx, int dy){

        // calcuate rotation angle from direction
        double rangle = Math.toDegrees(Math.atan2(dy, dx)) + 90; // +90 because our sprite is facing upwards in the file

        // rotate the given bufferedimage
        AffineTransform transform = new AffineTransform();
        transform.rotate(rangle, sprite.getWidth()/2, sprite.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(sprite, null);
    }

    public void speedUp() {
        // TODO: implement acceleration and maxspeed
        this.setSpeed(this.getSpeed()+0.01);
        System.out.println("speeding up...");
    }

    public void slowDown() {
        // TODO: implement breaking
        this.setSpeed(this.getSpeed()-0.01);
        System.out.println("slowing down...");

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
