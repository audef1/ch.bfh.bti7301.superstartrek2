package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by filip on 02.12.2016.
 */
public class Bullet extends SpaceObject{

    private int damage;
    private float areaOfDamage;
    int timeToExplode = 0;

    public Bullet(int width, int height){
        super(width, height);
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
