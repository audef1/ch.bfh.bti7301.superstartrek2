package ch.bfh.bti7301.superstartrek.graphics;

import ch.bfh.bti7301.superstartrek.model.Background;
import ch.bfh.bti7301.superstartrek.state.State;

import java.awt.*;

/**
 * Created by Florian on 31.12.2016.
 */
public class MessagePanel extends SubPanel{

    public MessagePanel(State state, int width, int height){
        super(state,width, height);
        setBackground(new Background("cockpit_bottom.jpg", 0));
        getB().setPosition(0,0);
    }

    @Override
    public void draw()
    {
        // draw background
        getB().draw(getG());

        // draw other stuff here
    }

}