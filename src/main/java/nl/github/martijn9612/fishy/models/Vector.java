package nl.github.martijn9612.fishy.models;

import org.lwjgl.opengl.Display;

public class Vector {

	public double x;
	public double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector vector) {
		x += vector.x;
		y += vector.y;
	}

	public void mult(double n) {
		x = x * n;
		y = y * n;
	}

	public void div(double n) {
		x = x / n;
		y = y / n;
	}

	public double mag() {
		return Math.sqrt(x * x + y * y);
	}
	
	public void normalize() {
		double m = mag();
		if (m != 0) {
			div(m);
		}
	}

	public void limit(double n) {
		x = Math.max(Math.min(x, n), -n);
		y = Math.max(Math.min(y, n), -n);
	}

	public Vector get() {
		return new Vector(x, y);
	}

	public static Vector centerOfScreen() {
		return new Vector(Display.getWidth() / 2, Display.getHeight() / 2);
	}
}
