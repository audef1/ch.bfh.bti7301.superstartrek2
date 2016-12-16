package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by hoferfilip on 04.11.16.
 */
public class SpaceObject {

    private final int width;
    private final int height;

    private float speed;
    private int dx;
    private int dy;

    private float inerta;
    private float mass;

    public SpaceObject(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }

    public float getInerta() {
        return inerta;
    }

    public void setInerta(float inerta) {
        this.inerta = inerta;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void input() {

    }

    public void update() {

    }

    public Boolean intersects(SpaceObject spaceobject) {
        return true;
    }

    public void draw(){

    }

}