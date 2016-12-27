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
    private ArrayList<Background> background;

    public Quadrant(String name, int quadrantnr){
        this.name = name;
        this.quadrantnr = quadrantnr;
        initQuadrant();
    }

    private void initQuadrant(){
        // fill spaceobjects with rocks and stuff

        String color;
        if (quadrantnr % 4 == 0){
            color = "black";
        }
        else if (quadrantnr % 4 == 1){
            color = "blue";
        }
        else if (quadrantnr % 4 == 2){
            color = "purple";
        }
        else{
            color = "darkpurple";
        }
        background.add(new Background("background_" + color + ".jpg", 1));
        background.add(new Background("background_parallax.png", 0.5));

    }

    public void draw(Graphics2D g){
        for (Background bg : background){
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

    public ArrayList<Background> getBackground() {
        return background;
    }
    public void setBackground(ArrayList<Background> background) {
        this.background = background;
    }

}