package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.*;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.MapState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;
import java.util.ArrayList;

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
        getB().draw(g);

        MapState ms = (MapState) getState();
        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        StarFleetShip player = gs.getPlayer();

        Level level = ms.getCurrentLevel();

        Quadrant[][] quadrants = level.getQuadrants();

        int xMap = 32; // x startposition of map on panel
        int yMap = 40; // y startposition of map on panel

        int qWidth = 240; // quadrant width
        int qHeight = 147; // quadrant height

        // draw map
        for (int i = 0; i < quadrants.length; i++){
            for (int j = 0; j < quadrants[i].length; j++){

                Quadrant q = quadrants[i][j];
                ArrayList<SpaceObject> spaceObjects = q.getSpaceobjects();

                // draw quadrants
                if (q.getCleared()){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.RED);
                }

                int linewidth = 2;
                Stroke stroke;
                if (q.getVisited()){
                    stroke = new BasicStroke(linewidth);
                }
                else{
                    stroke = new BasicStroke(linewidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{16}, 0);
                }
                g.setStroke(stroke);

                g.drawRect(xMap + i * qWidth, yMap + j * qHeight, qWidth - linewidth, qHeight - linewidth);

                // draw player
                if (level.getCurrentquardant().getQuadrantnr() == (i+1)+(j*GamePanel.GAMESIZE)){
                    g.drawImage(player.getSprites().get(0)[0], xMap + i * qWidth + (int)(player.getX()/2.66), yMap + j * qHeight + (int)(player.getY()/3.3), player.getWidth()/2, player.getHeight()/2, null);
                }

                // draw spacestations, enemies
                for (SpaceObject so : spaceObjects ){

                    if(so instanceof SpaceStation){
                        g.setColor(Color.GREEN);
                        g.fillOval(xMap + i * qWidth + (int)(so.getX()/2.75), yMap + j * qHeight + (int)(so.getY()/3.3),15,15);
                    }

                    // draw only enemies which we have seen already
                    if (q.getVisited()){
                        if(so instanceof EnemyShip){
                            g.setColor(Color.RED);
                            g.fillOval(xMap + i * qWidth + (int)(so.getX()/2.75), yMap + j * qHeight + (int)(so.getY()/3.3),10,10);
                        }
                    }
                }
            }
        }

        // draw other stuff here

    }
}