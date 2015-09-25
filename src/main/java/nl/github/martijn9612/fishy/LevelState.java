package nl.github.martijn9612.fishy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implements the Level State of the game.
 */
public class LevelState extends BasicGameState {
    private Player player;
    private Sound bgPlayMusic;
    private String state = "Playing";
    private String fishPosition = "(" + 0 + "," + 0 + ")";
    public static String score = "0";

    private Image background;
    private OpponentHandler opponentHandler;
    private static final int PLAYER_WIN_AT_SCORE = 500;
    private static final int XPOS_STATE_STRING = 300;
    private static final int YPOS_STATE_STRING = 10;
    private static final int XPOS_SCORE_STRING = 450;

    /**
     * Constructor for the LevelState.
     * 
     * @param state
     *            - the number of the state
     */
    public LevelState(int state) {
        // Blank
    }

    /**
     * Initialize the play screen.
     * 
     * @param gc
     *            - the container holding the game
     * @param sbg
     *            - the game holding the state
     * @throws SlickException
     *             - indicates internal error
     */
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        Main.actionLogger.logLine("Entered level", getClass().getSimpleName());
        player = new Player();
        background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
        opponentHandler = new OpponentHandler();
        bgPlayMusic = new Sound("resources/sounds/bg-play-music.wav");
    }

    /**
     * Notification that the game state has been entered.
     * 
     * @param gc
     *            - the container holding the game
     * @param sbg
     *            - the game holding the state
     * @throws SlickException
     *             - indicates internal error
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        super.enter(gc, sbg);
        bgPlayMusic.loop();
    }

    /**
     * Renders the game's screen.
     * 
     * @param gc
     *            - the container holding the game
     * @param sbg
     *            - the game holding the state
     * @param g
     *            - the graphics content used to render
     * @throws SlickException
     *             - indicates internal error
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        g.setColor(Color.black);
        g.drawString(state, XPOS_STATE_STRING, YPOS_STATE_STRING);
        g.drawImage(background, 0, 0);
        g.drawString(fishPosition, XPOS_STATE_STRING, YPOS_STATE_STRING);
        g.drawString(score, XPOS_SCORE_STRING, YPOS_STATE_STRING);
        player.renderObject(g);
        opponentHandler.renderOpponents(g);
    }

    /**
     * Update the game logic and check if the win condition should be triggered.
     * 
     * @param gc
     *            - the container holding the game
     * @param sbg
     *            - the game holding the state
     * @param delta
     *            - the amount of time that has passed since last update in
     *            milliseconds
     * @throws SlickException
     *             - indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        player.objectLogic(gc, delta);
        opponentHandler.collide(player, sbg);
        opponentHandler.updateOpponents(gc, delta);
        opponentHandler.newOpponent(player);
        fishPosition = "(" + player.x + "," + player.y + ")";

        if (player.getScore() >= PLAYER_WIN_AT_SCORE) {
            Main.actionLogger.logLine("Player won the game", getClass()
                    .getSimpleName());
            player.resetPlayerVariables();
            sbg.enterState(Main.GAME_WIN_STATE);
        }
    }

    /**
     * Notification that we're leaving this game state.
     * 
     * @param gc
     *            - the container holding the game
     * @param sbg
     *            - the game holding this state
     * @throws SlickException
     *             - indicates internal error
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        bgPlayMusic.stop();
        super.leave(gc, sbg);
    }

    /**
     * Get the ID of this state.
     * 
     * @return the unique ID of this state
     */
    public int getID() {
        return Main.LEVEL_STATE;
    }
}
