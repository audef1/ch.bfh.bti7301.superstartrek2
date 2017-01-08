package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;
import java.util.ArrayList;

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

    @Override
    public void checkAttackCollisions(ArrayList<SpaceObject> spaceobjects) {

        // loop spaceobjects
        for(SpaceObject so : spaceobjects){

            // check collision
            if(intersects2(so)){
                int idx = getDx() * -1;
                int idy = getDy();

                int sodx = so.getDx() * -1;
                int sody = so.getDy();

                if (this instanceof StarFleetShip){
                    so.setDx(idx);
                    so.setDy(idy);
                    setSpeed(0);
                }
                else if (so instanceof Bullet){
                    int damage = ((Bullet) so).getDamage();
                    this.setHealth(this.getHealth()-damage);
                }
                else{
                    setDx(sodx);
                    setDy(sody);

                    so.setDx(idx);
                    so.setDy(idy);
                }
            }
        }
    }

}
