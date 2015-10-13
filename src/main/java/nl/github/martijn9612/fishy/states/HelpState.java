package nl.github.martijn9612.fishy.states;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Skullyhoofd on 13/10/2015.
 * Implements the HelpState which shows instructions to the game.
 */
public class HelpState extends BasicGameState {

    private Image back;
    private Image background;
    private MousePosition mouse;
    private DrawRectangle backButtonDR;
    private MouseRectangle backButtonMR;
    public static int PREVIOUS_STATE = 0;

    private static final int BACK_BUTTON_DRAW_X = 10;
    private static final int BACK_BUTTON_DRAW_Y = 30;

    private static final String BACK_BUTTON_RESOURCE = "resources/back-button.png";


    /**
     * Constructor for the Help State.
     *
     * @param state - the number of the state
     */
    public HelpState(int state) {
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
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
        background.setAlpha(0.5f);
        back = new Image(BACK_BUTTON_RESOURCE);
        backButtonDR = new DrawRectangle(BACK_BUTTON_DRAW_X,
                BACK_BUTTON_DRAW_Y, back.getWidth(), back.getHeight());
        backButtonMR = backButtonDR.getMouseRectangle();
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
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        g.drawImage(back, backButtonDR.getPositionX(), backButtonDR.getPositionY());
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
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        mouse.updatePosition();

        if (mouse.isInRectangle(backButtonMR)) {
            if (mouse.isLeftButtonDown()) {
                game.enterState(PREVIOUS_STATE);
            }
        }

        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_P)) {
            game.enterState(PREVIOUS_STATE);
        }
    }

    /**
     * Getter for the previous state the game was in.
     * @return - The previous state
     */
    public static int getPrevious() {
        return PREVIOUS_STATE;
    }

    /**
     * Setter to set the previous state.
     * @param prev - The previous state
     */
    public static void setPrevious(int prev) {
        PREVIOUS_STATE = prev;
    }

    /**
     * Get the ID of this state.
     *
     * @return the unique ID of this state
     */
    @Override
    public int getID() {
        return Main.HELP_STATE;
    }
}
