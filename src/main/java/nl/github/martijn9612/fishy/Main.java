package nl.github.martijn9612.fishy;

import nl.github.martijn9612.fishy.states.HelpState;
import nl.github.martijn9612.fishy.states.LevelState;
import nl.github.martijn9612.fishy.states.LoseState;
import nl.github.martijn9612.fishy.states.MenuState;
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
    public static final int MENU_STATE = 0;
    public static final int LEVEL_STATE = 1;
    public static final int GAME_LOSE_STATE = 2;
    public static final int GAME_WIN_STATE = 3;
    public static final int HELP_STATE = 4;

    private static final String GAME_NAME = "Fishy";
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 550;
    public static final String PLAYER_CHARACTER = "cheep-cheep";
    public static final String OPPONENT_CHARACTER = "cheep-cheep";
    public static final String LEVEL_BACKGROUND = "seabed";
    public static final int FRAMERATE = 60;
    public static ActionLogger actionLogger = new ActionLogger();
    public static final boolean DEBUG_MODE = false;

    /**
     * Constructor method.
     * Adds all the states to the game.
     * @param gameName - name of the new game
     */
    public Main(String gameName) {
        super(gameName);
        this.addState(new MenuState(MENU_STATE));
        this.addState(new LevelState(LEVEL_STATE));
        this.addState(new WinState(GAME_WIN_STATE));
        this.addState(new LoseState(GAME_LOSE_STATE));
        this.addState(new HelpState(HELP_STATE));
    }

    /**
     * Initialize the list of states making up the game.
     * Calls the init method in each state to initialize the game.
     * @param gc - the container holding the game
     * @throws SlickException - indicates a failure to initialize the resources
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MENU_STATE).init(gc, this);
        this.getState(LEVEL_STATE).init(gc, this);
        this.getState(GAME_LOSE_STATE).init(gc, this);
        this.getState(GAME_WIN_STATE).init(gc, this);
        this.getState(HELP_STATE).init(gc, this);
        this.enterState(MENU_STATE);
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
        Main.actionLogger.logLine("Game Closed!", getClass().getSimpleName());
        return true;
    }

    /**
     * Set method for the ActionLogger.
     * @param ac - the actionlogger to be set to.
     */
    public static void setActionLogger(ActionLogger ac) {
        actionLogger = ac;
    }

    /**
     * Get method for the ActionLogger.
     * @return the current actionlogger.
     */
    public static ActionLogger getActionLogger() {
        return actionLogger;
    }
}