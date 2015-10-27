package nl.github.martijn9612.fishy.models;

/**
 * Implements the moveable object that contains the position, velocity,
 * acceleration, dimensions and mass of an object. 
 * Software Engineering Methods Project - Group 11.
 */
public class Moveable {
	private Vector position = new Vector(0, 0);
	private Vector velocity = new Vector(0, 0);
	private Vector dimensions = new Vector(0, 0);
	private Vector acceleration = new Vector(0, 0);
	private float mass = 1;

    /**
     * Updates the position of the player according to Newton standards.
     */
    public void updatePosition(float maxSpeed) {
    	getVelocity().add(getAcceleration());
    	getVelocity().limit(maxSpeed);
    	getPosition().add(getVelocity());
    	getAcceleration().scale(0);
    }
    
    /**
     * Calculates the drag force the water applies to the player.
     * @param waterDragCoefficient - the amount of drag foce the water applies.
     */
    public void applyWaterDrag(float waterDragCoefficient) {
		float speed = getVelocity().length();
		float dragMagnitude = waterDragCoefficient * speed * speed;
		Vector drag = getVelocity().copy();
		drag.negateLocal();
		drag.normalise();
		drag.scale(dragMagnitude);
		applyForce(drag);
	}
    
    /**
     * Applies a force to the player, according to force = mass * acceleration;
     * @param force - Force to be applied to the player.
     */
    public void applyForce(Vector force) {
		Vector newForce = force.copy();
		newForce.scale(1 / getMass());
		getAcceleration().add(newForce);
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getDimensions() {
		return dimensions;
	}

	public void setDimensions(Vector dimensions) {
		this.dimensions = dimensions;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
}
