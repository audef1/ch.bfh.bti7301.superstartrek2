package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.model.Level;
import ch.bfh.bti7301.superstartrek.model.Quadrant;
import ch.bfh.bti7301.superstartrek.state.*;

/**
 * Created by florianauderset on 04.01.17.
 */
public class LevelStateMachine {


    /**
     * Current gamestate.
     */
    private GameState gameState;

    /**
     * Creates a state machine.
     */
    public LevelStateMachine(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Changes the current level.
     *
     * @param level the new level to go to.
     */
    public void changeLevel(Level level) {
        gameState.setCurrentLevel(level);

    }

    /**
     * Changes the current quadrant.
     *
     * @param quadrant the new quadrant to go to.
     */
    public void changeQuadrant(Quadrant quadrant) {
        gameState.getCurrentLevel().setCurrentquardant(quadrant);
        gameState.setSpaceObjects()
    }


}