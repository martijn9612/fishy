package nl.github.martijn9612.fishy.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Button;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Menu State of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class MenuState extends BasicGameState {
	private Image background;
    private String menu = "Menu";
    private MousePosition mouse;
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
	private Button playButton;
	private Button exitButton;
	private Button helpButton;
	private Button scoreButton;
	private Image title;

    private static final int MENU_TEXT_DRAW_X = 280;
    private static final int MENU_TEXT_DRAW_Y = 10;
    private static final int TITLE_IMAGE_DRAW_X = 190;
    private static final int TITLE_IMAGE_DRAW_Y = 50;
    private static final int PLAY_BUTTON_DRAW_X = 150;
    private static final int PLAY_BUTTON_DRAW_Y = 230;
    private static final int EXIT_BUTTON_DRAW_X = 150;
    private static final int EXIT_BUTTON_DRAW_Y = 375;
    private static final int HELP_BUTTON_DRAW_X = 580;
    private static final int HELP_BUTTON_DRAW_Y = 10;
	private static final int SCORE_BUTTON_DRAW_X = 580;
	private static final int SCORE_BUTTON_DRAW_Y = 90;

    /**
     * Constructor for the MenuState.
     * @param state the number of the state.
     */
    public MenuState(int state) {
        // Blank
    }

    /**
     * Initialize the game.
     * @param gc - the container holding the game.
     * @param sbg - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Main.actionLogger.logLine("Entered main menu", getClass().getSimpleName());
        playButton = new Button(PLAY_BUTTON_DRAW_X, PLAY_BUTTON_DRAW_Y, "resources/play-button.gif");
        exitButton = new Button(EXIT_BUTTON_DRAW_X, EXIT_BUTTON_DRAW_Y, "resources/exit-button.gif");
        helpButton = new Button(HELP_BUTTON_DRAW_X, HELP_BUTTON_DRAW_Y, "resources/gears.png");
        scoreButton = new Button(SCORE_BUTTON_DRAW_X, SCORE_BUTTON_DRAW_Y, "resources/trophy.png");
        background = new Image("resources/bg-menu.jpg");
        title = new Image("resources/something-fishy.png");
        mouse = new MousePosition();
    }

    /**
     * Notification that the game state has been entered.
     * @param gameContainer - the container holding the game.
     * @param stateBasedGame - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.enter(gameContainer, stateBasedGame);
		musicPlayer.loopSound(MusicPlayer.BG_MUSIC_MENU);
	}

    /**
     * Renders the game's screen.
     * @param gc - the container holding the game.
     * @param sbg - the game holding the state.
     * @param g - the graphics content used to render.
     * @throws SlickException - indicates internal error.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.drawImage(background, 0, 0); 
    	g.drawString(menu, MENU_TEXT_DRAW_X, MENU_TEXT_DRAW_Y);
    	g.drawImage(title.getScaledCopy(0.5f), TITLE_IMAGE_DRAW_X, TITLE_IMAGE_DRAW_Y);
    	scoreButton.draw(g);
    	playButton.draw(g);
    	exitButton.draw(g);
    	helpButton.draw(g);
    }

    /**
     * Update the game logic.
     * @param gc - the container holding the game.
     * @param sbg - the game holding the state.
     * @param delta - the amount of time that has
     * passed since last update in milliseconds.
     * @throws SlickException - indicates internal error.
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        mouse.updatePosition();
        menu = "(" + mouse.getPositionX() + "," + mouse.getPositionY() + ")";

        if (playButton.wasClickedBy(mouse)) {
            sbg.enterState(Main.LEVEL_STATE);
        }

        if (exitButton.wasClickedBy(mouse)) {
            Main.actionLogger.logLine("Game Closed!", getClass().getSimpleName());
            gc.exit();
        }

        if (helpButton.wasClickedBy(mouse)) {
            Main.actionLogger.logLine("HelpState opened.", getClass().getSimpleName());
            sbg.enterState(Main.HELP_STATE);
            HelpState.setPrevious(Main.MENU_STATE);
        }
        
        if (scoreButton.wasClickedBy(mouse)) {
	        Main.actionLogger.logLine("ScoreState opened.", getClass().getSimpleName());
	        sbg.enterState(ScoreState.STATE_ID);
        }

        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_ENTER)) {
            sbg.enterState(Main.LEVEL_STATE);
        }

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
        	gc.exit();
        }
    }

    /**
     * Notification that we're leaving this game state.
     * @param gameContainer - the container holding the game.
     * @param stateBasedGame - the game holding this state.
     * @throws SlickException - indicates internal error.
     */
    @Override
	public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		super.leave(gameContainer, stateBasedGame);
		musicPlayer.stopSound(MusicPlayer.BG_MUSIC_MENU);
	}

    /**
     * Get the ID of this state.
     * @return the unique ID of this state.
     */
    public int getID() {
        return Main.MENU_STATE;
    }
}
