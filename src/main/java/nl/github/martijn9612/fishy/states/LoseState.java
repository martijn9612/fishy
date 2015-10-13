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
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;

/**
 * Implements the Lose State of the game.
 */
public class LoseState extends BasicGameState {
    private Image play;
    private Image deadFish;
    private MousePosition mouse;
    private DrawRectangle playButtonDR;
    private MouseRectangle playButtonMR;

    private static final int PLAY_BUTTON_DRAW_X = 150;
    private static final int PLAY_BUTTON_DRAW_Y = 350;
    private static final int DEAD_FISH_DRAW_X = 220;
    private static final int DEAD_FISH_DRAW_Y = 150;
    private static final int DEAD_TEXT_DRAW_X = 230;
    private static final int DEAD_TEXT_DRAW_Y = 100;

    private static final String DEAD_TEXT_STRING = "Urr a dead fish! :(";
    private static final String PLAY_BUTTON_RESOURCE = "resources/play-button.gif";
    private static final String DEAD_FISH_RESOURCE = "resources/dead-fish.png";

    /**
     * Constructor for the Lose State.
     * 
     * @param state
     *            - the number of the state
     */
    public LoseState(int state) {
        // Blank
    }

    /**
     * Initialize the game.
     * 
     * @param container
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @throws SlickException
     *             - indicates internal error
     */
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        play = new Image(PLAY_BUTTON_RESOURCE);
        deadFish = new Image(DEAD_FISH_RESOURCE);
        playButtonDR = new DrawRectangle(PLAY_BUTTON_DRAW_X,
                PLAY_BUTTON_DRAW_Y, play.getWidth(), play.getHeight());
        playButtonMR = playButtonDR.getMouseRectangle();
        mouse = new MousePosition();
    }

    /**
     * Renders the game's screen.
     * 
     * @param gc
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @param g
     *            - the graphics content used to render
     * @throws SlickException
     *             - indicates internal error
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setColor(Color.white);
        g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        g.drawImage(play, playButtonDR.getPositionX(),
                playButtonDR.getPositionY());
        g.setColor(Color.black);
        g.drawString(DEAD_TEXT_STRING, DEAD_TEXT_DRAW_X, DEAD_TEXT_DRAW_Y);
        g.drawImage(deadFish, DEAD_FISH_DRAW_X, DEAD_FISH_DRAW_Y);
    }

    /**
     * Update the game logic.
     * 
     * @param gc
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @param delta
     *            - the amount of time that has passed since last update in
     *            milliseconds
     * @throws SlickException
     *             - indicates internal error
     */
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
        mouse.updatePosition();

        if (mouse.isInRectangle(playButtonMR)) {
            if (mouse.isLeftButtonDown()) {
                game.enterState(Main.LEVEL_STATE);
            }
        }

        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE)) {
            game.enterState(Main.LEVEL_STATE);
        }
    }

    /**
     * Get the ID of this state.
     * 
     * @return the unique ID of this state
     */
    @Override
    public int getID() {
        return Main.GAME_LOSE_STATE;
    }

}
