package ch.bfh.bti7301.superstartrek.model;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;

/**
 * Created by filip on 02.12.2016.
 */
public class Bullet extends SpaceObject{

    private int damage;
    private float areaOfDamage;
    private int timeToExplode = 0;
    private long shotMicroTime = 0;

    public Bullet(int width, int height, double x, double y, int dx, int dy){
        super(width, height,x,y,dx,dy, 10);
        this.getSprite("images/PNG/Lasers/laserBlue01.png");
    }

    public Bullet(int width, int height, double x, double y, int dx, int dy, int damage, float areaOfDamage, int timeToExplode, int speed, String filename ){
        super(width, height,x,y,dx,dy,speed);
        setDamage(damage);
        shotMicroTime = System.currentTimeMillis();
        setAreaOfDamage(areaOfDamage);
        setTimeToExplode(timeToExplode);
        this.getSprite("images/PNG/Lasers/"+filename);
    }

    public void update(){
        if(shotMicroTime > 0 && System.currentTimeMillis() - shotMicroTime <= timeToExplode){
            explode();
        }

        super.update();
    }

    private void explode(){

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getAreaOfDamage() {
        return areaOfDamage;
    }

    public void setAreaOfDamage(float areaOfDamage) {
        this.areaOfDamage = areaOfDamage;
    }

    public int getTimeToExplode() {
        return timeToExplode;
    }

    public void setTimeToExplode(int timeToExplode) {
        this.timeToExplode = timeToExplode;
    }
}
