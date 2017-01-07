package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

/**
 * Created by filip on 02.12.2016.
 */
public class EnemyShip extends SpaceShip {

    private long lastShot = System.currentTimeMillis();
    private long lastSpeedUp = System.currentTimeMillis();
    private int timeToNextShot = 1000;

    public EnemyShip(int width, int height, double x, double y, int dx, int dy, double speed){
        super(width, height, x, y, dx, dy, speed);
        this.getSprite("images/PNG/playerShip2_red.png");
    }

    public void update(StarFleetShip player){
        this.detectEnemyDirection(player.getX(), player.getY());
        super.update();
    }

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

            /*
            if(distance <= 50){
                long curSpeedUp = System.currentTimeMillis();
                if(curSpeedUp - lastSpeedUp > 200){
                    speedUp();
                    lastSpeedUp = curSpeedUp;
                }
            }
            */

            if(distance > 200){
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