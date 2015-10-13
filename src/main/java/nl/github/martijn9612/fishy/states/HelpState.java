package nl.github.martijn9612.fishy.states;

import nl.github.martijn9612.fishy.Main;
import org.newdawn.slick.state.BasicGameState;

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
     * Get the ID of this state.
     *
     * @return the unique ID of this state
     */
    @Override
    public int getID() {
        return Main.HELP_STATE;
    }
}
