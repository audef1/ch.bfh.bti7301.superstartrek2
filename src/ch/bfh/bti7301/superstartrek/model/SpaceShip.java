package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by filip on 04.11.2016.
 */
public class SpaceShip extends SpaceObject {

    protected ArrayList<Bullet> firedBullets = new ArrayList<>();
    protected ArrayList<Point> directions;
    protected int directionPointer = 0;
    protected String name;
    protected int shield;
    protected int shieldMax;
    protected int health;
    protected int healthMax;
    protected int selfrepair;
    protected boolean warp;
    protected int fuel;
    protected int maxFuel;
    protected int money;

    public SpaceShip(int width, int height, double x, double y, int dx, int dy, double speed){
        super(width, height, x, y, dx, dy, speed);

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
        setHealth(60);
        setHealthMax(100);
        setShield(30);
        setShieldMax(100);

    }

    public void fire(){
        firedBullets.add(new Bullet(10,10,this.x,this.y,this.dx,this.dy));
        System.out.println("peng");
    }

    public void draw(Graphics2D g){
        super.draw(g);
        firedBullets.forEach(Bullet -> Bullet.draw(g));
    }
    public void update(){
        super.update();
        firedBullets.forEach(Bullet -> Bullet.update());

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

    private ArrayList<Weapon> weapons;

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
    }

    public int shipHasDamage(){
        return (this.healthMax - this.health) + (this.shieldMax - this.shield);
    }

}
