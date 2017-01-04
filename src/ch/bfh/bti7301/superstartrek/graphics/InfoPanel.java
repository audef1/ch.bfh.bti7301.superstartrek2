package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.Quadrant;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;

/**
 * Created by Florian on 31.12.2016.
 */
public class InfoPanel extends SubPanel{

    public InfoPanel(State state, int width, int height){
        super(state,width, height);
        setBackground(new Background("cockpit_top.jpg", 0));
        getB().setPosition(0,0);
    }

    @Override
    public void draw()
    {
        Graphics2D g = getG();
        // draw background
        getB().draw(getG());

        // draw quadrant info
        Font font = new Font("Arial", Font.PLAIN, 20);


        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        Level level = gs.getCurrentLevel();

        Quadrant[][] quadrants = level.getQuadrants();
        Quadrant currentquadrant = level.getCurrentquardant();

        g.setFont(font);
        g.setColor(new Color(238,221,130));
        g.drawString("Level: "+ level.getName(), 395, 33);
        g.drawString("Quadrant: " + currentquadrant.getName(), 395, 63);

        // draw other stuff here
    }

}