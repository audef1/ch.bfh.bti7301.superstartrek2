package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.model.Quadrant;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by florianauderset on 11.11.16.
 */
public class QuadrantGenerator {
    private Quadrant[][] quadrants;
    private NameGenerator ngen;

    /**
     * Generates the given amount of quadrants
     * @param size how many quadrants should be created
     */
    public QuadrantGenerator(int size){
        quadrants = new Quadrant[size][size];
        ngen = new NameGenerator();
        Boolean spacestationplaced = false;
        int random = ThreadLocalRandom.current().nextInt(0, size*size)-1;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                int quadrantNr = (i+1)+(j* GamePanel.GAMESIZE);
                if (!spacestationplaced && quadrantNr == random){
                    quadrants[i][j] = new Quadrant(ngen.getName(), quadrantNr, true);
                    spacestationplaced = true;
                }
                else{
                    quadrants[i][j] = new Quadrant(ngen.getName(), quadrantNr, false);
                }
            }
        }
    }

    public Quadrant[][] getQuadrants(){
        return quadrants;
    }
}
