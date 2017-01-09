package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.graphics.SubPanel;
import ch.bfh.bti7301.superstartrek.model.MenuBackground;
import ch.bfh.bti7301.superstartrek.model.StarFleetShip;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Florian on 09.01.2017.
 */
public class GameOverState extends State {

        private SubPanel mainPanel;
        private BorderLayout layout = new BorderLayout();

        private ArrayList<String[]> options;
        private int menuPointer = 0;

        private Font font;
        private Color fontColor;
        private Color fontColorActive;
        private Font titleFont;
        private Color titleColor;
        private String message;
        private String score = "Score: ";

        private MenuBackground menuBackground;

        public GameOverState(StateMachine stateMachine) {

            super(stateMachine);

            mainPanel = new SubPanel(this, GamePanel.WIDTH, GamePanel.HEIGHT);
            getGamePanel().add(mainPanel);
            getPanels().add(mainPanel);

            // define menuoptions
            this.options = new ArrayList<String[]>();
            String[] gamerestart = new String[2];
            gamerestart[0] = "Restart game";    //menuoption name
            gamerestart[1] = "game";          //statename

            String[] gameexit = new String[2];
            gameexit[0] = "Exit";    //menuoption name
            gameexit[1] = "exit";    //statename

            options.add(gamerestart);
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

            // draw title and score
            g.setColor(titleColor);
            g.setFont(titleFont);
            String title = "GAME OVER";
            g.drawString(title, (GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(title)/2), 200);

            g.setFont(new Font("Final Frontier Old Style", Font.PLAIN, 120));
            g.drawString(message,(GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(message)/2), 300);

            g.setFont(new Font("Final Frontier Old Style", Font.PLAIN, 90));
            g.setColor(Color.RED);
            g.drawString(score,(GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(score)/2), 400);

            // draw menu options
            g.setFont(font);
            for (int i = 0; i < options.size(); i++) {
                if (i == menuPointer) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(fontColor);
                }
                g.drawString(options.get(i)[0], (GamePanel.WIDTH/2)-(g.getFontMetrics().stringWidth(options.get(i)[0])/2), 575 + i * font.getSize());
            }

        }

        @Override
        public void enter() {
            getGamePanel().setLayout(layout);
            getGamePanel().add(mainPanel, BorderLayout.CENTER);

            GameState gs = (GameState) getStateMachine().getStates().get("game");
            StarFleetShip p = gs.getPlayer();
            if (p.isDead()) {
                message = "You have been defeated!";
            }
            else {
                message = "You're Winner!";
            }

            score += gs.getScore();

            SoundBoard.ENDING.play();
        }

        @Override
        public void exit() {

            GameState gs = (GameState) getStateMachine().getStates().get("game");
            gs.initGame();
            gs.setScore(0);

            SoundBoard.ENDING.stop();
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
                SoundBoard.BUTTON.play();
            }

            if (key == KeyEvent.VK_DOWN) {
                menuPointer++;
                if (menuPointer == options.size()) {
                    menuPointer = 0;
                }
                SoundBoard.BUTTON.play();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        private void select() {
            if (!options.get(menuPointer)[1].equals("exit")) {
                getStateMachine().change(options.get(menuPointer)[1]);
            }
            else{
                System.exit(0);
            }
        }

}