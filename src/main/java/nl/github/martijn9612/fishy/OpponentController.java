package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.WhaleIndicator;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;
import nl.github.martijn9612.fishy.opponents.Whale;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentController {

	private ArrayList<Opponent> opponents;
	private ArrayList<Opponent> toRemove;
	private Random random = new Random();
	private ArrayList<Whale> whales;
	private WhaleIndicator indicator;
	private boolean whaleEventInProgress = false;
	private MusicPlayer musicPlayer;
	private boolean loadResources;

	public OpponentController(boolean loadResources) {
		this.loadResources = loadResources;
		if (loadResources) {
			musicPlayer = MusicPlayer.getInstance();
		}
		opponents = new ArrayList<Opponent>();
		toRemove = new ArrayList<Opponent>();
		whales = new ArrayList<Whale>();
	}
	
    /**
     * Get method for the variable opponents.
     * @return opponents - list with all the opponents in it
     */
    public ArrayList<Opponent> getOpponents() {
        return opponents;
    }

    /**
     * Get method for the variable toRemove.
     * @return toRemove - list with all the removed opponents
     */
    public ArrayList<Opponent> getToRemove() {
        return toRemove;
    }

    /**
     * Adds an opponent to the list with opponents.
     * @param opp - opponent to be added to the list
     */
    public void addOpponent(Opponent opp) {
        opponents.add(opp);
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
		int maxSize = (int) (player.getSize() * 2);
		int minSize = (int) (player.getSize() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int speed = random.nextInt(4) + 1;
		int max = 515 - size;
		int min = size;
		int ypos = random.nextInt(Math.abs(max - min)) + min;
		int xpos = (isleft ? 0 - size * 5 : 615 + size * 5);
		LinearOpponent linearOpponent = new LinearOpponent(isleft, xpos, ypos, size, speed, loadResources);
		opponents.add(linearOpponent);
	}

	private void newSinusOpponent(Player player) {
		int maxSize = (int) (player.getSize() * 2.5);
		int minSize = (int) (player.getSize() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int max = 615 - (int) Math.round(size);
		int min = (int) Math.round(size);
		int xpos = random.nextInt(Math.abs(max - min)) + min;
		SinusOpponent sinusOpponent = new SinusOpponent(xpos, size, loadResources);
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
	public void updateOpponents(GameContainer gc, int deltaTime) {
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

		if(whaleEventInProgress) {
			for(Whale whale : whales) {
				whale.objectLogic(gc, deltaTime);
			}
			indicator.objectLogic(gc, deltaTime);
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
			if (opponent.intersects(player)) {
				String log = "Player collides with opponent of size " + Math.floor(opponent.getSize());
				Main.actionLogger.logLine(log, getClass().getSimpleName());
				if (player.getSize() > opponent.getSize()) {
					player.eat(opponent);
					destroy(opponent);
				} else {
					Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
					player.resetPlayerVariables();
					whaleEventInProgress = false;
					destroyAllOpponents();
					musicPlayer.stopSound(MusicPlayer.WHALE_EVENT);
					sbg.enterState(Main.GAME_LOSE_STATE);
				}
			}
		}

		if (whaleEventInProgress) {
			for (Whale whale : whales) {
				if (whale.intersects(player)) {
					Main.actionLogger.logLine("Player lost the game because of the whale", getClass().getSimpleName());
					player.resetPlayerVariables();
					whaleEventInProgress = false;
					destroyAllOpponents();
					musicPlayer.stopSound(MusicPlayer.WHALE_EVENT);
					sbg.enterState(Main.GAME_LOSE_STATE);
					break;
				}
			}
		}
	}

	public void startWhaleEvent(Player player) {
		double rand = Math.random();
		if(rand < 0.0006) {
			whaleEventInProgress = true;
			indicator = new WhaleIndicator(player, true);
			Whale whale = new Whale(player, true);
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

	public boolean isWhaleEventInProgress(){
		return this.whaleEventInProgress;
	}

	public void setWhaleEventInProgress( boolean status){
		this.whaleEventInProgress = status;
	}
}
