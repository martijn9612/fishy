package nl.github.martijn9612.fishy.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.ScoreController;
import nl.github.martijn9612.fishy.models.Button;
import nl.github.martijn9612.fishy.models.SubmitScoreWidget;
import nl.github.martijn9612.fishy.position.MousePosition;

/**
 * Implements the win state of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class WinState extends BasicGameState {
	private static final int SCORE_DRAW_X = 550;
	private static final int SCORE_DRAW_Y = 20;
    private static final int SUBMIT_SCORE_X = 183;
    private static final int SUBMIT_SCORE_Y = 360;
    private static final int WIN_TEXT_DRAW_X = 230;
    private static final int WIN_TEXT_DRAW_Y = 20;
    private static final int PLAY_BUTTON_DRAW_X = 150;
    private static final int PLAY_BUTTON_DRAW_Y = 400;
    private static final int EATING_FISH_DRAW_X = 210;
    private static final int EATING_FISH_DRAW_Y = 80;
    private static final String WIN_TEXT_STRING = "You WON the game!! :D";
    private static final String PLAY_BUTTON_RESOURCE = "resources/play-button.gif";
    private static final String EATING_FISH_RESOURCE = "resources/eating-fish.png";
    private static final String SCORE_TEXT = "Score: ";
    public static final int STATE_ID = 3;
    
    private Image eatingFish;
    private Button playButton;
    private MousePosition mouse;
    private SubmitScoreWidget submitScore;
    private TrueTypeFont textFont;

    /**
     * Initialize the game.
     * @param container - the container holding the game.
     * @param game - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        playButton = new Button(PLAY_BUTTON_DRAW_X, PLAY_BUTTON_DRAW_Y, PLAY_BUTTON_RESOURCE);
        eatingFish = new Image(EATING_FISH_RESOURCE);
        textFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN, 18), true);
        submitScore = new SubmitScoreWidget(container, SUBMIT_SCORE_X, SUBMIT_SCORE_Y);
        mouse = new MousePosition();
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
		Main.actionLogger.logLine("Entering WinState", getClass().getSimpleName());
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
        g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        g.drawImage(eatingFish, EATING_FISH_DRAW_X, EATING_FISH_DRAW_Y);
        int playerScore = (int) Math.round(ScoreController.getInstance().getPlayerScore());
        textFont.drawString(WIN_TEXT_DRAW_X, WIN_TEXT_DRAW_Y, WIN_TEXT_STRING, Color.black);
        textFont.drawString(SCORE_DRAW_X, SCORE_DRAW_Y, SCORE_TEXT + playerScore, Color.black);
        submitScore.render(gc, g);
        playButton.draw(g);
    }

    /**
     * Update the game logic.
     * @param gc - the container holding the game.
     * @param game - the game holding the state.
     * @param delta - the amount of time that has passed since last update in
     * milliseconds.
     * @throws SlickException - indicates internal error.
     */
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        mouse.updatePosition();
        Input input = gc.getInput();
        submitScore.update(gc, game, mouse);
        
        if (playButton.wasClickedBy(mouse) || input.isKeyDown(Input.KEY_ENTER)) {
        	submitScore.submitScore();
            game.enterState(LevelState.STATE_ID);
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
		Main.actionLogger.logLine("Leaving WinState", getClass().getSimpleName());
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
