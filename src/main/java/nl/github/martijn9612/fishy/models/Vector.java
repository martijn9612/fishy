package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.geom.Vector2f;

import nl.github.martijn9612.fishy.Main;

public strictfp class Vector extends Vector2f {

	private static final long serialVersionUID = -8617114641262854177L;

	public Vector(float x, float y) {
		super(new float[]{x, y});
	}
	
	public Vector copy() {
		return new Vector(x,y);
	}

	public void limit(float n) {
		x = Math.max(Math.min(x, n), -n);
		y = Math.max(Math.min(y, n), -n);
	}

	public static Vector centerOfScreen() {
		return new Vector(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 2);
	}

	public String toString() {
		return "(" + Math.round(x) + "," + Math.round(y) + ")";
	}
}
