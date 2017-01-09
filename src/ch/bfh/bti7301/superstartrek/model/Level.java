package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.misc.QuadrantGenerator;

/**
 * Created by florianauderset on 04.11.16.
 */
public class Level {

    private String name;
    private int levelnr;
    private Boolean cleared = false;
    private Quadrant[][] quadrants;
    private Quadrant currentquadrant;

    /**
     * overloaded constructor
     * @param name Level name
     * @param levelnr level number
     * @param size level size
     */
    public Level(String name, int levelnr, int size){
        this.name = name;
        this.levelnr = levelnr;
        quadrants = new Quadrant[size][size];
        initQuadrants(size);
        currentquadrant = quadrants[0][0];
    }

    /**
     * create quadrants
     * @param size how many quadrants should be created
     */
    private void initQuadrants(int size) {
        QuadrantGenerator quadrantgenerator = new QuadrantGenerator(size);
        quadrants = quadrantgenerator.getQuadrants();
    }

    /**
     * Gets a quadrant based on the given parameter
     * @param nr which quadrant should be returned
     * @return Quadrant asked by parameter
     */
    public Quadrant getQuadrantByNr(int nr){
        for (int i = 0; i < quadrants.length; i++){
            for (int j = 0; j < quadrants[i].length; j++){
                if (nr == (i+1)+(j* GamePanel.GAMESIZE)){
                    return quadrants[i][j];
                }
            }
        }
        return quadrants[0][0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelnr() {
        return levelnr;
    }

    public void setLevelnr(int levelnr) {
        this.levelnr = levelnr;
    }

    public Boolean getCleared() {
        return cleared;
    }

    public void setCleared(Boolean cleared) {
        this.cleared = cleared;
    }

    public Quadrant[][] getQuadrants() {
        return quadrants;
    }

    public void setQuadrants(Quadrant[][] quadrants) {
        this.quadrants = quadrants;
    }

    public Quadrant getCurrentquardant() {
        return currentquadrant;
    }

    public void setCurrentquardant(Quadrant currentquadrant) {
        this.currentquadrant = currentquadrant;
    }


}
