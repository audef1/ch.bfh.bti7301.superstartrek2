package ch.bfh.bti7301.superstartrek.misc;


import ch.bfh.bti7301.superstartrek.model.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by buche on 27.12.2016.
 */
public class SpaceObjectFactory {

    private static ArrayList<SpaceObject> so = new ArrayList<SpaceObject>();

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

                    Meteor m = new Meteor(10, 10,random.nextInt(600), random.nextInt(450),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.1 - 0.01) + 0.01));

                    for(SpaceObject object: so) {
                        if(m.intersects(object))
                        {
                            m.setX((int) object.getX() + 10);
                            m.setY((int) object.getY() + 10);
                        }
                    }
                    so.add(m);
                }
            break;

            case "enemy":
            {
                for (int i = 0; i < amount; i++) {
                    EnemyShip es = new EnemyShip(10, 10,random.nextInt(600), random.nextInt(450),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.5 - 0.1) + 0.1));
                    so.add(es);
                }
            }
            break;

            case "spaceStation":
            {
                for (int i = 0; i < amount; i++) {
                    SpaceStation ss = new SpaceStation(10, 10, random.nextInt(600), random.nextInt(450));
                    so.add(ss);
                }
            }
            break;
        }
        return so;
    }
}