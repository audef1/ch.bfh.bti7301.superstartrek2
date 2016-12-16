package ch.bfh.bti7301.superstartrek.model;

import ch.bfh.bti7301.superstartrek.state.GameState;
import ch.bfh.bti7301.superstartrek.state.MenuState;
import ch.bfh.bti7301.superstartrek.state.StateMachine;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.logging.Logger;

import static org.lwjgl.glfw.GLFW.*;


/**
 * Created by florianauderset on 04.11.16.
 */
public class Game {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    private ch.bfh.bti7301.superstartrek.model.Level[][] levels;
    private ch.bfh.bti7301.superstartrek.model.Level currentLevel;
    private int size = 4;
    private int score = 0;

    private GLFWErrorCallback errorCallback;
    protected boolean running;
    protected Window window;
    protected Timer timer;
    protected Renderer renderer;
    protected StateMachine state;

    public Game(int size) {
        this.size = size;
        timer = new Timer();
        renderer = new Renderer();
        state = new StateMachine();
    }

    public void start() {
        init();
        gameLoop();
        dispose();
    }

    /**
     * Releases resources that where used by the game.
     */
    public void dispose() {
        /* Dipose renderer */
        renderer.dispose();

        /* Set empty state to trigger the exit method in the current state */
        state.change(null);

        /* Release window and its callbacks */
        window.destroy();

        /* Terminate GLFW and release the error callback */
        glfwTerminate();
        errorCallback.free();
    }

    /**
     * Initializes the game.
     */
    public void init() {

        /* Set error callback */
        errorCallback = GLFWErrorCallback.createPrint();
        glfwSetErrorCallback(errorCallback);

        /* Initialize GLFW */
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        /* Create GLFW window */
        window = new Window(640, 480, "Super Star Trek", true);

        /* Initialize timer */
        timer.init();

        /* Initialize renderer */
        renderer.init(isDefaultContext());

        /* Initialize states */
        initStates();

         /* Initializing done, set running to true */
        running = true;
    }

    /**
     * Initializes the states.
     */
    public void initStates() {
        if (renderer.hasDefaultContext()) {
            //state.add("example", new ExampleState());
            //state.add("texture", new TextureState());
        } else {
            //state.add("example", new LegacyExampleState());
            //state.add("texture", new LegacyTextureState());
        }
        state.add("menu", new MenuState(renderer));
        state.add("game", new GameState(renderer, size));
        state.change("menu");
    }

    /**
     * The game loop.
     */
    public void gameLoop() {
        float delta;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
        float alpha;

        while (running) {
            /* Check if game should close */
            if (window.isClosing()) {
                running = false;
            }

            /* Get delta time and update the accumulator */
            delta = timer.getDelta();
            accumulator += delta;

            /* Handle input */
            input();

            /* Update game and timer UPS if enough time has passed */
            while (accumulator >= interval) {
                update();
                timer.updateUPS();
                accumulator -= interval;
            }

            /* Calculate alpha value for interpolation */
            alpha = accumulator / interval;

            /* Render game and update timer FPS */
            render(alpha);
            timer.updateFPS();

            /* Update timer */
            timer.update();

            /* Draw FPS, UPS and Context version */
            /*
            int height = renderer.getDebugTextHeight("Context");
            renderer.drawDebugText("FPS: " + timer.getFPS() + " | UPS: " + timer.getUPS(), 5, 5 + height);
            renderer.drawDebugText("Context: " + (renderer.hasDefaultContext() ? "3.2 core" : "2.1"), 5, 5);
            */

            /* Update window to show the new screen */
            window.update();

            /* Synchronize if v-sync is disabled */
            if (!window.isVSyncEnabled()) {
                sync(TARGET_FPS);
            }
        }
    }

    /**
     * Handles input.
     */
    public void input() {
        state.input();
    }

    /**
     * Updates the game.
     */
    public void update() {
        state.update();
    }

    /**
     * Renders the game (no interpolation).
     */
    public void render() {
        state.render();
    }

    /**
     * Renders the game (with interpolation).
     *
     * @param alpha Alpha value, needed for interpolation
     */
    public void render(float alpha) {
        state.render(alpha);
    }

    /**
     * Synchronizes the game at specified frames per second.
     *
     * @param fps Frames per second
     */
    public void sync(int fps) {
        double lastLoopTime = timer.getLastLoopTime();
        double now = timer.getTime();
        float targetTime = 1f / fps;

        while (now - lastLoopTime < targetTime) {
            Thread.yield();

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            now = timer.getTime();
        }
    }

    /**
     * Determines if the OpenGL context supports version 3.2.
     *
     * @return true, if OpenGL context supports version 3.2, else false
     */
    private boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }

    public Level[][] getLevels() {
        return levels;
    }
    public void setLevels(Level[][] levels) {
        this.levels = levels;
    }

    public Level getCurrentlevel() {
        return currentLevel;
    }
    public void setCurrentlevel(Level currentLevel) { this.currentLevel = currentLevel; }

    public int getSize() { return size; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

}