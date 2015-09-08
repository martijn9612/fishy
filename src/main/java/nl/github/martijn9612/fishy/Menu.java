package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Skullyhoofd on 08/09/2015.
 */
public class Menu extends BasicGameState{
    public Menu(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawString("Are you ready to eat some fish?",100,100);
        Image play = new Image("resources/play-button.gif");
        g.drawImage(play,200,200);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    public int getID(){
        return 0;
    }
}
