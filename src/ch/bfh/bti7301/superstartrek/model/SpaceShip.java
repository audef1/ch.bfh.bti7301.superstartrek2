package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.misc.MessageGenerator;

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

    public void fire(int index){
        if(weapons.get(index).getCapacity() > 0) {
            //firedBullets.add(weapons.get(index).fire(this.x + sprites.get(0)[0].getWidth()* this.dx / 2, this.y + sprites.get(0)[0].getHeight() * this.dy / 2, this.dx, this.dy));
            firedBullets.add(weapons.get(index).fire(this.x + (sprites.get(0)[0].getHeight()/2) * this.dy, this.y + (sprites.get(0)[0].getWidth()/2) * this.dx, this.dx, this.dy));
        }
    }

    public void draw(Graphics2D g){
        super.draw(g);
        firedBullets.forEach(Bullet -> Bullet.draw(g));
    }

    public void update(){
        super.update();
        firedBullets.forEach(Bullet -> Bullet.update());

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

                if (so instanceof Bullet){
                    this.shipTakesDamage(((Bullet)so).getDamage());
                }
            }

            /* check if hit by a bullet */
            if (so instanceof SpaceShip){
                for (int i = 0; i < ((SpaceShip) so).getFiredBullets().size(); i++){
                    if (intersects(((SpaceShip) so).getFiredBullets().get(i))){
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
        this.health = Math.max(this.health - (damage - shield), 0);

        this.shield -= (this.shield >= damage) ? damage : this.shield;

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

    public ArrayList<Bullet> getFiredBullets() {
        return firedBullets;
    }



}