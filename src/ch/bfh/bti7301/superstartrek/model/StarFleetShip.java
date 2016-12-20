package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by filip on 02.12.2016.
 */
public class StarFleetShip extends SpaceShip {


    private ArrayList<Point> directions;
    private int directionPointer = 0;

    public StarFleetShip(int width, int height){
        super(width, height);

        // init directions
        directions = new ArrayList<>();
        directions.add(new Point(0, 1));
        directions.add(new Point(-1, 1));
        directions.add(new Point(-1, 0));
        directions.add(new Point(-1, -1));
        directions.add(new Point(0, -1));
        directions.add(new Point(1, -1));
        directions.add(new Point(1, 0));
        directions.add(new Point(1, 1));
    }

    public void fire(){
        System.out.println("peng");
    }

    public void turnLeft(){
        if (directionPointer == directions.size() -1)
            directionPointer = 0;
        else
            directionPointer ++;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());
        System.out.println("turning left... x: " + this.getDx() + " y: " + this.getDy());
    }

    public void turnRight(){
        if (directionPointer == 0)
            directionPointer = directions.size() -1;
        else
            directionPointer --;

        // set new direction
        this.setDx((int) directions.get(directionPointer).getX());
        this.setDy((int) directions.get(directionPointer).getY());
        System.out.println("turning right... x: " + this.getDx() + " y: " + this.getDy());
    }

    public void speedUp() {
        // TODO: implement acceleration and maxspeed
        this.setSpeed(this.getSpeed()+1);
        System.out.println("speeding up...");
    }

    public void slowDown() {
        // TODO: implement breaking
        this.setSpeed(this.getSpeed()-1);
        System.out.println("slowing down...");

    }
}
