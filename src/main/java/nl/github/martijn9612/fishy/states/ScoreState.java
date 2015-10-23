package nl.github.martijn9612.fishy.states;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.ScoreController;
import nl.github.martijn9612.fishy.models.Button;
import nl.github.martijn9612.fishy.models.Score;
import nl.github.martijn9612.fishy.position.MousePosition;

/**
 * Implements the Lose State of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class ScoreState extends BasicGameState {
	private static final int BACK_TEXT_DRAW_X = 70;
	private static final int BACK_TEXT_DRAW_Y = 40;
	private static final int BACK_BUTTON_DRAW_X = 10;
    private static final int BACK_BUTTON_DRAW_Y = 30;
    
    private static final String BACK_TEXT = "Back";
    private static final String BACK_BUTTON_RESOURCE = "resources/back-button.png";
    private static final String BACKGROUND_RESOURCE = "resources/bg-menu.jpg";
    private static final String NO_SCORES_FOUND = "No submitted scores were found.";
    private static final String SCORE_TITLE_TEXT = "List of submitted Fishy highscores";
    
	public static final int STATE_ID = 5;
	public static final int MAX_SCORE_ENTRIES = 22;
	private static final float SCORE_TITLE_X = 200;
	private static final float SCORE_TITLE_Y = 30;
	private static final float SCORE_ENTRY_Y = 70;
	private static final float SCORE_SCORE_X = 450;
	private static final float SCORE_NAME_X = 200;
	private static final float LINE_HEIGHT = 20;
	
	private Image background;
	private Button backButton;
	private TrueTypeFont textFont;
	private TrueTypeFont titleFont;
	private MousePosition mouse = new MousePosition();
	private Color backButtonTextColor = new Color(70, 175, 230);

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
    	background = new Image(BACKGROUND_RESOURCE);
    	backButton = new Button(BACK_BUTTON_DRAW_X, BACK_BUTTON_DRAW_Y, BACK_BUTTON_RESOURCE);
    	textFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN, 16), true);
        titleFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.BOLD, 24), true);
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
        g.drawImage(background, 0, 0);
        backButton.draw(g);
        titleFont.drawString(BACK_TEXT_DRAW_X, BACK_TEXT_DRAW_Y, BACK_TEXT, backButtonTextColor);
        titleFont.drawString(SCORE_TITLE_X, SCORE_TITLE_Y, SCORE_TITLE_TEXT);
        renderHighscores();
    }
    
    /**
     * Render the list of known scores by displaying name and score value. 
     */
    private void renderHighscores() {
		List<Score> scores = ScoreController.getInstance().getScoreList();
		if (scores.size() > MAX_SCORE_ENTRIES) {
			scores = scores.subList(0, MAX_SCORE_ENTRIES);
		}
		if (scores.size() == 0) {
			textFont.drawString(SCORE_NAME_X, SCORE_ENTRY_Y, NO_SCORES_FOUND);
		} else {
			int loopIndex = 0;
			for (Score score : scores) {
				int playerScore = (int) Math.round(score.getScore());
				float yAxis = SCORE_ENTRY_Y + LINE_HEIGHT * loopIndex++;
				textFont.drawString(SCORE_NAME_X, yAxis, score.getName());
				textFont.drawString(SCORE_SCORE_X, yAxis, String.valueOf(playerScore));
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
    	mouse.updatePosition();
        if (backButton.wasClickedBy(mouse)) {
            game.enterState(Main.MENU_STATE);
        }
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
