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
 * Created by Skullyhoofd on 08/09/2015.
 */
public class LevelState extends BasicGameState {
	public Player player;
	public Sound bgPlayMusic;
	public String state = "Playing";
	public String fishPosition = "(" + 0 + "," + 0 + ")";
	public static String score = "0";
	public static int time = 0;
	
	private Image background;
	private OpponentHandler opponentHandler;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();
	
	private static int PLAYER_WIN_AT_SCORE = 500;

	public LevelState(int state) {
		// Blank
	}

	/**
	 * Initialization of the play screen.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Main.actionLogger.logLine("Entered level", getClass().getSimpleName());
		player = new Player();
		background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
		opponentHandler = new OpponentHandler();
	}
  
	@Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.enter(gameContainer, stateBasedGame);
		musicPlayer.loopSound(MusicPlayer.BG_MUSIC_LEVEL);
	}

	/**
	 * Render everything in the level.
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawString(state, 300, 10);
		g.drawImage(background, 0, 0);
		g.drawString(fishPosition, 300, 10);
		g.drawString(score, 450, 10);
		player.renderObject(g);
		opponentHandler.renderOpponents(g);
		if(opponentHandler.getWhaleEventInProgress()) {opponentHandler.renderWhaleEvent(g);}
	}

	/**
	 * Update the elements on the screen and check if the win condition should be triggered.
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.objectLogic(gc, delta);
		opponentHandler.updateOpponents(gc, delta, player);
		opponentHandler.newOpponent(player);
		if(!opponentHandler.getWhaleEventInProgress()) {
			opponentHandler.startWhaleEvent(player, delta);
			if(opponentHandler.getWhaleEventInProgress()) {
				time = 25000;
			}
		}

		if(time > 0){
			time -= delta;
		} else{
			time = 0;
			opponentHandler.setWhaleEventInProgress(false);
		}
		fishPosition = "(" + player.x + "," + player.y + ")";
		opponentHandler.collide(player, sbg);

		if(player.getScore() >= PLAYER_WIN_AT_SCORE ) {
			Main.actionLogger.logLine("Player won the game", getClass().getSimpleName());
			player.resetPlayerVariables();
			sbg.enterState(Main.GAME_WIN_STATE);
		}
	}

	@Override
	public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.leave(gameContainer, stateBasedGame);
		musicPlayer.stopSound(MusicPlayer.BG_MUSIC_LEVEL);
	}

	/**
	 * Get id.
	 */
	public int getID() {
		return Main.LEVEL_STATE;
	}
}
