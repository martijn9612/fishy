package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentHandler {

	private ArrayList<Opponent> opponents;
	private ArrayList<Opponent> toRemove;
	private Random random = new Random();
	private Whale whale;
	private ArrayList<Whale> whales;
	private WhaleIndicator indicator;
	private boolean whaleEventInProgress = false;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();

	public OpponentHandler() {
		opponents = new ArrayList<Opponent>();
		toRemove = new ArrayList<Opponent>();
		whales = new ArrayList<Whale>();
	}
	  
	/**
	 * create a new fish.
	 */
	public void spawnOpponents(Player player) {
		if (opponents.size() < 20) {
			if ((random.nextInt(5) + 1) > 1) {
				newLinearOpponent(player);
			} else {
				newSinusOpponent(player);
			}
		}
	}

	private void newLinearOpponent(Player player) {
		boolean isleft = random.nextBoolean();
		int maxSize = (int) (player.getWidth() * 2);
		int minSize = (int) (player.getWidth() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int speed = random.nextInt(4) + 1;
		int max = 515 - size;
		int min = size;
		int ypos = random.nextInt(Math.abs(max - min)) + min;
		int xpos = (isleft ? 0 - size * 5 : 615 + size * 5);
		LinearOpponent linearOpponent = new LinearOpponent(isleft, xpos, ypos, size, speed);
		opponents.add(linearOpponent);
	}

	private void newSinusOpponent(Player player) {
		int maxSize = (int) (player.getWidth() * 2.5);
		int minSize = (int) (player.getWidth() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int max = 615 - (int) Math.round(size);
		int min = (int) Math.round(size);
		int xpos = random.nextInt(Math.abs(max - min)) + min;
		SinusOpponent sinusOpponent = new SinusOpponent(xpos, size);
		opponents.add(sinusOpponent);
	}

	/**
	 * render all linearOpponents.
	 * @param graph the graphics.
	 */
	public void renderOpponents(Graphics graph) {
		for (Opponent opponent : opponents) {
			opponent.renderObject(graph);
		}
	}

	/**
	 * Update the opponents.
	 *
	 * @param gc the container holding the game
	 * @param deltaTime the amount of time that has passed since last update in milliseconds
	 */
	public void updateOpponents(GameContainer gc, int deltaTime, Player player) {
		for (Opponent opponent : opponents) {
			opponent.objectLogic(gc, deltaTime);
			if (opponent.isOffScreen()) {
				destroy(opponent);
			}
		}
		for (Opponent opponent : toRemove) {
			opponents.remove(opponent);
		}
		toRemove.clear();

		if(whaleEventInProgress){
			for(Whale w : whales){
				w.objectLogic(gc, deltaTime);
			}
			indicator.objectLogic(gc, deltaTime, player);
		}
	}

	/**
	 * Destroy an opponent.
	 *
	 * @param fishy opponent to destroy
	 */
	public void destroy(Opponent fishy) {
		toRemove.add(fishy);
	}

	/**
	 * Destroy all the opponents.
	 */
	public void destroyAllOpponents() {
		for (Opponent opponent : opponents) {
			destroy(opponent);
		}
		whales.clear();
		Main.actionLogger.logLine("All opponents destroyed", getClass().getSimpleName());
	}

	/**
	 * Checking for collisions.
	 *
	 * @param player Player in the game.
	 * @param sbg the game holding the state
	 */
	public void collide(Player player, StateBasedGame sbg) {
		for (Opponent opponent : opponents) {
			if (opponent.ellipse.intersects(player.ellipse)) {
				String log = "Player collides with opponent of size "
						+ Math.floor(opponent.getSize());
				Main.actionLogger.logLine(log, getClass().getSimpleName());
				if (player.getWidth() > opponent.getWidth()) {
					player.eat(opponent);
					destroy(opponent);
				} else {
					Main.actionLogger.logLine("Player lost the game",
							getClass().getSimpleName());
					player.resetPlayerVariables();
					destroyAllOpponents();
					sbg.enterState(Main.GAME_LOSE_STATE);
				}
			}
		}

		if(whaleEventInProgress) {
			if (player.ellipse.intersects(whale.ellipse)) {
				Main.actionLogger.logLine("Player lost the game because of the whale", getClass().getSimpleName());
				player.resetPlayerVariables();
				destroyAllOpponents();
				sbg.enterState(Main.GAME_LOSE_STATE);
				musicPlayer.stopSound(MusicPlayer.WHALE_EVENT);
			}
		}
	}

	public void startWhaleEvent(Player player){
		double rand = Math.random();
		if(rand < 0.0006){
			whaleEventInProgress = true;
			int playery = player.getY();
			indicator = new WhaleIndicator(playery);
			whale = new Whale(playery);
			whales.add(whale);
			musicPlayer.playSound(MusicPlayer.WHALE_EVENT);
		}


	}

	public void renderWhaleEvent(Graphics g){
		indicator.renderObject(g);
		for (Whale w : whales) {
			w.renderObject(g);
		}
	}

	public boolean getWhaleEventInProgress(){
		return this.whaleEventInProgress;
	}

	public void setWhaleEventInProgress( boolean status){
		this.whaleEventInProgress = status;
	}
}
