package ch.bfh.bti7301.superstartrek.model;

/**
 * Created by florianauderset on 04.11.16.
 */
public class MenuBackground extends Background{

    /**
     * overloaded constructor
     * @param filename Background-filename
     * @param ms refresh time in ms
     */
    public MenuBackground(String filename, double ms) {
        super(filename, ms);
        setX(0);
        setY(0);
    }

    /**
     * Updates background position
     */
    public void update() {
        setX(getX() + getDx());
        setY(getY() + getDy());

        // reset the background position if image is out of screen
        if (Math.abs(getX()) == (getBackground().getWidth()/2)){
            setX(0);
            setY(0);
        }
    }
}