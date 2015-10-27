package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the basic object in the game on which players and opponents are
 * based. It provides methods for updating the bounding box and rendering (a
 * scaled copy of) itself.
 * Software Engineering Methods Project - Group 11.
 */
public abstract class Entity {
	public static final int IMAGE_ORIENTATE_LEFT = 0;
	public static final int IMAGE_ORIENTATE_RIGHT = 1;
	protected MusicPlayer musicPlayer;
	protected boolean hasOpenGL;
	private Moveable data;
	private int orientation = IMAGE_ORIENTATE_LEFT;
	private Vector oldDimensions;
	private Image originalImage;
	private Image scaledImage;
	private Ellipse boundingBox;

	/**
	 * Setup a new Entity object.
	 * @param data - the Moveable data of the new entity.
	 * @param hasOpenGL - true if OpenGL context should be loaded, false if not.
	 */
	public Entity(Moveable data, boolean hasOpenGL) {
		this.setData(data);
		this.oldDimensions = new Vector(-1,-1);
		this.hasOpenGL = hasOpenGL;
		updateBoundingbox();
	}

	/**
	 * Loads the resources associated with this entity. These are
	 * the given sprite location and the musicPlayer instance. Also,
	 * a scaled copy of the sprite is made related to the dimensions.
	 * @param imagePath - resource path to filesystem location of the sprite.
	 */
	public void loadResources(String imagePath) {
		if (hasOpenGL) {
			try {
				originalImage = new Image(imagePath);
				musicPlayer = MusicPlayer.getInstance();
				getScaledImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads the image associated with this entity. 
	 * @param imagePath - resource path to filesystem location of the sprite.
	 */
	public void loadImage(String imagePath) {
		try {
			originalImage = new Image(imagePath);
			if (orientation != 0) {
                originalImage = originalImage.getFlippedCopy(true, false);
			}
			getScaledImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Set the orientation of the sprite. When the orientation changes,
	 * a new copy is made by flipping the y axis and will be stored.
	 * @param newOrientation - integer defining the orientation.
	 */
	public void setImageOrientation(int newOrientation) {
		if (orientation != newOrientation) {
			scaledImage = scaledImage.getFlippedCopy(true, false);
			originalImage = originalImage.getFlippedCopy(true, false);
			orientation = newOrientation;
		}
    }

	/**
	 * Renders the object and the boundary box when enabled.
	 * @param g - the graphics content used to render.
	 */
	public void renderObject(Graphics g) {
        if (hasOpenGL) {
			Image image = getScaledImage();
			g.drawImage(image, getData().getPosition().x, getData().getPosition().y);
			if (Main.DEBUG_MODE) {
				g.drawOval(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
			}
		}
	}

	/**
	 * Returns the size of the opponent.
	 * @return size value.
	 */
	public float getSize() {
		return ((getData().getDimensions().x + getData().getDimensions().y) / 2);
	}

	/**
	 * Checks whether the bounding boxes of two entities collide.
	 * @param entity - the instance to check for.
	 * @return boolean, true if they collide, false if not.
	 */
	public boolean intersects(Entity entity) {
		return boundingBox.intersects(entity.getEllipse());
	}

	/**
	 * Get the bounding box ellipse instance and returns it.
	 * @return Ellipse - the bounding box.
	 */
	public Ellipse getEllipse() {
		return boundingBox;
	}

	/**
	 * Updates the bounding box around the entity according to the dimensions.
	 * When the bounding box wasn't calculated before, a new Ellipse instance is
	 * created to act as bounding box.
	 */
	public void updateBoundingbox() {
		float radiiX = (getData().getDimensions().x / 2);
		float radiiY = (getData().getDimensions().y / 2);

		if (boundingBox == null) {
			boundingBox = new Ellipse(getData().getPosition().x, getData().getPosition().y, radiiX, radiiY);
		} else {
			boundingBox.setLocation(getData().getPosition());
			boundingBox.setRadii(radiiX, radiiY);
		}
    }

	/**
	 * Get a scaled version of the image sprite. When the dimensions of an
	 * entity changes, a new copy from the original sprite is created and
	 * returned.
	 * @return Image - a scaled version of the entity image.
	 */
	private Image getScaledImage() {
		if (!oldDimensions.equals(getData().getDimensions())) {
			int width = Math.round(getData().getDimensions().x);
			int height = Math.round(getData().getDimensions().y);
            scaledImage = originalImage.getScaledCopy(width, height);
			oldDimensions = getData().getDimensions();
		}
        return scaledImage;
	}

	/**
	 * Execute the logic calculations associated with this entity.
	 * @param gc - the container holding the game.
	 * @param deltaTime - milliseconds passed since last call.
	 */
	public abstract void objectLogic(GameContainer gc, int deltaTime);

	/**
	 * Gets the bounding box of the given entity.
	 * Method for testing purposes.
	 * @return Ellipse - the bounding box.
	 */
	public Ellipse getBoundingBox() {
	    return boundingBox;
	}

	public Moveable getData() {
		return data;
	}

	public void setData(Moveable data) {
		this.data = data;
	}
}
