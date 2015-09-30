package nl.github.martijn9612.fishy.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.OpponentController;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Level State of the game.
 */
public class LevelState extends BasicGameState {

	public Player player;
	public Sound bgPlayMusic;
	public String state = "Playing";
	public String fishPosition = "(" + 0 + "," + 0 + ")";
	public static String score = "0";
	public static int time = 0;
	private Image background;
	private OpponentController opponentController;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    private static final int PLAYER_WIN_AT_SCORE = 500;
    private static final int XPOS_STATE_STRING = 300;
    private static final int YPOS_STATE_STRING = 10;
    private static final int XPOS_SCORE_STRING = 450;

    /**
     * Constructor for the LevelState.
     * 
     * @param state the number of the state
     */
    public LevelState(int state) {
        // Blank
    }

    /**
     * Initialization of the play screen.
     ** @param gc the container holding the game
     * @param sbg the game holding the state
     * @throws SlickException indicates internal error
     */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
	}

    @Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.enter(gameContainer, stateBasedGame);
		musicPlayer.loopSound(MusicPlayer.BG_MUSIC_LEVEL);
		Main.actionLogger.logLine("Entered level", getClass().getSimpleName());
		opponentController = new OpponentController(true);
		player = new Player(true);
	}

    /**
     * Renders the game's screen.
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
		player.renderObject(g);
		opponentController.renderOpponents(g);
	}

    /**
     * Update the game logic and check if the win condition should be triggered.
     * @param gc the container holding the game
     * @param sbg the game holding the state
     * @param delta the amount of time that has passed since last update in
     *              milliseconds
     * @throws SlickException indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        player.objectLogic(gc, delta);
        opponentController.updateOpponents(gc, delta);
        opponentController.spawnOpponents(player);
        fishPosition = player.position.toString();

		if(!opponentController.isWhaleEventInProgress()) {
			opponentController.startWhaleEvent(player);
			if(opponentController.isWhaleEventInProgress()) {
				time = 25000;
			}
		}

		if(time > 0){
			time -= delta;
		} else{
			time = 0;
			opponentController.setWhaleEventInProgress(false);
		}
		opponentController.collide(player, sbg);

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
     * @param gc the container holding the game
     * @param sbg the game holding this state
     * @throws SlickException indicates internal error
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.leave(gc, sbg);
        player.resetPlayerVariables();
        musicPlayer.stopSound(MusicPlayer.WHALE_EVENT);
        musicPlayer.stopSound(MusicPlayer.BG_MUSIC_LEVEL);
        opponentController.destroyAllOpponents();
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
