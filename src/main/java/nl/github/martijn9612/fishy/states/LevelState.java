package nl.github.martijn9612.fishy.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.OpponentController;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.powerups.PowerupController;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Level State of the game.
 */
public class LevelState extends BasicGameState {
	private Player player;
	private String state = "Playing";
	private String fishPosition = "(" + 0 + "," + 0 + ")";
	private String lives = "lives: (" + 0 + ")";
	private static String score = "0";
	private Image background;
	private static OpponentController opponentController;
	private static PowerupController powerupController;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    private static final int PLAYER_WIN_AT_SCORE = 500;
    private static final int XPOS_STATE_STRING = 300;
    private static final int YPOS_STATE_STRING = 10;
    private static final int XPOS_SCORE_STRING = 450;
    private static final int XPOS_LIVES_STRING = 500;

    /**
     * Constructor for the LevelState.
     * 
     * @param state the number of the state
     */
    public LevelState(int state) {
        // Blank
    }

	public static void setScore(String s) {
		score = s;
	}

	/**
     * Initialization of the play screen.
     * 
     * @param gc the container holding the game
     * @param sbg the game holding the state
     * @throws SlickException indicates internal error
     */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
		opponentController = new OpponentController(true);
		powerupController = new PowerupController();
		player = Player.createPlayer(true);
	}

	/**
	 * Triggers when the state is entered.
	 * @param gameContainer the container of the game
	 * @param stateBasedGame the game holding the state
	 * @throws SlickException
	 */
    @Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.enter(gameContainer, stateBasedGame);
		Main.actionLogger.logLine("Entered level", getClass().getSimpleName());
		musicPlayer.loopSound(MusicPlayer.BG_MUSIC_LEVEL);
	}

	/**
	 * Triggers when the state is left.
	 * @param gc the container of the game
	 * @param sbg the game holding the state
	 * @throws SlickException
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.leave(gc, sbg);
		musicPlayer.stopSound(MusicPlayer.BG_MUSIC_LEVEL);
	}

    /**
     * Renders the game's screen.
     * 
     * @param gc the container holding the game
     * @param sbg the game holding the state
     * @param g the graphics content used to render
     * @throws SlickException indicates internal error
     */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawString(state, XPOS_STATE_STRING, YPOS_STATE_STRING);
		g.drawImage(background, 0, 0);
		g.drawString(fishPosition, XPOS_STATE_STRING, YPOS_STATE_STRING);
		g.drawString(score, XPOS_SCORE_STRING, YPOS_STATE_STRING);
		g.drawString(lives, XPOS_LIVES_STRING, YPOS_STATE_STRING);
		player.renderObject(g);
		opponentController.renderOpponents(g);
		powerupController.renderOpponents(g);
	}

    /**
     * Update the game logic and check if the win condition should be triggered.
     * 
     * @param gc the container holding the game
     * @param sbg the game holding the state
     * @param delta time that has passed since last update in milliseconds
     * @throws SlickException indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_P)) {

			gc.setPaused(!gc.isPaused());

			sbg.enterState(Main.HELP_STATE);
			HelpState.setPrevious(Main.LEVEL_STATE);
		}
		player.objectLogic(gc, delta);
        opponentController.updateOpponents(gc, delta);
        opponentController.spawnOpponents(player);
		opponentController.collide(player, sbg);
		powerupController.updatePowerup(gc, delta);
		powerupController.SpawnPowerup();
		powerupController.collide(player, sbg);
		fishPosition = player.position.toString();
		lives = player.getLivesAsString();

        if (player.getScore() >= PLAYER_WIN_AT_SCORE) {
            Main.actionLogger.logLine("Player won the game", getClass()
					.getSimpleName());
            player.resetPlayerVariables();
            sbg.enterState(Main.GAME_WIN_STATE);
        }
    }



    /**
     * Get the ID of this state.
     * 
     * @return the unique ID of this state
     */
    public int getID() {
        return Main.LEVEL_STATE;
    }

	public static String getScore() {
		return score;
	}

	/**
	 * Get the OpponentController.
	 * @return the OpponentController
	 */
	public static OpponentController getOC() {
		return opponentController;
	}

	/**
	 * Get the PowerupController.
	 * @return the Powerupcontroller
	 */
	public static PowerupController getPC() {
		return powerupController;
	}
}
