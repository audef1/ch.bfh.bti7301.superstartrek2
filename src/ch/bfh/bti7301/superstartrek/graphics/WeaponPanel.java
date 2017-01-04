package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.model.StarFleetShip;
import ch.bfh.bti7301.superstartrek.model.Weapon;
import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by Florian on 31.12.2016.
 */
public class WeaponPanel extends SubPanel{

    public WeaponPanel(State state, int width, int height){
        super(state,width, height);
        setBackground(new Background("cockpit_right.jpg", 0));
        getB().setPosition(0,0);
    }

    @Override
    public void draw()
    {
        Graphics2D g = getG();
        GameState gs = (GameState) getState().getStateMachine().getStates().get("game");
        StarFleetShip p = gs.getPlayer();

        // draw background
        getB().draw(g);

        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);

        // draw weapon 1
        Weapon w1 = p.getWeapons().get(0);
        int w1capacity = w1.getCapacity();
        int w1maxCapacity = w1.getCapacity();
        int w1height = 100;
        int w1singleHeight = w1height / (w1maxCapacity/10);

        for (int i = 0; i < (w1capacity/10); i++){
            if (w1capacity > i){
                if (w1capacity < (w1maxCapacity/3*2)) {
                    g.setColor(Color.ORANGE);
                    if (w1capacity < w1maxCapacity / 3) {
                        g.setColor(Color.RED);
                    }
                }
                else{
                    g.setColor(Color.GREEN);
                }
                g.fillRect(51, 235 - (i * w1singleHeight), 26, w1singleHeight);
                g.setColor(new Color(230,230,230));
                g.setStroke(new BasicStroke(4));
                g.drawRect(51, 235 - (i * w1singleHeight), 26, w1singleHeight);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(w1capacity), 52,285);

        if (p.getWeapons().size() > 1){
            Weapon w2 = p.getWeapons().get(1);
            int w2capacity = w2.getCapacity();
            int w2maxCapacity = w2.getCapacity();
            int w2height = 100;
            int w2singleHeight = w2height / (w2maxCapacity);

            for (int i = 0; i < (w2capacity); i++){
                if (w2capacity > i){
                    if (w2capacity < (w2maxCapacity/3*2)) {
                        g.setColor(Color.ORANGE);
                        if (w1capacity < w2maxCapacity / 3) {
                            g.setColor(Color.RED);
                        }
                    }
                    else{
                        g.setColor(Color.GREEN);
                    }
                    g.fillRect(112, 235 - (i * w2singleHeight), 26, w2singleHeight);
                    g.setColor(new Color(230,230,230));
                    g.setStroke(new BasicStroke(4));
                    g.drawRect(112, 235 - (i * w2singleHeight), 26, w2singleHeight);
                }
            }
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(w2capacity), 113,285);
        }
        else{
            // draw a grey box over weapon2
            g.setColor(new Color(167,167,167));
            g.fillRect(95, 100 , 60, 170);
        }

        // draw other stuff here
    }

}