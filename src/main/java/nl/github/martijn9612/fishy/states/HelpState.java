package nl.github.martijn9612.fishy.states;

import java.util.Enumeration;
import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;

/**
 * Implements the HelpState which shows instructions to the game.
 * Software Engineering Methods Project - Group 11.
 */
public class HelpState extends BasicGameState {

    private Image back;
    private Image background;
    private Image poison;
    private Image shield;
    private Image extralife;
    private Image speedup;
    private Image fish;
    private Image squid;
    private Image whale;
    private MousePosition mouse;
    private DrawRectangle backButtonDR;
    private MouseRectangle backButtonMR;
    private Font textFont;
    private Font titleFont;
    private Font introFont;
    private Color myBlue = new Color(70, 175, 230);

    public static int PREVIOUS_STATE = 0;
    public static final int STATE_ID = 4;

    private static final int LINE_HEIGHT = 20;
    private static final int WRAP_LENGTH = 40;
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
    private static final String ENEMIES_TEXT = "Enemies:";
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
    private static final int SQUID_DRAW_X = 105;
    private static final int SQUID_DRAW_Y = 220;
    private static final String ENEMIES_DESCRIPTION = "These are the standard " +
            "enemies. The fish swim across the screen horizontally while the " +
            "squid swim from the bottom up.";
    private static final int ENEMIES_TEXT_DRAW_Y = 265;
    private static final String WHALE_TEXT = "This is a special enemy, the whale," +
            " which has a small chance of spawning following a short warning. " +
            "Whales are huge and cannot be eaten.";
    private static final int WHALE_TEXT_DRAW_Y = 430;

    private static final String BACK_BUTTON_RESOURCE = "resources/back-button.png";
    private static final String POISON_RESOURCE = "resources/poison.png";
    private static final String EXTRALIFE_RESOURCE = "resources/ExtraLife-fish.png";
    private static final String SHIELD_RESOURCE = "resources/shield.png";
    private static final String SPEEDUP_RESOURCE = "resources/Speedup-fish.png";
    private static final String FISH_RESOURCE = "resources/opponent-fish.png";
    private static final String SQUID_RESOURCE = "resources/squid.png";
    private static final String WHALE_RESOURCE = "resources/whale.png";

    /**
     * Initialize the game.
     * @param gc - the container holding the game.
     * @param game - the game holding the state.
     * @throws SlickException - indicates internal error.
     */
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
        background.setAlpha(0.1f);
        back = new Image(BACK_BUTTON_RESOURCE);
        poison = new Image(POISON_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        shield = new Image(SHIELD_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        speedup = new Image(SPEEDUP_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        extralife = new Image(EXTRALIFE_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        fish = new Image(FISH_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        squid = new Image(SQUID_RESOURCE).getScaledCopy(POWERUP_SIZE, POWERUP_SIZE);
        whale = new Image(WHALE_RESOURCE).getScaledCopy(POWERUP_SIZE * 2, POWERUP_SIZE * 2);
        backButtonDR = new DrawRectangle(BACK_BUTTON_DRAW_X,
                BACK_BUTTON_DRAW_Y, back.getWidth(), back.getHeight());
        backButtonMR = backButtonDR.getMouseRectangle();
        mouse = new MousePosition();
        textFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN , 16), true);
        titleFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.BOLD , 24), true);
        introFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.BOLD , 16), true);
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
		Main.actionLogger.logLine("Entering HelpState", getClass().getSimpleName());
	}

    /**
     * Renders the game's screen.
     * @param gc - the container holding the game.
     * @param game - the game holding the state.
     * @param g - the graphics content used to render.
     * @throws SlickException - indicates internal error.
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        g.drawImage(background, 0, 0);

        renderBackButton(g);
        renderInstructions(g);
        renderEnemies(g);
        renderPowerups(g);
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

        if (mouse.isInRectangle(backButtonMR)) {
            if (mouse.isLeftButtonDown()) {
                game.enterState(getPrevious());
            }
        }

        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_P)) {
            game.enterState(getPrevious());
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
		Main.actionLogger.logLine("Leaving HelpState", getClass().getSimpleName());
	}

    /**
     * Make sure text overflow works properly.
     * @param text - the text to be wrapped.
     * @param len - max length of the text in characters.
     * @return Array of individual lines.
     */
    static String[] wrapText(String text, int len) {
        // return empty array for null text
        if (text == null) {
            return new String[]{};
        }

        // return text if len is zero or less
        if (len <= 0) {
            return new String[] {text};
        }

        // return text if less than length
        if (text.length() <= len) {
            return new String[]{text};
        }

        char[] chars = text.toCharArray();
        Vector<String> lines = new Vector<String>();
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
        for (Enumeration<String> e = lines.elements(); e.hasMoreElements(); c++) {
            ret[c] = (String) e.nextElement();
        }

        return ret;
    }

    /**
     * Getter for the previous state the game was in.
     * @return The previous state.
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
     * @return the unique ID of this state.
     */
    @Override
    public int getID() {
        return STATE_ID;
    }
    
    /**
     * Renders the back button in the HelpState screen.
     * @param g - the graphics content to render.
     */
    private void renderBackButton(Graphics g) {
        g.drawImage(back, backButtonDR.getPositionX(), backButtonDR.getPositionY());
        titleFont.drawString(BACK_DRAW_X, BACK_DRAW_Y, BACK_TEXT, myBlue);
    }
    
    /**
     * Renders the game's instructions in the screen.
     * @param g - the graphics content to render.
     */
    private void renderInstructions(Graphics g) {
        String [] instructions = wrapText(INSTRUCTIONS_TEXT, WRAP_LENGTH);
        for (int i = 0; i < instructions.length; i++) {
            introFont.drawString(INSTRUCTIONS_DRAW_X, INSTRUCTIONS_DRAW_Y + i * LINE_HEIGHT, instructions[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the different enemies.
     * @param g - the graphics content to render.
     */
    private void renderEnemies(Graphics g) {
        titleFont.drawString(INSTRUCTIONS_DRAW_X, SHIELD_DRAW_Y, ENEMIES_TEXT, myBlue);
        renderEnemiesFishSquid(g);
        renderEnemiesWhale(g);
    }
    
    /**
     * Renders the information about the different powerups.
     * @param g - the graphics content to render.
     */
    private void renderPowerups(Graphics g) {
        titleFont.drawString(POWERUP_DRAW_X, POWERUP_DRAW_Y, POWERUP_TEXT, myBlue);
        renderPowerupPoison(g);
        renderPowerupShield(g);
        renderPowerupSpeedup(g);
        renderPowerupExtraLife(g);
    }
    
    /**
     * Renders the information about the powerup poison.
     * @param g - the graphics content to render.
     */
    private void renderPowerupPoison(Graphics g) {
        g.drawImage(poison, POWERUP_DRAW_X, POISON_DRAW_Y);
        String [] poisontext = wrapText(POISON_TEXT, WRAP_LENGTH);
        for (int i = 0; i < poisontext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, POISON_TEXT_DRAW_Y + i * LINE_HEIGHT, poisontext[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the powerup shield.
     * @param g - the graphics content to render.
     */
    private void renderPowerupShield(Graphics g) {
        g.drawImage(shield, POWERUP_DRAW_X, SHIELD_DRAW_Y);
        String [] shieldtext = wrapText(SHIELD_TEXT, WRAP_LENGTH);
        for (int i = 0; i < shieldtext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, SHIELD_TEXT_DRAW_Y + i * LINE_HEIGHT, shieldtext[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the powerup speedup.
     * @param g - the graphics content to render.
     */
    private void renderPowerupSpeedup(Graphics g) {
        g.drawImage(speedup, POWERUP_DRAW_X, SPEEDUP_DRAW_Y);
        String [] speeduptext = wrapText(SPEEDUP_TEXT, WRAP_LENGTH);
        for (int i = 0; i < speeduptext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, SPEEDUP_TEXT_DRAW_Y + i * LINE_HEIGHT, speeduptext[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the powerup extralife.
     * @param g - the graphics content to render.
     */
    private void renderPowerupExtraLife(Graphics g) {
        g.drawImage(extralife, POWERUP_DRAW_X, EXTRALIFE_DRAW_Y);
        String [] extralifetext = wrapText(EXTRALIFE_TEXT, WRAP_LENGTH);
        for (int i = 0; i < extralifetext.length; i++) {
            textFont.drawString(POWERUP_DRAW_X, EXTRALIFE_TEXT_DRAW_Y + i * LINE_HEIGHT, extralifetext[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the fish and squid enemies.
     * @param g - the graphics content to render.
     */
    private void renderEnemiesFishSquid(Graphics g) {
        g.drawImage(fish, INSTRUCTIONS_DRAW_X, SQUID_DRAW_Y);
        g.drawImage(squid, SQUID_DRAW_X, SQUID_DRAW_Y);
        String [] enemiestext = wrapText(ENEMIES_DESCRIPTION, WRAP_LENGTH);
        for (int i = 0; i < enemiestext.length; i++) {
            textFont.drawString(INSTRUCTIONS_DRAW_X, ENEMIES_TEXT_DRAW_Y + i * LINE_HEIGHT, enemiestext[i], Color.white);
        }
    }
    
    /**
     * Renders the information about the whale enemy.
     * @param g - the graphics content to render.
     */
    private void renderEnemiesWhale(Graphics g) {
        g.drawImage(whale, INSTRUCTIONS_DRAW_X, SPEEDUP_TEXT_DRAW_Y);
        String [] whaletext = wrapText(WHALE_TEXT, WRAP_LENGTH);
        for (int i = 0; i < whaletext.length; i++) {
            textFont.drawString(INSTRUCTIONS_DRAW_X, WHALE_TEXT_DRAW_Y + i * LINE_HEIGHT, whaletext[i], Color.white);
        }
    }
}
