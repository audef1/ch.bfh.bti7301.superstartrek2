package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.model.Quadrant;

import java.util.ArrayList;

/**
 * Created by florianauderset on 11.11.16.
 */
public class QuadrantGenerator {
    private Quadrant[][] quadrants;
    private NameGenerator ngen;
    private ArrayList<Background> backgrounds;

    public QuadrantGenerator(int size){
        quadrants = new Quadrant[size][size];
        ngen = new NameGenerator();
        backgrounds = new ArrayList<Background>();
        backgrounds.add(new Background("background_black.jpg", 1));
        backgrounds.add(new Background("background_blue.jpg", 1));
        backgrounds.add(new Background("background_purple.jpg", 1));
        backgrounds.add(new Background("background_darkpurple.jpg", 1));
        backgrounds.add(new Background("background_parallax.png", 1));

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                int quadrantNr = (i+1)*(j+1*size);

                // add backgrounds to the quadrants
                ArrayList<Background> levelBackgrounds = new ArrayList<Background>();
                levelBackgrounds.add(backgrounds.get(quadrantNr % 4));
                levelBackgrounds.add(backgrounds.get(4)); // parallax

                quadrants[i][j] = new Quadrant(ngen.getName(), quadrantNr, backgrounds);
            }
        }
    }

    public Quadrant[][] getQuadrants(){
        return quadrants;
    }
}
