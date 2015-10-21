package nl.github.martijn9612.fishy.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.ScoreController;
import nl.github.martijn9612.fishy.models.Score;

/**
 * Implements the Lose State of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class ScoreState extends BasicGameState {
    
	public static final int STATE_ID = 5;
	private ScoreController scoreController;

    /**
     * Constructor for the Lose State.
     * @param state - the number of the state.
     */
    public ScoreState(int state) {
        // Blank
    }

    /**
     * Initialize the game.
     * @param container - the container holding the game.
     * @param game - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	scoreController = new ScoreController();
    	scoreController.addScore(new Score("Leon Noordam", 9000));
    }

    /**
     * Renders the game's screen.
     * @param gc - the container holding the game.
     * @param game - the game holding the state.
     * @param g - the graphics content used to render.
     * @throws SlickException - indicates internal error.
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("List of submitted Fishy highscores:", 50, 50);
        renderHighscores(g);
    }
    
    private void renderHighscores(Graphics g) {
		ArrayList<Score> scores = scoreController.getScoreList();
		if (scores.size() == 0) {
			g.drawString("No submitted scores were found.", 50, 100);
		} else {
			int loopIndex = 0;
			for (Score score : scores) {
				g.drawString(score.getName(), 50, 100 + 50 * loopIndex++);
			}
		}
    }

    /**
     * Update the game logic.
     * @param gc - the container holding the game.
     * @param game - the game holding the state.
     * @param delta - time that has passed since last update in milliseconds.
     * @throws SlickException - indicates internal error.
     */
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
    	
    }

    /**
     * Get the ID of this state.
     * @return the unique ID of this state.
     */
    @Override
    public int getID() {
        return STATE_ID;
    }

}
