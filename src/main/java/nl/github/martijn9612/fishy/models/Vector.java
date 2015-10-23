package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.geom.Vector2f;

import nl.github.martijn9612.fishy.Main;

/**
 * Implements the vector used to represent the position,
 * dimension, acceleration and velocity.
 * Software Engineering Methods Project - Group 11.
 */
public strictfp class Vector extends Vector2f {

	private static final long serialVersionUID = -8617114641262854177L;

	/**
	 * Constructor creates a new Vector, based on the Vector2f class.
	 * @param x - x value of the new Vector.
	 * @param y - y value of the new Vector.
	 */
	public Vector(float x, float y) {
		super(new float[]{x, y});
	}
	
	/**
	 * Returns a new Vector with the current x and y values.
	 * @return new vector with the current x and y values.
	 */
	public Vector copy() {
		return new Vector(x, y);
	}

	/**
	 * Changes the x and y values of the vector to the limit values.
	 * @param n - limit value.
	 */
	public void limit(float n) {
		x = Math.max(Math.min(x, n), -n);
		y = Math.max(Math.min(y, n), -n);
	}

	/**
	 * Returns a Vector with as x and y the center of the screen.
	 * @return Vector with x and y values center of sceen values.
	 */
	public static Vector centerOfScreen() {
		return new Vector(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 2);
	}

	/**
	 * Gives the string representation of the Vector.
	 * @return string representation of Vector.
	 */
	public String toString() {
		return "(" + Math.round(x) + "," + Math.round(y) + ")";
	}
}
