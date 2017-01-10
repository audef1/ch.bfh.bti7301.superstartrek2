package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.misc.SpaceObjectFactory;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by florianauderset on 04.11.16.
 */
public class Quadrant {

    private String name;
    private int quadrantnr;
    private Boolean cleared = false;
    private Boolean visited = false;
    private ArrayList<SpaceObject> spaceobjects;
    private Boolean spacestation = false;

    SpaceObjectFactory sof;

    /**
     * overloaded constructor
     * @param name quadrant name
     * @param quadrantnr quadrant number
     * @param spacestation set to true places a spacestation
     */
    public Quadrant(String name, int quadrantnr, boolean spacestation){
        this.name = name;
        this.quadrantnr = quadrantnr;
        this.spacestation = spacestation;
        spaceobjects = new ArrayList<SpaceObject>();

        sof = new SpaceObjectFactory();
        initQuadrant();
    }

    /**
     * Creates a quadrant
     */
    private void initQuadrant(){

        int min = 0;
        int maxMeteors = 3;
        int maxEnemies = 3;

        int amountMeteors = ThreadLocalRandom.current().nextInt(min, maxMeteors + 1);
        int amountEnemies = ThreadLocalRandom.current().nextInt(min, maxEnemies + 1);

        // fill spaceobjects with meteors and stuff
        if (spacestation)
            spaceobjects.addAll(sof.createSpaceObject("spaceStation", 1));
        spaceobjects.addAll(sof.createSpaceObject("meteor", amountMeteors));
        spaceobjects.addAll(sof.createSpaceObject("enemy", amountEnemies));
    }

    public String getName() {
        return name;
    }
    public int getQuadrantnr() {
        return quadrantnr;
    }

    public Boolean getCleared() {
        return cleared;
    }
    public void setCleared(Boolean cleared) {
        this.cleared = cleared;
    }

    public Boolean getVisited() {
        return visited;
    }
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public ArrayList<SpaceObject> getSpaceobjects() {
        return spaceobjects;
    }
    public void setSpaceobjects(ArrayList<SpaceObject> spaceobjects) {
        this.spaceobjects = spaceobjects;
    }

}