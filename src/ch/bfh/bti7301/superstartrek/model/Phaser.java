package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

/**
 * Created by Florian on 03.01.2017.
 */
public class Phaser extends Weapon {

    /**
     * default constructor
     */
    public Phaser(){
        setName("Phaser");
        setCapacity(99);
        setMaxCapacity(99);
        setDamage(10);
        setAreaOfDamage(15);
        setSpeed(10);
        setWidth(9);
        setHeight(54);
    }

    /**
     * fires the phaser
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dx x-direction
     * @param dy y-direction
     * @return bullet with given parameters
     */
    public Bullet fire(double x, double y, int dx, int dy){
        SoundBoard.LASER.play();
        return new Bullet(getWidth(), getHeight(), x, y, dx, dy, getDamage(), getAreaOfDamage(), getTimeToExplode(), getSpeed(), "laserBlue01.png");
    }

}