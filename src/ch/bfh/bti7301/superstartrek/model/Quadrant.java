package ch.bfh.bti7301.superstartrek.model;

import java.util.ArrayList;

/**
 * Created by florianauderset on 04.11.16.
 */
public class Quadrant {

    private String name;
    private int quadrantnr;
    private Boolean cleared = false;
    private ArrayList<SpaceObject> spaceobjects;

    public Quadrant(String name, int quadrantnr){
        this.name = name;
        this.quadrantnr = quadrantnr;
        initQuadrant();
    }

    private void initQuadrant(){
        // fill spaceobjects with rocks and stuff

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

    public ArrayList<SpaceObject> getSpaceobjects() {
        return spaceobjects;
    }
    public void setSpaceobjects(ArrayList<SpaceObject> spaceobjects) {
        this.spaceobjects = spaceobjects;
    }

}