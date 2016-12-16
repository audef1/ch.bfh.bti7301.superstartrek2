package ch.bfh.bti7301.superstartrek.model;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by filip on 02.12.2016.
 */
public class StarFleetShip extends SpaceShip {

    public StarFleetShip(Color color, Texture texture, float x, float y, float speed, int width, int height, int tx, int ty){
        super(color, texture, x, y, speed, width, height, tx, ty);
    }

    /**
     * Handles input of the starfleet ship.
     *
     * @param entity Only needed for the AI
     */
    @Override
    public void input(Entity entity) {
        /* Player input */
        long window = GLFW.glfwGetCurrentContext();
        if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS) {
            direction.y = 1f;
        }
        if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS) {
            direction.y = -1f;
        }
        if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) {
            direction.x = -1f;
        }
        if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
            direction.x = 1f;
        }
    }


}
