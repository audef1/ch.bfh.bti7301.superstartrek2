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
    protected double dx;
    protected double dy;

    private float inerta;
    private float speed;
    private float maxSpeed;
    private float mass;

    // sprites
    private ArrayList<BufferedImage[]> sprites;

    public SpaceObject(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void input() {

    }

    public void update() {

    }

    public boolean intersects(SpaceObject spaceobject) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = spaceobject.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
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

}