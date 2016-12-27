package ch.bfh.bti7301.superstartrek.model;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;

/**
 * Created by filip on 02.12.2016.
 */
public class Bullet extends SpaceObject{

    private int damage;
    private float areaOfDamage;
    int timeToExplode = 0;

    public Bullet(int width, int height, double x, double y, int dx, int dy ){
        super(width, height,x,y,dx,dy,10);
        this.getSprite("images/PNG/Lasers/laserBlue01.png");
    }

    public void update(){
        super.update();
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
