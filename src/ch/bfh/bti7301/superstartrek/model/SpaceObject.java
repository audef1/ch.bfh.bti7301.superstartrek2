package ch.bfh.bti7301.superstartrek.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hoferfilip on 04.11.16.
 */
public class SpaceObject {

    // dimensions
    private final int width;
    private final int height;

    // collisionbox dimensions
    protected int cwidth;
    protected int cheight;

    // position and direction
    protected double x;
    protected double y;
    protected int dx = 0;
    protected int dy = 1;

    protected float inerta;
    protected double speed;
    protected float maxSpeed;
    protected float mass;

    // sprites
    protected ArrayList<BufferedImage[]> sprites = new ArrayList<BufferedImage[]>();

    public SpaceObject(int width, int height) {
            this.width = width;
        this.height = height;
    }

    protected void getSprite(String path){
        try {
            BufferedImage sprite = ImageIO.read(new File(getClass().getClassLoader().getResource(path).getFile()));
            BufferedImage[] bi = new BufferedImage[1];
            bi[0] = sprite;
            sprites.add(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SpaceObject(int width, int height, double x, double y, int dx, int dy, double speed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    public void input() {

    }

    public void update() {
        double penalty = 1d/Math.sqrt(dx*dx+dy*dy);

        // set new position
        x += (dy*speed)*penalty;
        y -= (dx*speed)*penalty;
    }

    public boolean intersects(SpaceObject spaceobject) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = spaceobject.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
    }

    public void draw(Graphics2D g){
        double rangle = Math.toDegrees(Math.atan2(dy, dx));
        AffineTransform transform = new AffineTransform();
        transform.translate(x,y);
        transform.rotate((rangle/180)*Math.PI, sprites.get(0)[0].getWidth()/2, sprites.get(0)[0].getHeight()/2);

        g.drawImage(
                sprites.get(0)[0],
                transform,
                null
        );
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCwidth() {
        return cwidth;
    }

    public void setCwidth(int cwidth) {
        this.cwidth = cwidth;
    }

    public int getCheight() {
        return cheight;
    }

    public void setCheight(int cheight) {
        this.cheight = cheight;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public float getInerta() {
        return inerta;
    }

    public void setInerta(float inerta) {
        this.inerta = inerta;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public ArrayList<BufferedImage[]> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<BufferedImage[]> sprites) {
        this.sprites = sprites;
    }

}