package nl.github.martijn9612.fishy.states;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Skullyhoofd on 13/10/2015.
 * Implements the HelpState which shows instructions to the game.
 */
public class HelpState extends BasicGameState {

    /**
     * Constructor for the Help State.
     *
     * @param state - the number of the state
     */
    public HelpState(int state) {
        // Blank
    }

    /**
     * Initialize the game.
     *
     * @param container
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @throws SlickException
     *             - indicates internal error
     */
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    /**
     * Renders the game's screen.
     *
     * @param gc
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @param g
     *            - the graphics content used to render
     * @throws SlickException
     *             - indicates internal error
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

    }

    /**
     * Update the game logic.
     *
     * @param gc
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @param delta
     *            - the amount of time that has passed since last update in
     *            milliseconds
     * @throws SlickException
     *             - indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        
    }



    /**
     * Get the ID of this state.
     *
     * @return the unique ID of this state
     */
    @Override
    public int getID() {
        return Main.HELP_STATE;
    }
}
