package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.DrawRectangle;
import nl.github.martijn9612.fishy.models.MousePosition;
import nl.github.martijn9612.fishy.models.MouseRectangle;

/**
 * Created by Skullyhoofd on 08/09/2015.
 */
public class MenuState extends BasicGameState {
	
	public String menu = "Menu";
    
	private Image play;
	private Image exit;
	private MousePosition mouse;
	private DrawRectangle playButtonDR;
	private DrawRectangle exitButtonDR;
	private MouseRectangle playButtonMR;
	private MouseRectangle exitButtonMR;
	
	private static int MENU_TEXT_DRAW_X = 280;
	private static int MENU_TEXT_DRAW_Y = 10;
	private static int PLAY_BUTTON_DRAW_X = 150;
	private static int PLAY_BUTTON_DRAW_Y = 200;
	private static int EXIT_BUTTON_DRAW_X = 150;
	private static int EXIT_BUTTON_DRAW_Y = 375;

    public MenuState(int state) {
    	// Blank
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Main.actionLogger.logLine("Entered main menu", getClass().getSimpleName());
        play = new Image("resources/play-button.gif");
        exit = new Image("resources/exit-button.gif");
        playButtonDR = new DrawRectangle(PLAY_BUTTON_DRAW_X, PLAY_BUTTON_DRAW_Y, play.getWidth(), play.getHeight());
        exitButtonDR = new DrawRectangle(EXIT_BUTTON_DRAW_X, EXIT_BUTTON_DRAW_Y, play.getWidth(), play.getHeight());
        playButtonMR = playButtonDR.getMouseRectangle();
        exitButtonMR = exitButtonDR.getMouseRectangle();
        mouse = new MousePosition();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString(menu, MENU_TEXT_DRAW_X, MENU_TEXT_DRAW_Y);
        g.drawImage(play, playButtonDR.getPositionX(), playButtonDR.getPositionY());
        g.drawImage(exit, exitButtonDR.getPositionX(), exitButtonDR.getPositionY());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        mouse.updatePosition();
        menu = "("+mouse.getPositionX()+","+mouse.getPositionY()+")";
        
        if(mouse.isInRectangle(playButtonMR)) {
            if(mouse.isLeftButtonDown()) {
                sbg.enterState(Main.PLAY_STATE);
            }
        }
        
        if(mouse.isInRectangle(exitButtonMR)) {
        	if(mouse.isLeftButtonDown()) {
                Main.actionLogger.logLine("Game Closed!", getClass().getSimpleName());
                Main.actionLogger.close();
                System.exit(0);
            }
        }
        
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_ENTER)) {
        	sbg.enterState(Main.PLAY_STATE);
        }
        
        if(input.isKeyDown(Input.KEY_ESCAPE)) {
        	System.exit(0);
        }
    }

    public int getID() {
        return Main.MENU_STATE;
    }
}
