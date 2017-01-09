package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.SpaceObject;
import ch.bfh.bti7301.superstartrek.state.GameState;

import java.awt.*;

/**
 * Created by Florian on 06.01.2017.
 * Main partial of the Cockpit HUD View.
 */
public class MainPanel extends SubPanel {

    /**
     * Class Constructor
     *
     * @param state  The current state of the state machine.
     * @param width  Width of the Panel.
     * @param height Height of the Panel.
     */
    public MainPanel(GameState state, int width, int height) {
        super(state, width, height);
    }

    /**
     * Draw to buffered image.
     */
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
            if(so.getX() > 50 && so.getX() < 500 && so.getY() > 50 && so.getY() < 350){
                so.draw(g);
            }

        }
        /* draw other stuff here */
    }
}