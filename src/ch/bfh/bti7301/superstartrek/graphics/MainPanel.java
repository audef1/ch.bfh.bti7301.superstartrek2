package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.MapState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Florian on 06.01.2017.
 */
public class MainPanel extends SubPanel {

    public MainPanel(GameState state, int width, int height) {
        super(state, width, height);
    }

    @Override
    public void draw() {
        Graphics2D g = getG();
        GameState gs = (GameState) getState();

        /* draw level background */
        gs.getBackgrounds().get(gs.getCurrentLevel().getCurrentquardant().getQuadrantnr() % 4).draw(g);

        /* draw player */
        gs.getPlayer().draw(g);

        /* draw all all other spaceobjects on screen */
        for (SpaceObject so : gs.getSpaceobjects()) {
            so.draw(g);
        }
        // draw other stuff here

    }
}