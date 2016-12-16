package ch.bfh.bti7301.superstartrek;

import ch.bfh.bti7301.superstartrek.model.Game;
import org.lwjgl.glfw.GLFW;

public class Main {

    /**
     * Main function.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* Start game */
        Game game = new Game(3);
        try {
            game.start();
        } finally {
            /* GLFW has to be terminated or else the application will run in
             * background */
            GLFW.glfwTerminate();
        }
    }

}