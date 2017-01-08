package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;

/**
 * Created by filip on 02.12.2016.
 */
public class StarFleetShip extends SpaceShip {


    public StarFleetShip(int width, int height, int x, int y, int dx, int dy, double speed){
        super(width, height, x, y, dx, dy, speed);
        weapons.add(new GrenadeLauncher());

        this.getSprite("images/PNG/playerShip3_green.png");
        this.setPolygon();
    }

    /**
     * Sets the Collisionbox to the shape of the image.
     * Polygon has to be generated manually.
     */
    @Override
    public void setPolygon(){
        int xPoly[] = {0,38,44,52,58,96,94,70,60,34,26,0};
        int yPoly[] = {56,14,0,0,14,56,64,64,74,74,62,64};
        shape = new Polygon(xPoly, yPoly, xPoly.length);
    }

}
