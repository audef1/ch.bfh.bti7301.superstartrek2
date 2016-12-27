package ch.bfh.bti7301.superstartrek.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by florianauderset on 04.11.16.
 */
public class Quadrant {

    private String name;
    private int quadrantnr;
    private Boolean cleared = false;
    private ArrayList<SpaceObject> spaceobjects;
    private ArrayList<Background> backgrounds;

    public Quadrant(String name, int quadrantnr, ArrayList<Background> backgrounds){
        this.name = name;
        this.quadrantnr = quadrantnr;
        this.backgrounds = backgrounds;
        initQuadrant();
    }

    private void initQuadrant(){
        // fill spaceobjects with rocks and stuff

    }

    public void draw(Graphics2D g){
        for (Background bg : backgrounds){
            bg.draw(g);
        }
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

    public ArrayList<Background> getBackgrounds() {
        return backgrounds;
    }
    public void setBackgrounds(ArrayList<Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

}