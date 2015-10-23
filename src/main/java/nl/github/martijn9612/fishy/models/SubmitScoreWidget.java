package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.ScoreController;
import nl.github.martijn9612.fishy.position.DrawRectangle;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;

public class SubmitScoreWidget {
	private static int INPUT_WIDTH = 200;
	private static int BUTTON_WIDTH = 84;
	private static int WIDGET_HEIGHT = 25;
	private static int TEXT_OFFSET_Y = 1;
	private static int TEXT_SUBMIT_X = INPUT_WIDTH + 18;
	private static int TEXT_SUBMITTED_X = INPUT_WIDTH + 7;
	
	private TextField scoreInput;
	private DrawRectangle submitDR;
	private MouseRectangle submitMR;
	private boolean hasSubmitted = false;
	private boolean mouseOver = false;
	private TrueTypeFont textFont;
	private int x;
	private int y;
	
	public SubmitScoreWidget(GUIContext container, int x, int y) {
		this.x = x;
		this.y = y;
		createTextInput(container);
		submitDR = new DrawRectangle(x + INPUT_WIDTH + 2, y, BUTTON_WIDTH, WIDGET_HEIGHT);
		submitMR = submitDR.getMouseRectangle();
	}
	
	private void createTextInput(GUIContext container) {
		textFont = new TrueTypeFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN, 18), true);
		scoreInput = new TextField(container, textFont, x, y, INPUT_WIDTH, WIDGET_HEIGHT);
	}
	
	public void render(GUIContext container, Graphics g) {
		scoreInput.render(container, g);
		g.setColor(Color.gray);
		
		if(hasSubmitted) {
			g.fillRect(submitDR.getPositionX(), submitDR.getPositionY(), submitDR.getWidth(), submitDR.getHeight());
			textFont.drawString(x + TEXT_SUBMITTED_X, y + TEXT_OFFSET_Y, "Submitted", Color.white);
		} else {
			g.drawRect(submitDR.getPositionX(), submitDR.getPositionY(), submitDR.getWidth(), submitDR.getHeight());
			textFont.drawString(x + TEXT_SUBMIT_X, y + TEXT_OFFSET_Y, "Submit", mouseOver ? Color.black : Color.gray);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, MousePosition mouse) {
		mouseOver = mouse.isInRectangle(submitMR);
		
		if (mouseOver && mouse.isLeftButtonDown()) {
			String playerName = scoreInput.getText();
			if(!hasSubmitted && playerName.length() > 0) {
				ScoreController.getInstance().savedStoredScore(playerName);
				hasSubmitted = true;
			}
		}
    }
}
