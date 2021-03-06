package nl.github.martijn9612.fishy.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.OpponentController;
import nl.github.martijn9612.fishy.ScoreController;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.powerups.PowerupController;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Level State of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class LevelState extends BasicGameState {
	private Player player;
	private String state = "Playing";
	private String fishPosition = "(" + 0 + "," + 0 + ")";
	private String lives = "lives: (" + 0 + ")";
	private static String score = "0";
	private Image background;
	private OpponentController opponentController;
	private PowerupController powerupController;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();
	private boolean nextStateIsHelpState = false;
    private static final int PLAYER_WIN_AT_SCORE = 500;
    private static final int XPOS_STATE_STRING = 300;
    private static final int YPOS_STATE_STRING = 10;
    private static final int XPOS_SCORE_STRING = 450;
    private static final int XPOS_LIVES_STRING = 500;
    public static final int STATE_ID = 1;

    /**
     * Sets the score.
     * @param s - new score.
     */
	public static void setScore(String s) {
		score = s;
	}

	/**
     * Initialization of the play screen.
     * @param gc - the container holding the game.
     * @param sbg - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
		opponentController = new OpponentController(true);
		powerupController = new PowerupController();
		player = Player.createPlayer(true);
	}

	/**
     * Method executed when entering this game state.
     * @param gameContainer - the container holding the game.
     * @param stateBasedGame - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    @Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.enter(gameContainer, stateBasedGame);
		Main.actionLogger.logLine("Entering LevelState", getClass().getSimpleName());
		musicPlayer.loopSound(MusicPlayer.BG_MUSIC_LEVEL);
		nextStateIsHelpState = false;
	}

    /**
     * Renders the game's screen.
     * @param gc - the container holding the game
     * @param sbg - the game holding the state
     * @param g - the graphics content used to render
     * @throws SlickException - indicates internal error
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
     * @param gc - the container holding the game
     * @param sbg - the game holding the state
     * @param delta - time that has passed since last update in milliseconds
     * @throws SlickException - indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_P)) {
			gc.setPaused(!gc.isPaused());
			HelpState.setPrevious(LevelState.STATE_ID);
			sbg.enterState(HelpState.STATE_ID);
			nextStateIsHelpState = true;
		}
		
		player.objectLogic(gc, delta);
        opponentController.updateOpponents(gc, delta);
        opponentController.spawnOpponents(player);
		opponentController.collide(player, sbg);
		powerupController.updatePowerup(gc, delta);
		powerupController.SpawnPowerup();
		powerupController.collide(player, sbg);
		fishPosition = player.getData().getPosition().toString();
		lives = player.getLivesAsString();

        if (player.getScore() >= PLAYER_WIN_AT_SCORE) {
            Main.actionLogger.logLine("Player won the game", getClass().getSimpleName());
            ScoreController.getInstance().storePlayerScore(player.getScore());
            player.resetPlayerVariables();
            sbg.enterState(WinState.STATE_ID);
        }
    }

    /**
     * Method executed when leaving this game state.
     * @param gameContainer - the container holding the game.
     * @param stateBasedGame - the game holding this state.
     * @throws SlickException - indicates internal error.
     */
	@Override
	public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.leave(gameContainer, stateBasedGame);
		Main.actionLogger.logLine("Leaving LevelState", getClass().getSimpleName());
		musicPlayer.stopSound(MusicPlayer.BG_MUSIC_LEVEL);
		if(!nextStateIsHelpState) {
			opponentController.removeAllOpponents();
			player.resetPlayerVariables();
			powerupController.remove();
		}
	}
    
    /**
     * Get the ID of this state.
     * @return the unique ID of this state.
     */
    public int getID() {
        return STATE_ID;
    }

    /**
     * Gets the current score.
     * @return current score.
     */
	public static String getScore() {
		return score;
	}
}
