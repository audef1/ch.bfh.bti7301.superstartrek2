package ch.bfh.bti7301.superstartrek.model;

import java.util.ArrayList;

/**
 * Created by filip on 04.11.2016.
 */
public class SpaceShip extends SpaceObject {

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

    public SpaceShip(int width, int height){
        super(width, height);
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
        this.health = this.health - (damage - shield);

        this.shield -= (this.shield >= damage) ? damage : this.shield;
    }

    public int shipHasDamage(){
        return (this.healthMax - this.health) + (this.shieldMax - this.shield);
    }

}
