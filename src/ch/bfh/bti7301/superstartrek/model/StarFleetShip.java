package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by filip on 02.12.2016.
 */
public class StarFleetShip extends SpaceShip {


    public StarFleetShip(int width, int height, int x, int y, int dx, int dy, double speed){
        super(width, height, x, y, dx, dy, speed);
        weapons.add(new GrenadeLauncher());

        this.getSprite("images/PNG/playerShip3_green.png");
    }

}
