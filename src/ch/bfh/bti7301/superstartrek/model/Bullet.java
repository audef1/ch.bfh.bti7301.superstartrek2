package ch.bfh.bti7301.superstartrek.model;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by filip on 02.12.2016.
 */
public class Bullet extends SpaceObject{

    private int damage;
    private float areaOfDamage;
    private int timeToExplode = 0;
    private long shotMicroTime = 0;

    /**
     * The Bullet Class is used by Weapons. Makes damage on intersection with enemies
     * First Constructor is for Bullets wich donn't explode.
     * @param width
     * @param height
     * @param x
     * @param y
     * @param dx
     * @param dy
     */
    public Bullet(int width, int height, double x, double y, int dx, int dy){
        super(9, 54,x,y,dx,dy, 10);
        this.getSprite("images/PNG/Lasers/laserBlue01.png");
    }

    /**
     * The second Constructor is for Exploding weapons
     * @param width
     * @param height
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param damage
     * @param areaOfDamage
     * @param timeToExplode
     * @param speed
     * @param filename
     */
    public Bullet(int width, int height, double x, double y, int dx, int dy, int damage, float areaOfDamage, int timeToExplode, int speed, String filename ){
        super(width, height,x,y,dx,dy,speed);
        setDamage(damage);
        shotMicroTime = System.currentTimeMillis();
        setAreaOfDamage(areaOfDamage);
        setTimeToExplode(timeToExplode);
        this.getSprite("images/PNG/Lasers/"+filename);
    }

    /**
     * check if bulled should explode
     * call update from spaceObject
     */
    public void update(){
        if(shotMicroTime > 0 && System.currentTimeMillis() - shotMicroTime <= timeToExplode){
            explode();
        }

        super.update();
    }

    @Override
    public void checkAttackCollisions(ArrayList<SpaceObject> spaceobjects){

        // loop spaceobjects
        for(SpaceObject so : spaceobjects){

            // check collision
            if(intersects(so)){
                if(so instanceof SpaceShip){
                    System.out.println(this.getDamage());
                    ((SpaceShip) so).shipTakesDamage(this.getDamage());
                    this.remove();
                }
                else if (so instanceof Meteor){
                    this.remove();
                }
            }
        }
    }

    /**
     * Sets the Collisionbox to the shape of the image.
     * Polygon has to be generated manually.
     */
    @Override
    public void setPolygon(){
        shape = new Rectangle(0,0,getWidth(), getHeight());
    }

    /**
     * Makes damage in radius = areaOfDamage
     */
    private void explode(){

    }

    /**
     * Returns the damage of the Bullet
     * @return
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the Damage of the Bullet
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Return the Area of Damage (radius)
     * @return
     */
    public float getAreaOfDamage() {
        return areaOfDamage;
    }

    /**
     * Sets the Area of Damage (radius)
     * @param areaOfDamage
     */
    public void setAreaOfDamage(float areaOfDamage) {
        this.areaOfDamage = areaOfDamage;
    }

    /**
     * Returns the time to explode (Timer starts with consstruction of class)
     * @return
     */
    public int getTimeToExplode() {
        return timeToExplode;
    }

    /**
     * Sets the time to explode (Timer starts with consstruction of class)
     * @param timeToExplode
     */
    public void setTimeToExplode(int timeToExplode) {
        this.timeToExplode = timeToExplode;
    }
}
