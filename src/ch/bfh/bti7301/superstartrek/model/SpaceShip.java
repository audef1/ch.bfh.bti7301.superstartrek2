package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.misc.MessageGenerator;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by filip on 04.11.2016.
 */
public class SpaceShip extends SpaceObject {

    protected ArrayList<Bullet> firedBullets = new ArrayList<>(25);
    protected ArrayList<Point> directions;
    protected ArrayList<Weapon> weapons;
    protected int directionPointer = 0;
    protected long phaserDelay = System.currentTimeMillis();
    protected String name;
    protected int shield;
    protected int shieldMax;
    protected int health;
    protected int healthMax;
    protected int selfrepair;
    protected Boolean warp;
    protected int fuel;
    protected int maxFuel;
    protected int money;
    protected Boolean dead = false;
    protected Boolean shieldup = false;

    /**
     * SpaceShip - parent class for starFleetShip and EnemyShip.
     *
     * @param width
     * @param height
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param speed
     * @param health
     * @param shield
     */
    public SpaceShip(int width, int height, double x, double y, int dx, int dy, double speed, int health, int shield){
        super(width, height, x, y, dx, dy, speed);

        // init weapons
        weapons = new ArrayList<Weapon>();
        weapons.add(new Phaser());

        // init directions
        directions = new ArrayList<>();
        directions.add(new Point(1,0));
        directions.add(new Point(1, 1));
        directions.add(new Point(0, 1));
        directions.add(new Point(-1, 1));
        directions.add(new Point(-1, 0));
        directions.add(new Point(-1, -1));
        directions.add(new Point(0, -1));
        directions.add(new Point(1, -1));
        setMaxSpeed(5);
        setHealth(health);
        setHealthMax(health);
        setShield(shield);
        setShieldMax(shield);
    }

    /**
     * Adds a new Bullet to firedBullets, if the weapon has capacity > 0
     * @param index
     */
    public synchronized void fire(int index){
        if(weapons.get(index).getCapacity() > 0 && System.currentTimeMillis() - this.phaserDelay >= 300) {
            firedBullets.add(weapons.get(index).fire(this.x + (sprites.get(0)[0].getHeight()/2), this.y, this.dx, this.dy));
            if(this instanceof StarFleetShip){
                SoundBoard.LASER.play();
            }
            phaserDelay = System.currentTimeMillis();
        }
    }

    /**
     * Draw function - also draws the Bullets, because they won't be drawn
     * by the game itself
     * @param g
     */
    public void draw(Graphics2D g){
        super.draw(g);
        firedBullets.forEach(Bullet -> Bullet.draw(g));
    }

    /**
     * update function - also updates the Bullets, because they won't be updated
     */
    public void update(){
        super.update();

        for (int i = 0; i < firedBullets.size(); i++){
            firedBullets.get(i).update();
        }

        if (health == 0){
            dead = true;
        }

        if (shield == 0){
            shieldup = false;
        }

        /*if(!firedBullets.isEmpty()){
            for(Bullet bullet : firedBullets){
                if(bullet.x > 400 || bullet.y > 400){
                    firedBullets.remove(bullet);
                }
            }
        }*/

    }

    /**
     * Sets the direction of the Ship 45° to the left
     */
    public void turnLeft(){
        if (directionPointer == 0)
            directionPointer = directions.size() -1;
        else
            directionPointer --;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());

    }

    @Override
    public void checkAttackCollisions(ArrayList<SpaceObject> spaceobjects){
        // loop spaceobjects
        for(SpaceObject so : spaceobjects){

            // check collision
            if(intersects(so)){
                int idx = getDx() * -1;
                int idy = getDy();

                so.setDx(idx);
                so.setDy(idy);
                setSpeed(0);

                if (so instanceof Bullet && !firedBullets.contains(so)){
                    this.shipTakesDamage(((Bullet)so).getDamage());
                }
            }

            /* check if hit by a bullet */
            if (so instanceof SpaceShip){
                for (int i = 0; i < ((SpaceShip) so).getFiredBullets().size(); i++){
                    if (intersects(((SpaceShip) so).getFiredBullets().get(i)) && System.currentTimeMillis() - ((SpaceShip) so).getFiredBullets().get(i).getShotMicroTime() > 100){
                        this.shipTakesDamage(((SpaceShip) so).getFiredBullets().get(i).getDamage());
                        //((SpaceShip) so).getFiredBullets().get(i).remove();
                        ((SpaceShip) so).getFiredBullets().remove(i);
                        i--;
                    }
                }
            }

        }

        // check if my bullets hit someone else
        for (int i = 0; i < firedBullets.size(); i++){
            if (!firedBullets.get(i).remove){
                firedBullets.get(i).checkAttackCollisions(spaceobjects);
            }else{
                firedBullets.remove(i);
                i--;
            }
        }
    }

    /**
     *
     */
    public void turnRight(){
        if (directionPointer == directions.size() -1)
            directionPointer = 0;
        else
            directionPointer ++;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());

    }

    public void speedUp() {
        this.setSpeed(Math.min(this.getSpeed()+1,this.maxSpeed));
    }

    public void slowDown() {
        this.setSpeed(Math.max(this.getSpeed()-1, 0));
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getShieldMax() {
        return shieldMax;
    }

    public void setShieldMax(int shieldMax) {
        this.shieldMax = shieldMax;
    }

    public Boolean shieldUp() {
        return shieldup;
    }

    public void toggleShield() {
        shieldup = (!shieldup);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getSelfrepair() {
        return selfrepair;
    }

    public void setSelfrepair(int selfrepair) {
        this.selfrepair = selfrepair;
    }

    public boolean isWarp() {
        return warp;
    }

    public void setWarp(boolean warp) {
        this.warp = warp;
    }

    public void shipTakesDamage(int damage){

        if (shieldup){
            this.shield -= (this.shield >= damage) ? damage : 0;
        }
        else{
            this.health -= (this.health >= damage) ? damage : 0;
        }

        if(this.health <= 0){
            this.remove();
        }
    }

    public int shipHasDamage(){
        return (this.healthMax - this.health) + (this.shieldMax - this.shield);
    }

    public Boolean isDead() {
        return dead;
    }

    public void setDead(Boolean dead){
        this.dead = dead;
    }

    public ArrayList<Bullet> getFiredBullets() {
        return firedBullets;
    }



}