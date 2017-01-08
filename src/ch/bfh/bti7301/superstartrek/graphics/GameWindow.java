package ch.bfh.bti7301.superstartrek.graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by florianauderset on 16.12.16.
 * The main game window.
 */
public class GameWindow extends JFrame {

    /**
     * Class Constructor
     */
    public GameWindow() {

        super("Super Star Trek");

        // add star trek font
        // usage -> titleFont = new Font("Final Frontier Old Style", Font.PLAIN, 100);
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getClassLoader().getResource("fonts/finalfrontierold.ttf").getFile())));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        start();
    }

    /**
     * Start up the game window.
     */
    public void start() {
        setContentPane(new GamePanel());
        setSize(1024, 768);
        setBounds(0, 0, 1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
    }

}