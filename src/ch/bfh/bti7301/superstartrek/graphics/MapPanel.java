package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.Quadrant;
import ch.bfh.bti7301.superstartrek.state.MapState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;

/**
 * Created by Florian on 01.01.2017.
 */
public class MapPanel extends SubPanel {

    public MapPanel(State state, int width, int height) {
        super(state, width, height);
        setBackground(new Background("cockpit_map.jpg", 0));
        getB().setPosition(0, 0);
    }

    @Override
    public void draw() {
        Graphics2D g = getG();

        // draw background
        getB().draw(getG());

        MapState ms = (MapState) getState();
        Level level = ms.getCurrentLevel();

        Quadrant[][] quadrants = level.getQuadrants();

        // draw map
        for (int i = 0; i < quadrants.length; i++){
            for (int j = 0; j < quadrants[i].length; j++){
                if (quadrants[i][j].getCleared()){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.RED);
                }
                g.drawRect(32 + i * 240, 40 + j * 147, 240, 147);
            }
        }

        // draw other stuff here

    }
}