package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;

/**
 * Created by filip on 02.12.2016.
 */
public class EnemyShip extends SpaceShip {

    private long lastShot = System.currentTimeMillis();
    private long lastSpeedUp = System.currentTimeMillis();
    private int timeToNextShot = 1000;

    /**
     * Generated Ships which are able to attack the player.
     * @param width Ship-width
     * @param height Ship-height
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dx x-direction
     * @param dy y-direction
     * @param speed ships moving speed
     * @param health how much health does the enemy have
     * @param shield how much shield does the enemy have
     */
    public EnemyShip(int width, int height, double x, double y, int dx, int dy, double speed, int health, int shield){
        super(width, height, x, y, dx, dy, speed, health, shield);
        this.getSprite("images/PNG/playerShip2_red.png");
    }

    /**
     * check where the enemy (Player) is.
     * then call parent update
     * @param player
     */
    public void update(StarFleetShip player){
        this.detectEnemyDirection(player.getX(), player.getY());
        super.update();
    }

    /**
     * Sets the Collisionbox to the shape of the image.
     * Polygon has to be generated manually.
     */
    @Override
    public void setPolygon(){
        int xPoly[] = {0,46,52,60,64,110,92,72,68,46,40,38,16};
        int yPoly[] = {42,16,0,0,16,42,70,66,74,74,62,66,72};
        shape = new Polygon(xPoly, yPoly, xPoly.length);
    }

    /**
     * check where the enemy is. the ship rotates to this direction.
     * @param enemyX
     * @param enemyY
     */
    public void detectEnemyDirection(double enemyX, double enemyY){
        double diffX = this.x - enemyX;
        double diffY = this.y - enemyY;

        double distance = Math.sqrt((diffX)*(diffX) + (diffY)*(diffY));

        if(distance <= 500){
            double rangle = Math.toDegrees(Math.atan2(diffY, diffX));

            if(rangle >= -22.5 && rangle < 22.5){
                this.directionPointer = 6;
            }else if(rangle >= 22.5 && rangle < 67.5){
                this.directionPointer = 7;
            }else if(rangle >= 67.5 && rangle < 112.5){
                this.directionPointer = 0;
            }else if(rangle >= 112.5 && rangle < 157.5){
                this.directionPointer = 1;
            }else if((rangle >= 157.5 && rangle <= 180) || (rangle < -157.5 && rangle >= -180)){
                this.directionPointer = 2;
            }else if(rangle >= -157.5 && rangle < -112.5){
                this.directionPointer = 3;
            }else if(rangle >= -112.5 && rangle < -67.5){
                this.directionPointer = 4;
            }else if(rangle >= -67.5 && rangle < -22.5){
                this.directionPointer = 5;
            }

            this.setDx((int) directions.get(directionPointer).getX());
            this.setDy((int) directions.get(directionPointer).getY());

            /**
             * If enemy is close enough, follow player
             */
            if(distance <= 250 && distance >= 100){
                long curSpeedUp = System.currentTimeMillis();
                if(curSpeedUp - lastSpeedUp > 1000){
                    speedUp();
                    lastSpeedUp = curSpeedUp;
                }
            }else{
                slowDown();
            }

            /**
             * If enemy is close enough, shoot
             */
            if(distance > 50){
                long curShot = System.currentTimeMillis();
                if(curShot - lastShot > timeToNextShot){
                    fire(0);
                    lastShot = curShot;
                    timeToNextShot = 100 + (int)(Math.random() * (2000 - 100));
                    SoundBoard.LASER3.play();
                }
            }
        }
    }
}