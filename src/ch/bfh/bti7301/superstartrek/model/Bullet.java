package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by filip on 02.12.2016.
 */
public class Bullet extends SpaceObject{

    private int damage;
    private float areaOfDamage;
    int timeToExplode = 0;

    public Bullet(Color color, Texture texture, int width, int height, int tx, int ty, int speed, int damage, float areaOfDamage, int timeToExplode){
        super(color, texture, width, height, speed, width, height, tx, ty);

        this.damage = damage;
        this.areaOfDamage = areaOfDamage;
        this.timeToExplode = timeToExplode;
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
