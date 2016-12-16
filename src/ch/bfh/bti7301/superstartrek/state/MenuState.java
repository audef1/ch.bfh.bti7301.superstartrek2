package ch.bfh.bti7301.superstartrek.state;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by florianauderset on 16.12.16.
 */
public class MenuState implements State {

    private final Renderer renderer;
    private ArrayList<String[]> options;
    private int menupointer = 0;

    public MenuState(Renderer renderer) {
        this.renderer = renderer;

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
        long window = GLFW.glfwGetCurrentContext();
        if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS) {
            if (menupointer != 0){
                menupointer--;
            }
        }
        if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS) {
            if (menupointer != options.size()-1){
                menupointer++;
            }
        }
        if (glfwGetKey(window, GLFW_KEY_ENTER) == GLFW_PRESS) {
            System.out.println("entering " + options.get(menupointer) + " state.");
        }

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(options.get(menupointer) + " selected");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float alpha) {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }
}
