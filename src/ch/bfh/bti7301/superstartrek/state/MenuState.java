package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.model.MenuBackground;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by florianauderset on 16.12.16.
 */
public class MenuState implements State {

    private final StateMachine statemachine;

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Color fontColor;
    private Color fontColorActive;
    private Font titleFont;
    private Color titleColor;

    private MenuBackground menuBackground;

    public MenuState(StateMachine statemachine) {

        this.statemachine = statemachine;

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

        // init menu fonts and colors
        titleColor = new Color(238,221,130);
        titleFont = new Font("Final Frontier Old Style", Font.PLAIN, 100);

        fontColor = new Color(255,255,255);
        fontColorActive = new Color(255,0,0);
        font = new Font("Arial", Font.PLAIN, 40);

        // init backgroundimage
        menuBackground = new MenuBackground("images/Backgrounds/background_blue.jpg", 1000);
        menuBackground.setVector(1,-1);

    }


    @Override
    public void input() {

    }

    @Override
    public void update() {
        menuBackground.update();
    }

    @Override
    public void draw(Graphics2D g) {

        // draw menuBackground
        menuBackground.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Super Star Trek", 60, 100);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.size(); i++) {
            if (i == menuPointer) {
                g.setColor(Color.RED);
            } else {
                g.setColor(fontColor);
            }
            g.drawString(options.get(i)[0], 200, 200 + i * font.getSize());
        }

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    private void select() {
        if (!options.get(menuPointer)[1].equals("exit")) {
            statemachine.change(options.get(menuPointer)[1]);
        }
        else{
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            menuPointer--;
            if (menuPointer == -1) {
                menuPointer = options.size() - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            menuPointer++;
            if (menuPointer == options.size()) {
                menuPointer = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
