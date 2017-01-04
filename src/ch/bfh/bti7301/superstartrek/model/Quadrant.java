package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
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

    public Quadrant(String name, int quadrantnr){
        this.name = name;
        this.quadrantnr = quadrantnr;
        spaceobjects = new ArrayList<SpaceObject>();
        initQuadrant();
    }

    private void initQuadrant(){
        // fill spaceobjects with rocks and stuff
        int min = 0;
        int maxSpaceStation = 1;
        int maxMeteors = 7;
        int maxEnemies = 4;

        int amountSpaceStation = ThreadLocalRandom.current().nextInt(min, maxSpaceStation + 1);
        int amountMeteors = ThreadLocalRandom.current().nextInt(min, maxMeteors + 1);
        int amountEnemies = ThreadLocalRandom.current().nextInt(min, maxEnemies + 1);

        spaceobjects.addAll(SpaceObjectFactory.createSpaceObject("spaceStation", amountSpaceStation));
        spaceobjects.addAll(SpaceObjectFactory.createSpaceObject("meteor", amountMeteors));
        spaceobjects.addAll(SpaceObjectFactory.createSpaceObject("enemy", amountEnemies));
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