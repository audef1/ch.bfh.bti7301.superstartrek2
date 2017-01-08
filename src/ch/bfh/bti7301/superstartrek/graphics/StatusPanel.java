package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.model.StarFleetShip;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;

/**
 * Created by Florian on 31.12.2016.
 * Left partial of the Cockpit HUD View.
 */
public class StatusPanel extends SubPanel {

    /**
     * Class Constructor
     *
     * @param state  The current state of the state machine.
     * @param width  Width of the Panel.
     * @param height Height of the Panel.
     */
    public StatusPanel(State state, int width, int height) {
        super(state, width, height);
        setBackground(new Background("cockpit_left.jpg", 0));
        getB().setPosition(0, 0);
    }

    /**
     * Draw to buffered image.
     */
    @Override
    public void draw() {
        Graphics2D g = getG();
        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        StarFleetShip p = gs.getPlayer();

        /* draw background */
        getB().draw(g);

        /* draw power */
        int power = p.getHealth();
        int maxPower = p.getHealthMax();
        int height = 139;
        int singleHeight = height / (maxPower / 10);

        for (int i = 0; i < (power / 10); i++) {
            if (power > i) {
                if (power < (maxPower / 3 * 2)) {
                    g.setColor(Color.ORANGE);
                    if (power < maxPower / 3) {
                        g.setColor(Color.RED);
                    }
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(51, 235 - (i * singleHeight), 26, singleHeight);
                g.setColor(new Color(230, 230, 230));
                g.setStroke(new BasicStroke(4));
                g.drawRect(51, 235 - (i * singleHeight), 26, singleHeight);
            }
        }

        /* draw shieldenergy */
        int shield = p.getShield();
        int maxShield = p.getShieldMax();
        int shieldHeight = 139;
        int singleShieldHeight = shieldHeight / (maxShield / 10);

        for (int i = 0; i < (shield / 10); i++) {
            if (shield > i) {
                if (shield < (maxShield / 3 * 2)) {
                    g.setColor(Color.ORANGE);
                    if (shield < maxShield / 3) {
                        g.setColor(Color.RED);
                    }
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(112, 235 - (i * singleShieldHeight), 26, singleShieldHeight);
                g.setColor(new Color(230, 230, 230));
                g.setStroke(new BasicStroke(4));
                g.drawRect(112, 235 - (i * singleShieldHeight), 26, singleShieldHeight);
            }
        }

        /* draw other stuff here */
    }

}