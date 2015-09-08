package nl.github.martijn9612.fishy;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Skullyhoofd on 08/09/2015.
 */
public class Menu extends BasicGameState{
    public String menu = "Menu";
    Image play;
    Image exit;
    int xPlay = 150;
    int yPlay = 200;

    public Menu(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        play = new Image("resources/play-button.gif");
        exit = new Image("resources/exit-button.gif");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawString(menu,300,10);
        g.drawImage(play,xPlay,yPlay);
        g.drawImage(exit,150,375);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input = gc.getInput();
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        if((xPos>150 && xPos<450) && (yPos>200 && yPos<350)){
            if(input.isMouseButtonDown(0)){
                sbg.enterState(1);
            }
        }
        if((xPos>150 && xPos<450) && (yPos>375 && yPos<525)){
            if(input.isMouseButtonDown(0)){
                System.exit(0);
            }
        }
    }

    public int getID(){
        return 0;
    }
}
