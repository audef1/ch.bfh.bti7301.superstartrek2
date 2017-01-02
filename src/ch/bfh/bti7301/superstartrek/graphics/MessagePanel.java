package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Florian on 31.12.2016.
 */
public class MessagePanel extends SubPanel{

    private ArrayList<Color> colors;

    public MessagePanel(State state, int width, int height){
        super(state,width, height);
        setBackground(new Background("cockpit_bottom.jpg", 0));
        getB().setPosition(0,0);
        colors = new ArrayList<Color>();
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        colors.add(Color.RED);
    }

    @Override
    public void draw()
    {
        Graphics2D g = getG();
        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        StarFleetShip p = gs.getPlayer();
        ArrayList<SpaceObject> spaceobjects = gs.getSpaceobjects();

        // draw background
        getB().draw(getG());

        // draw speed
        int speed = (int) p.getSpeed();
        int maxSpeed = (int) p.getMaxSpeed();
        int height = 115;
        int singleHeight = height / maxSpeed;

        for (int i = 0; i < maxSpeed; i++){

            if (speed > i){
                g.setColor(new Color(153,0,0));
                g.fillRect(684, 153 - (i * singleHeight), 26, singleHeight);
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(4));
                g.drawRect(684, 153 - (i * singleHeight), 26, singleHeight);
            }


        }

        // draw minimap
        int mapWidth = 233;
        int mapHeight = 145;
        int xMapStart = 760;
        int yMapStart = 25;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.GREEN);
        g.drawRect(xMapStart, yMapStart, mapWidth, mapHeight);

        for(SpaceObject so : spaceobjects){
            if (so instanceof EnemyShip){
                if (xMapStart + (int)(so.getX()/2.66) >= xMapStart && xMapStart + (int)(so.getX()/2.66) <= xMapStart+mapWidth && yMapStart + (int)(so.getY()/3.3) >= yMapStart && yMapStart + (int)(so.getY()/3.3) <= yMapStart+mapHeight - so.getHeight()){
                    g.setColor(Color.RED);
                    g.fillOval(xMapStart + (int)(so.getX()/2.66), yMapStart + (int)(so.getY()/3.3),10,10);
                }
            }

            if (so instanceof SpaceStation){
                g.setColor(Color.GREEN);
                g.fillOval(xMapStart + (int)(so.getX()/2.66), yMapStart + (int)(so.getY()/3.3),15,15);
            }
        }

        // draw messageterminal

        // draw other stuff here
    }

}