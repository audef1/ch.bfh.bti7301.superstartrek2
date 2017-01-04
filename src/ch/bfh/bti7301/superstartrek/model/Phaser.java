package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by Florian on 03.01.2017.
 */
public class Phaser extends Weapon {

    public Phaser(){
        setName("Phaser");
        setCapacity(99);
        setMaxCapacity(99);
        setDamage(10);
        setAreaOfDamage(15);
        setSpeed(10);
    }

    @Override
    public Bullet fire(double x, double y, int dx, int dy){
        return new Bullet(getWidth(), getHeight(), x, y, dx, dy, getDamage(), getAreaOfDamage(), getTimeToExplode(), getSpeed(), "laserBlue01.png");
    }

}