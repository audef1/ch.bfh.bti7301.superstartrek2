package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    protected ArrayList<BufferedImage[]> sprites;

    public SpaceObject(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SpaceObject(int width, int height, int x, int y, int dx, int dy, double speed) {
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

        // TODO: calculate new position
        x += dx*speed;
        y += dy*speed;

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
        // TODO: write a generic draw method
    }

    /*
    public void calculateCorners(double x, double y) {
        int leftTile = (int) (x - cwidth / 2) / tileSize;
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2) / tileSize;
        int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;
        if(topTile < 0 || bottomTile >= tileMap.getNumRows() ||
                leftTile < 0 || rightTile >= tileMap.getNumCols()) {
            topLeft = topRight = bottomLeft = bottomRight = false;
            return;
        }

        topLeft = tileMap.isBlocking(topTile, leftTile);
        topRight = tileMap.isBlocking(topTile, rightTile);
        bottomLeft = tileMap.isBlocking(bottomTile, leftTile);
        bottomRight = tileMap.isBlocking(bottomTile, rightTile);
    }


    public void draw(Graphics2D g){
        //do some stuff that has to be done before drawing
        g.drawImage(
            animation.getImage(),
                (int) (x + xmap - width / 2),
                (int) (y + ymap - height / 2),
                null
        );
    }

    */

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

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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