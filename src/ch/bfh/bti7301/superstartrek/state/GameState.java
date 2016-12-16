package ch.bfh.bti7301.superstartrek.state;

import ch.bfh.bti7301.superstartrek.graphics.math.Vector2f;
import ch.bfh.bti7301.superstartrek.misc.LevelGenerator;
import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.SpaceObject;
import ch.bfh.bti7301.superstartrek.model.StarFleetShip;
import com.sun.prism.Texture;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.glClearColor;

// import spaceobjects


/**
 * Created by florianauderset on 02.12.16.
 */

public class GameState implements State {

    private final Renderer renderer;

    private Level[][] levels;
    private Level currentLevel;
    private ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
    private Texture texture;
    private SubTextureLoader subtextureLoader = new SubTextureLoader();

    /* private variables - ex. score */

    public GameState(Renderer renderer, int size) {
        this.renderer = renderer;
        initlevels(size);

        /* Initialize variables defined on top of the class */


        /* Get width and height of framebuffer */
        long window = GLFW.glfwGetCurrentContext();
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetFramebufferSize(window, widthBuffer, heightBuffer);
        int width = widthBuffer.get();
        int height = heightBuffer.get();

        /* Load Spritesheet into Texture */
        texture = Texture.loadTexture(this.getClass().getResource("/images/Spritesheet/sheet.png").getPath());

         /* Initialize game objects */
        SubTexture p1 = subtextureLoader.load("playerShip1_blue.png");
        StarFleetShip ship = new StarFleetShip(Color.WHITE, texture, width/2, height/2, 1, p1.getWidth(), p1.getHeight(), p1.getX(), p1.getY());
        ship.setDirection(new Vector2f(0,-1));
        ship.setSpeed(150);

        SubTexture r1 = subtextureLoader.load("meteorBrown_big1.png");
        SpaceObject rock = new SpaceObject(Color.WHITE, texture, 20, 30, 2, r1.getWidth(), r1.getHeight(), r1.getX(), r1.getY() );
        rock.setDirection(new Vector2f(1, 1));
        rock.setSpeed(50);

        SubTexture r2 = subtextureLoader.load("meteorBrown_big1.png");
        SpaceObject rock2 = new SpaceObject(Color.WHITE, texture, 50, 70, 2, r2.getWidth(), r2.getHeight(), r2.getX(), r2.getY() );
        rock2.setDirection(new Vector2f(1, 1));
        rock2.setSpeed(70);

        SubTexture r3 = subtextureLoader.load("meteorBrown_big1.png");
        SpaceObject rock3 = new SpaceObject(Color.WHITE, texture, 400, 300, 2, r3.getWidth(), r3.getHeight(), r3.getX(), r3.getY() );
        rock3.setDirection(new Vector2f(-1, -1));
        rock3.setSpeed(80);

        spaceobjects.add(ship);
        spaceobjects.add(rock);
        spaceobjects.add(rock2);
        spaceobjects.add(rock3);

        /* Set clear color to gray */
        glClearColor(0.5f, 0.5f, 0.5f, 1f);
    }

    private void initlevels(int size) {
        levels = new LevelGenerator(size).getLevels();
        currentLevel = levels[0][0];
    }

    @Override
    public void input() {
        /* check input of all spaceobjects - specificspaceobject.input() */
        for(SpaceObject so : spaceobjects){
            so.input();
        }
    }

    @Override
    public void update(float delta) {
        /* Update position */
        for(SpaceObject so : spaceobjects){
            //check collisions
            so.update(delta);
        }
        /* Check for collisions */


        /* Update scores etc if necessary */

    }

    @Override
    public void render(float alpha) {
        /* Clear drawing area */
        renderer.clear();

        /* Draw game objects */
        texture.bind();
        renderer.begin();
            /* render all specific spaceobjects - specificspaceobject.render(renderer, alpha) */

            for(SpaceObject so : spaceobjects){
                so.render(renderer, alpha);
            }

        renderer.end();

        System.out.println("rendering...");

        /* Draw Stuff such as Score, Playerinformation, Messages etc. */
        /*
        String scoreText = "Score";
        int scoreTextWidth = renderer.getTextWidth(scoreText);
        int scoreTextHeight = renderer.getTextHeight(scoreText);
        float scoreTextX = (gameWidth - scoreTextWidth) / 2f;
        float scoreTextY = gameHeight - scoreTextHeight - 5;
        renderer.drawText(scoreText, scoreTextX, scoreTextY, Color.BLACK);
        */
        //renderer.drawText("Hallo", 20, 20, Color.WHITE);
    }

    @Override
    public void enter() {
        /* do stuff when entering this state */

    }

    @Override
    public void exit() {
        /* do stuff when exiting this state */
    }

}