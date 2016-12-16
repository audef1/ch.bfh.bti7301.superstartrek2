package ch.bfh.bti7301.superstartrek.state;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by florianauderset on 16.12.16.
 */
public class MenuState implements State {

    private ArrayList<String[]> options;
    private int menupointer = 0;

    public MenuState() {

        // define menuoptions
        this.options = new ArrayList<String[]>();
        String[] gamestart = new String[2];
        gamestart[0] = "Start game";    //menuoption name
        gamestart[1] = "game";          //statename

        String[] gameoptions = new String[2];
        gameoptions[0] = "Options";    //menuoption name
        gameoptions[1] = "options";    //statename

        String[] gameexit = new String[2];
        gameexit[0] = "Exit";    //menuoption name
        gameexit[1] = "exit";    //statename

        options.add(gamestart);
        options.add(gameoptions);
        options.add(gameexit);

    }


    @Override
    public void input() {
        if (false) {
            if (menupointer != 0){
                menupointer--;
            }
        }
        if (false) {
            if (menupointer != options.size()-1){
                menupointer++;
            }
        }
        if (false) {
            System.out.println("entering " + options.get(menupointer)[0] + " state.");
        }

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(options.get(menupointer)[0] + " selected");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
