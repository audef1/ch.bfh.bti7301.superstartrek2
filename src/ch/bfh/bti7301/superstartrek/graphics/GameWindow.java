package ch.bfh.bti7301.superstartrek.graphics;

import javax.swing.*;

/**
 * Created by florianauderset on 16.12.16.
 */
public class GameWindow extends JFrame {

    public GameWindow(){
        super("Super Star Trek");
        start();
    }

    public void start(){
        setContentPane(new GamePanel(4));
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}