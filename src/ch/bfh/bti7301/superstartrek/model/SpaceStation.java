package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by filip on 04.11.2016.
 */
public class SpaceStation extends SpaceObject {

    private int fuelCost;
    private int repairCost;

    private int upgradeWeaponMultiplier = 3;
    private int upgradeFuelMultiplier = 2;
    private int upgradeShieldMultiplier = 2;
    private int upgradeHealthMultiplier = 2;

    public SpaceStation(Color color, Texture texture, float x, float y, float speed, int width, int height, int tx, int ty){
        super(color, texture,x,y,speed,width,height,tx,ty);
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(int fuelCost) {
        this.fuelCost = fuelCost;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public boolean canAffordRepair(SpaceShip ship){
        return ((ship.getMoney() >= this.getRepairCostForSpaceShip(ship)));
    }

    public boolean canAffordRefuel(SpaceShip ship){
        return ((ship.getMoney() >= this.getFuelCostForSpaceShip(ship)));
    }

    public boolean canAffordUpgradeWeaponCost(SpaceShip ship, Weapon weapon){
        return ((ship.getMoney() >= this.getUpgradeWeaponCost(weapon)));
    }

    public boolean canAffordUpgradeHealthCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeHealthCost(ship)));
    }

    public boolean canAffordUpgradeShieldCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeShieldCost(ship)));
    }

    public boolean canAffordUpgradeFuelCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeFuelCost(ship)));
    }

    public int getFuelCostForSpaceShip(SpaceShip ship){
        return (ship.getMaxFuel() - ship.getFuel()) * this.fuelCost;
    }

    public int getRepairCostForSpaceShip(SpaceShip ship){
        return ship.shipHasDamage() * this.repairCost;
    }

    public int getUpgradeWeaponCost(Weapon weapon){
        return weapon.getDamage() * this.upgradeWeaponMultiplier;
    }

    public int getUpgradeFuelCost(SpaceShip ship){
        return ship.getMaxFuel() * this.upgradeFuelMultiplier;
    }

    public int getUpgradeShieldCost(SpaceShip ship){
        return ship.getHealthMax() * this.upgradeShieldMultiplier;
    }

    public int getUpgradeHealthCost(SpaceShip ship){
        return ship.getHealthMax() * this.upgradeHealthMultiplier;
    }

    public void refuel(SpaceShip ship){
        if(this.canAffordRefuel(ship)){
            ship.setMoney(ship.getMoney() - this.getFuelCostForSpaceShip(ship));
            ship.setFuel(ship.getMaxFuel());
        }
    }

    public void repair(SpaceShip ship){
        if(this.canAffordRepair(ship)){
            ship.setMoney(ship.getMoney() - this.getRepairCostForSpaceShip(ship));
            ship.setHealth(ship.getHealthMax());
            ship.setShield(ship.getShieldMax());
        }
    }

    public void upgradeShield(SpaceShip ship){
        if(this.canAffordUpgradeShieldCost(ship)){
            ship.setMoney(ship.getMoney() - this.getUpgradeShieldCost(ship));
            ship.setShieldMax(ship.getShieldMax() + 5);
        }
    }

    public void upgradeFuel(SpaceShip  ship){
        if(this.canAffordUpgradeFuelCost(ship)){
            ship.setMoney(ship.getMoney() - this.getUpgradeFuelCost(ship));
            ship.setMaxFuel(ship.getMaxFuel() + 5);
        }
    }

    public void upgradeWeapon(SpaceShip ship, Weapon weapon){
        if(this.canAffordUpgradeWeaponCost(ship, weapon)){
            ship.setMoney(ship.getMoney() - this.getUpgradeWeaponCost(weapon));
            weapon.setDamage(weapon.getDamage()+2);
        }
    }

    public void upgradeHealth(SpaceShip ship){
        if(ship.getMoney() >= ship.getHealthMax()*this.upgradeHealthMultiplier){
            ship.setHealthMax(ship.getHealthMax() + 5);
        }
    }


}
