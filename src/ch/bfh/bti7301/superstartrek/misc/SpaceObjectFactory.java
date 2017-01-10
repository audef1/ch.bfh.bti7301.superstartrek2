package ch.bfh.bti7301.superstartrek.misc;


import ch.bfh.bti7301.superstartrek.model.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by buche on 27.12.2016.
 */
public class SpaceObjectFactory {

    private static ArrayList<SpaceObject> so = new ArrayList<SpaceObject>();

    /**
     * default constructor
     */
    public SpaceObjectFactory() {
        so = new ArrayList<SpaceObject>();
    }

    /**
     * Creates SpaceObjects
     * @param objectType which object should be created
     * @param amount how many objects should be created
     * @return ArrayList with all created objects
     */
    public  static ArrayList<SpaceObject> createSpaceObject(String objectType , int amount) {
        Random random = new Random();


        switch (objectType) {
            case "meteor":
                for (int i = 0; i < amount; i++){

                    Meteor m = new Meteor(100, 80, setObjectPosition(50, 540), setObjectPosition(50, 380),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.05 - 0.01) + 0.01));

                    setPosition(so, m);
                    so.add(m);
                }
            break;

            case "enemy":
            {
                for (int i = 0; i < amount; i++) {
                    EnemyShip es = new EnemyShip(112, 75,setObjectPosition(50, 540), setObjectPosition(50, 380),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.1 - 0.05) + 0.05), 40, 20);

                    setPosition(so, es);
                    so.add(es);
                }
            }
            break;

            case "spaceStation":
            {
                for (int i = 0; i < amount; i++) {
                    SpaceStation ss = new SpaceStation(91, 91, setObjectPosition(50, 540), setObjectPosition(50, 380));

                    setPosition(so, ss);
                    so.add(ss);
                }
            }
            break;
        }
        return so;
    }

    /**
     * recursive method to set position until no collision with other objects
     * @param arrSo SpaceObjects Array
     * @param spaceObject Current SpaceObject
     */
    private static void setPosition(ArrayList<SpaceObject> arrSo, SpaceObject spaceObject){
        for(SpaceObject obj: arrSo){
            if(spaceObject.intersects(obj)){
                spaceObject.setX(setObjectPosition(50, 540));
                spaceObject.setY(setObjectPosition(50, 380));

                setPosition(arrSo, obj);
            }
        }
    }

    /**
     * gives random values between min/max
     * @param min min value
     * @param max max value
     * @return random value between the two given values
     */
    private static int setObjectPosition(int min, int max){
        return(ThreadLocalRandom.current().nextInt(min, max + 1));
    }
}