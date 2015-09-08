package nl.github.martijn9612.fishy;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.StateBasedTest;

public class Main extends StateBasedGame
{
	public static final String gameName = "Fishy";
    public static final int xSize = 650;
    public static final int ySize = 480;
    public static final int menu = 0;
    public static final int play = 1;

	public Main(String gamename)
	{
		super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }


	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main(gameName));
			appgc.setDisplayMode(xSize, ySize, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}