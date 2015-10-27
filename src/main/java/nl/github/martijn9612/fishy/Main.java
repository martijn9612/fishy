package nl.github.martijn9612.fishy;

import nl.github.martijn9612.fishy.states.HelpState;
import nl.github.martijn9612.fishy.states.LevelState;
import nl.github.martijn9612.fishy.states.LoseState;
import nl.github.martijn9612.fishy.states.MenuState;
import nl.github.martijn9612.fishy.states.ScoreState;
import nl.github.martijn9612.fishy.states.WinState;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.utils.ActionLogger;

import java.util.logging.Logger;

/**
 * Implements the Main knowledge behind the game.
 * Software Engineering Methods Project - Group 11.
 */
public class Main extends StateBasedGame {
    private static final String GAME_NAME = "Fishy";
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 550;
    public static final String PLAYER_CHARACTER = "cheep-cheep";
    public static final String OPPONENT_CHARACTER = "cheep-cheep";
    public static final String LEVEL_BACKGROUND = "seabed";
    public static final int FRAMERATE = 60;
    public static final ActionLogger actionLogger = new ActionLogger();
    public static final boolean DEBUG_MODE = false;

    /**
     * Constructor method.
     * Adds all the states to the game.
     * @param gameName - name of the new game
     */
    public Main(String gameName) {
        super(gameName);
        this.addState(new MenuState());
        this.addState(new LevelState());
        this.addState(new WinState());
        this.addState(new LoseState());
        this.addState(new HelpState());
        this.addState(new ScoreState());
    }

    /**
     * Initialize the list of states making up the game.
     * Calls the init method in each state to initialize the game.
     * @param gc - the container holding the game
     * @throws SlickException - indicates a failure to initialize the resources
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
    	// Blank
    }

    /**
     * Main method.
     * @param args - args.
     */
    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main(GAME_NAME));
            appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
            appgc.setTargetFrameRate(FRAMERATE);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * Notification that the game close has been requested.
     * @return true if the game should close.
     */
    @Override
    public boolean closeRequested() {
        Main.actionLogger.logLine("Game will close!", getClass().getSimpleName());
        return true;
    }

    /**
     * Get method for the ActionLogger.
     * @return the current actionlogger.
     */
    public static ActionLogger getActionLogger() {
        return actionLogger;
    }
}