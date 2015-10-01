package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the basic object in the game on which players and opponents are
 * based.
 */
public abstract class Entity {
	public Vector position;
	public Vector velocity;
	public Vector acceleration;
	public Vector dimensions;
	private boolean hasOpenGL;
	private Ellipse ellipse = null;
	private Image entityImage = null;
	protected MusicPlayer musicPlayer;

	/**
	 * Setup a new Entity object.
	 * 
	 * @param hasOpenGL
	 *            - whether openGL context can be invoked.
	 */
	public Entity(boolean hasOpenGL) {
		this.hasOpenGL = hasOpenGL;
	}

	/**
	 * Loads the sprite of the entity.
	 * 
	 * @param imagePath
	 *            - the resource path to the file in which the sprite is stored
	 */
	public void loadResources(String imagePath) {
		if (hasOpenGL) {
			try {
				entityImage = new Image(imagePath);
				musicPlayer = MusicPlayer.getInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Renders the object and the boundary box around it.
	 * 
	 * @param g
	 *            - the graphics content used to render
	 */
	public void renderObject(Graphics g) {
		if (hasOpenGL) {
			g.drawImage(getScaledImageCopy(), position.x, position.y);
			if (Main.DEBUG_MODE) {
				g.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
			}
		}
	}
	
    /**
     * Returns the size of the opponent.
     * @return size value
     */
    public float getSize() {
        return ((dimensions.x + dimensions.y) / 2);
    }
	
	public boolean intersects(Entity entity) {
		return ellipse.intersects(entity.getEllipse());
	}
	
	public Ellipse getEllipse() {
		return ellipse;
	}

	/**
	 * Calculates the boundary box around the entity with the right size and
	 * center.
	 */
	public void calculateBoundingbox() {
		float radiiX = (dimensions.x / 2);
		float radiiY = (dimensions.y / 2);

		if (ellipse == null) {
			ellipse = new Ellipse(position.x, position.y, radiiX, radiiY);
		} else {
			ellipse.setLocation(position);
			ellipse.setRadii(radiiX, radiiY);
		}
	}

	/**
	 * Creates a copy of the entity sprite and returns it.
	 * 
	 * @return Image - the scaled version of the entity image
	 */
	private Image getScaledImageCopy() {
		int width = Math.round(dimensions.x);
		int height = Math.round(dimensions.y);
		return entityImage.getScaledCopy(width, height);
	}

	/**
	 * Initial setup for the objectLogic of every object.
	 * 
	 * @param gc
	 *            - the container holding the game
	 * @param deltaTime
	 *            - amount of milliseconds passed since last logic call
	 */
	public abstract void objectLogic(GameContainer gc, int deltaTime);
}
