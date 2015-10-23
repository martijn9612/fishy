package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;

public class Button extends Image {
	private DrawRectangle drawRectangle;
	private MouseRectangle mouseRectangle;
	
	public Button(int x, int y, String resource) throws SlickException {
		super(resource);
		drawRectangle = new DrawRectangle(x, y, getWidth(), getHeight());
		mouseRectangle = drawRectangle.getMouseRectangle();
	}
	
	public boolean wasClickedBy(MousePosition mouse) {
		return (mouse.isInRectangle(mouseRectangle) && mouse.isLeftButtonDown());
	}
	
	public void draw(Graphics g) {
		g.drawImage(this, drawRectangle.getPositionX(), drawRectangle.getPositionY());
	}

}
