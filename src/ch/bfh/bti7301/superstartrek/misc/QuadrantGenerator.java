package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.model.Quadrant;

/**
 * Created by florianauderset on 11.11.16.
 */
public class QuadrantGenerator {
    private Quadrant[][] quadrants;
    private NameGenerator ngen;

    public QuadrantGenerator(int size){
        quadrants = new Quadrant[size][size];
        ngen = new NameGenerator();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                int quadrantNr = (i+1)+(j* GamePanel.GAMESIZE);
                quadrants[i][j] = new Quadrant(ngen.getName(), quadrantNr);
            }
        }
    }

    public Quadrant[][] getQuadrants(){
        return quadrants;
    }
}
