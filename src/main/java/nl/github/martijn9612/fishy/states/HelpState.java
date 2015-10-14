package nl.github.martijn9612.fishy.states;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Skullyhoofd on 13/10/2015.
 * Implements the HelpState which shows instructions to the game.
 */
public class HelpState extends BasicGameState {

    private Image back;
    private Image background;
    private Image poison;
    private Image shield;
    private Image extralife;
    private Image speedup;
    private MousePosition mouse;
    private DrawRectangle backButtonDR;
    private MouseRectangle backButtonMR;
    private Font textFont;
    private Font titleFont;

    public static int PREVIOUS_STATE = 0;

    private static final int LINE_HEIGHT = 20;
    private static final int POWERUP_SIZE = 40;
    private static final int BACK_BUTTON_DRAW_X = 10;
    private static final int BACK_BUTTON_DRAW_Y = 30;
    private static final int INSTRUCTIONS_DRAW_X = 60;
    private static final int INSTRUCTIONS_DRAW_Y = 85;
    private static final String INSTRUCTIONS_TEXT = "This game is simple! Eat smaller " +
            "fish, don't get eaten by bigger fish. Slowly grow to be the biggest " +
            "fish in the ocean! Control your fish with the arrow keys or WASD. " +
            "Press P to pause.";
    private static final int BACK_DRAW_X = 70;
    private static final int BACK_DRAW_Y = 40;
    private static final String BACK_TEXT = "Back";
    private static final int POWERUP_DRAW_X = 350;
    private static final int POWERUP_DRAW_Y = 40;
    private static final String POWERUP_TEXT = "Power-ups:";
    private static final int POISON_DRAW_Y = 85;
    private static final int POISON_TEXT_DRAW_Y = 127;
    private static final String POISON_TEXT = "This is a poisonous mushroom! " +
            "Eating this will disorientate you and reverse your controls. " +
            "Avoid these if possible.";
    private static final int SHIELD_DRAW_Y = 190;
    private static final int SHIELD_TEXT_DRAW_Y = 232;
    private static final String SHIELD_TEXT = "This is a shield! " +
            "Eating this will protect you from dying for a little while. " +
            "Definitely try to get these! ";
    private static final int SPEEDUP_DRAW_Y = 300;
    private static final int SPEEDUP_TEXT_DRAW_Y = 347;
    private static final String SPEEDUP_TEXT = "This is a speed-up! " +
            "Eating this will increase your speed significantly. " +
            "Avoid these if possible. ";
    private static final int EXTRALIFE_DRAW_Y = 415;
    private static final int EXTRALIFE_TEXT_DRAW_Y = 457;
    private static final String EXTRALIFE_TEXT = "This is an extra life! " +
            "Eating this will allow you to be hit by a bigger fish one time. " +
            "Definitely try to get these! ";



    private static final String BACK_BUTTON_RESOURCE = "resources/back-button.png";
    private static final String POISON_RESOURCE = "resources/poison.png";
    private static final String EXTRALIFE_RESOURCE = "resources/ExtraLife-fish.png";
    private static final String SHIELD_RESOURCE = "resources/shield.png";
    private static final String SPEEDUP_RESOURCE = "resources/Speedup-fish.png";


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
     * @param gc
     *            - the container holding the game
     * @param game
     *            - the game holding the state
     * @throws SlickException
     *             - indicates internal error
     */
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
        background.setAlpha(0.2f);
        back = new Image(BACK_BUTTON_RESOURCE);
        poison = new Image(POISON_RESOURCE).getScaledCopy(POWERUP_SIZE,POWERUP_SIZE);
        shield = new Image(SHIELD_RESOURCE).getScaledCopy(POWERUP_SIZE,POWERUP_SIZE);
        speedup = new Image(SPEEDUP_RESOURCE).getScaledCopy(POWERUP_SIZE,POWERUP_SIZE);
        extralife = new Image(EXTRALIFE_RESOURCE).getScaledCopy(POWERUP_SIZE,POWERUP_SIZE);
        backButtonDR = new DrawRectangle(BACK_BUTTON_DRAW_X,
                BACK_BUTTON_DRAW_Y, back.getWidth(), back.getHeight());
        backButtonMR = backButtonDR.getMouseRectangle();
        mouse = new MousePosition();
        textFont = new TrueTypeFont(new java.awt.Font("Calibri",java.awt.Font.PLAIN , 16), true);
        titleFont = new TrueTypeFont(new java.awt.Font("Calibri",java.awt.Font.BOLD , 24), true);

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
        g.drawImage(background, 0, 0);
        g.drawImage(back, backButtonDR.getPositionX(), backButtonDR.getPositionY());
        String [] instructions = wrapText(INSTRUCTIONS_TEXT, 40);
        for (int i = 0; i < instructions.length; i++) {
            textFont.drawString(INSTRUCTIONS_DRAW_X, INSTRUCTIONS_DRAW_Y + i*LINE_HEIGHT, instructions[i], Color.white);
        }
        titleFont.drawString(BACK_DRAW_X, BACK_DRAW_Y, BACK_TEXT, new Color(70,175,230));
        titleFont.drawString(POWERUP_DRAW_X, POWERUP_DRAW_Y, POWERUP_TEXT, new Color(70,175,230));
        g.drawImage(poison, POWERUP_DRAW_X, POISON_DRAW_Y);
        String [] poisontext = wrapText(POISON_TEXT, 40);
        for (int i = 0; i < poisontext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, POISON_TEXT_DRAW_Y + i*LINE_HEIGHT, poisontext[i], Color.white);
        }
        g.drawImage(shield, POWERUP_DRAW_X, SHIELD_DRAW_Y);
        String [] shieldtext = wrapText(SHIELD_TEXT, 40);
        for (int i = 0; i < shieldtext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, SHIELD_TEXT_DRAW_Y + i*LINE_HEIGHT, shieldtext[i], Color.white);
        }
        g.drawImage(speedup, POWERUP_DRAW_X, SPEEDUP_DRAW_Y);
        String [] speeduptext = wrapText(SPEEDUP_TEXT, 40);
        for (int i = 0; i < speeduptext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, SPEEDUP_TEXT_DRAW_Y + i*LINE_HEIGHT, speeduptext[i], Color.white);
        }
        g.drawImage(extralife, POWERUP_DRAW_X, EXTRALIFE_DRAW_Y);
        String [] extralifetext = wrapText(EXTRALIFE_TEXT, 40);
        for (int i = 0; i < extralifetext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, EXTRALIFE_TEXT_DRAW_Y + i*LINE_HEIGHT, extralifetext[i], Color.white);
        }
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

    static String [] wrapText (String text, int len)
    {
        // return empty array for null text
        if (text == null)
            return new String [] {};

        // return text if len is zero or less
        if (len <= 0)
            return new String [] {text};

        // return text if less than length
        if (text.length() <= len)
            return new String [] {text};

        char [] chars = text.toCharArray();
        java.util.Vector lines = new Vector();
        StringBuilder line = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            word.append(chars[i]);
            if (chars[i] == ' ') {
                if ((line.length() + word.length()) > len) {
                    lines.add(line.toString());
                    line.delete(0, line.length());
                }
                line.append(word);
                word.delete(0, word.length());
            }
        }

        // handle any extra chars in current word
        if (word.length() > 0) {
            if ((line.length() + word.length()) > len) {
                lines.add(line.toString());
                line.delete(0, line.length());
            }
            line.append(word);
        }

        // handle extra line
        if (line.length() > 0) {
            lines.add(line.toString());
        }

        String [] ret = new String[lines.size()];
        int c = 0; // counter
        for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
            ret[c] = (String) e.nextElement();
        }

        return ret;
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
