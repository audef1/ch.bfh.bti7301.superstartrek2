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
        so = new ArrayList<SpaceObject>();

        switch (objectType) {
            case "meteor":
                for (int i = 0; i < amount; i++){

                    Meteor m = new Meteor(100, 80, random.nextInt(600), random.nextInt(450),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.1 - 0.01) + 0.01));

                    for(SpaceObject object: so) {
                        if(m.intersects(object))
                        {
                            m.setX((int) object.getX() + 100);
                            m.setY((int) object.getY() + 100);
                        }
                    }
                    so.add(m);
                }
            break;

            case "enemy":
            {
                for (int i = 0; i < amount; i++) {
                    EnemyShip es = new EnemyShip(112, 75,random.nextInt(600), random.nextInt(450),random.nextInt(3) -1,random.nextInt(3) -1,(Math.random() * (0.1 - 0.05) + 0.05));
                    for(SpaceObject object: so) {
                        if(es.intersects(object))
                        {
                            es.setX((int) object.getX() + 100);
                            es.setY((int) object.getY() + 100);
                        }
                    }
                    so.add(es);
                }
            }
            break;

            case "spaceStation":
            {
                for (int i = 0; i < amount; i++) {
                    SpaceStation ss = new SpaceStation(91, 91, random.nextInt(600), random.nextInt(450));
                    for(SpaceObject object: so) {
                        if(ss.intersects(object))
                        {
                            ss.setX((int) object.getX() + 100);
                            ss.setY((int) object.getY() + 100);
                        }
                    }
                    so.add(ss);
                }
            }
            break;
        }
        return so;
    }
}