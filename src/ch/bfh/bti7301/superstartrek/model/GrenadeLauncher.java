package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by Florian on 03.01.2017.
 */
public class GrenadeLauncher extends Weapon {

    /**
     * Pretty manually  - only the player has a grenade launcher
     */
    public GrenadeLauncher(){
        setName("GrenadeLauncher");
        setCapacity(10);
        setMaxCapacity(10);
        setDamage(30);
        setAreaOfDamage(50);
        setSpeed(6);
    }

    /**
     * fires the grenade
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dx x-direction
     * @param dy y-direction
     * @return bullet with the given parameters
     */
    public Bullet fire(double x, double y, int dx, int dy){
        setCapacity(Math.max(getCapacity()-1,0));
        return new Bullet(getWidth(), getHeight(), x, y, dx, dy, getDamage(), getAreaOfDamage(), getTimeToExplode(), getSpeed(), "laserBlue01.png");
    }

}