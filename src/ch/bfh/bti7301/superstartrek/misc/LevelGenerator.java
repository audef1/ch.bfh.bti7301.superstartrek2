package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.model.Level;

/**
 * Created by florianauderset on 11.11.16.
 */
public class LevelGenerator {
    private Level[][] levels;
    private NameGenerator ngen;

    /**
     * Generates the given amount of levels
     * @param size how many levels should be created
     */
    public LevelGenerator(int size){

        levels = new Level[size][size];
        ngen = new NameGenerator();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                int levelNr = (i+1)*(j+1*size);
                levels[i][j] = new Level(ngen.getName() + " " + levelNr, levelNr, size);
            }
        }
    }

    /**
     * Lvel getter method
     * @return amount of levels
     */
    public Level[][] getLevels(){
        return levels;
    };
}