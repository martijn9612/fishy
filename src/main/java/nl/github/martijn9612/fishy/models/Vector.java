package nl.github.martijn9612.fishy.models;

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
	
	public void limit(double n) {
		x = Math.max(Math.min(x, n), -n);
		y = Math.max(Math.min(y, n), -n);
	}
	
	public Vector get() {
		return new Vector(x, y);
	}
}
