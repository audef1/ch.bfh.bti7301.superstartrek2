package ch.bfh.bti7301.superstartrek.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.FlatteningPathIterator;
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
    protected Shape shape;

    // position and direction
    protected double x;
    protected double y;
    protected int dx = 0;
    protected int dy = 1;

    protected float inerta;
    protected double speed;
    protected int maxSpeed;
    protected float mass;

    protected Boolean remove = false;

    // sprites
    protected ArrayList<BufferedImage[]> sprites = new ArrayList<BufferedImage[]>();

    boolean isDebug;

    public SpaceObject(int width, int height){
        this.width = width;
        this.height = height;
        this.cwidth = width;
        this.cheight = height;

        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    public SpaceObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.cwidth = width;
        this.cheight = height;
        this.x = x;
        this.y = y;

        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    public SpaceObject(int width, int height, double x, double y, int dx, int dy, double speed) {
        this.width = width;
        this.height = height;
        this.cwidth = width;
        this.cheight = height;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;

        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    protected void getSprite(String path){
        try {
            BufferedImage sprite = ImageIO.read(new File(getClass().getClassLoader().getResource(path).getFile()));
            BufferedImage[] bi = new BufferedImage[1];
            bi[0] = sprite;
            sprites.add(bi);
            setPolygon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {

    }

    public void update() {
        double penalty = 1d/Math.sqrt(dx*dx+dy*dy);

        // set new position
        x += (dy*speed)*penalty;
        y -= (dx*speed)*penalty;
    }

    /**
     * Method to check if two SpaceObjects collide
     * @param spaceobject
     * @return
     */
    public boolean intersects(SpaceObject spaceobject) {
        if (this != spaceobject){
            Area areaA = new Area(getPolygon());
            areaA.intersect(new Area(spaceobject.getPolygon()));
            return !areaA.isEmpty();
        }
        else{
            return false;
        }
    }

    public void setPolygon(){
        int xPoly[] = {0,width,width, 0};
        int yPoly[] = {0,0,height,height};
        shape = new Polygon(xPoly, yPoly, xPoly.length);
    }

    public Shape getPolygon() {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }

        double rangle = Math.toDegrees(Math.atan2(dy, dx));
        AffineTransform transform = new AffineTransform();
        transform.translate(x,y);
        transform.rotate((rangle/180)*Math.PI, sprites.get(0)[0].getWidth()/2, sprites.get(0)[0].getHeight()/2);
        return transform.createTransformedShape(shape);
    }

    public void draw(Graphics2D g){
        double rangle = Math.toDegrees(Math.atan2(dy, dx));
        AffineTransform transform = new AffineTransform();
        transform.translate(x,y);
        transform.rotate((rangle/180)*Math.PI, sprites.get(0)[0].getWidth()/2, sprites.get(0)[0].getHeight()/2);

        // check if game has been started in debug mode
        if(isDebug){
            g.fill(getPolygon()); // translated shape collisionbox
        }

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

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
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

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Boolean shouldRemove() {
        return remove;
    }

    public void remove() {
        remove = true;
    }

    public void checkAttackCollisions(ArrayList<SpaceObject> spaceobjects) {
        /* standardprocedure for spaceobjects
        *  loop spaceobjects
        */
        for(SpaceObject so : spaceobjects){
            // check collision
            if(intersects(so)){
                int idx = getDx() * -1;
                int idy = getDy();

                int sodx = so.getDx() * -1;
                int sody = so.getDy();

                setDx(sodx);
                setDy(sody);

                so.setDx(idx);
                so.setDy(idy);
            }

            /* check if hit by a bullet
            *  remove bullet, do nothing else
            */
            if (so instanceof SpaceShip){
                for (int i = 0; i < ((SpaceShip) so).getFiredBullets().size(); i++){
                    if (intersects(((SpaceShip) so).getFiredBullets().get(i))){
                        ((SpaceShip) so).getFiredBullets().remove(i);
                        i--;
                    }
                }
            }
        }

    }
}