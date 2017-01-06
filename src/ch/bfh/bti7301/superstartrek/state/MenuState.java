package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;
import ch.bfh.bti7301.superstartrek.model.MenuBackground;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by florianauderset on 16.12.16.
 */
public class MenuState extends State {

    private SubPanel mainPanel;
    private BorderLayout layout = new BorderLayout();

    private ArrayList<String[]> options;
    private int menuPointer = 0;

    private Font font;
    private Color fontColor;
    private Color fontColorActive;
    private Font titleFont;
    private Color titleColor;

    private MenuBackground menuBackground;

    public MenuState(StateMachine stateMachine) {

        super(stateMachine);

        mainPanel = new SubPanel(this, GamePanel.WIDTH, GamePanel.HEIGHT);
        getGamePanel().add(mainPanel);
        getPanels().add(mainPanel);

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
        titleFont = new Font("Final Frontier Old Style", Font.PLAIN, 150);

        fontColor = new Color(255,255,255);
        fontColorActive = new Color(255,0,0);
        font = new Font("Arial", Font.PLAIN, 75);

        // init backgroundimage
        menuBackground = new MenuBackground("background_blue.jpg", 1000);
        menuBackground.setVector(1,-1);

        // init background music
        SoundBoard.MENU.play();
    }


    @Override
    public void input() {

    }

    @Override
    public void update() {
        menuBackground.update();
    }

    public void draw() {
        // draw menuBackground
        Graphics2D g = mainPanel.getG();
        menuBackground.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        String title = "Super Star Trek";
        g.drawString(title, (GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(title)/2), 200);

        // draw menu options
        g.setFont(font);
        int padding = 100;
        for (int i = 0; i < options.size(); i++) {
            if (i == menuPointer) {
                g.setColor(Color.RED);
            } else {
                g.setColor(fontColor);
            }
            g.drawString(options.get(i)[0], (GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(options.get(i)[0])/2), 370 + i * font.getSize());
        }

    }

    @Override
    public void enter() {
        getGamePanel().setLayout(layout);
        getGamePanel().add(mainPanel, BorderLayout.CENTER);

        GameState gs = (GameState) getStateMachine().getStates().get("game");
        if (gs.isInitialized()){
            options.get(0)[0] = "Resume game";
        }

        SoundBoard.MENU.play();
    }

    @Override
    public void exit() {
        SoundBoard.MENU.stop();
    }

    private void select() {
        if (!options.get(menuPointer)[1].equals("exit")) {
            getStateMachine().change(options.get(menuPointer)[1]);
        }
        else{
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            select();
        }
        if (key == KeyEvent.VK_UP) {
            menuPointer--;
            if (menuPointer == -1) {
                menuPointer = options.size() - 1;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            menuPointer++;
            if (menuPointer == options.size()) {
                menuPointer = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
