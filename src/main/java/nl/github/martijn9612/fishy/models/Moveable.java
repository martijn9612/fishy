package nl.github.martijn9612.fishy.models;

public class Moveable {
	public Vector position = new Vector(0, 0);
	public Vector velocity = new Vector(0, 0);
	public Vector acceleration = new Vector(0, 0);
	public Vector dimensions = new Vector(0, 0);
	public float mass = 1;

    /**
     * Updates the position of the player according to grandpa Newton.
     */
    public void updatePosition(float maxSpeed) {
    	velocity.add(acceleration);
    	velocity.limit(maxSpeed);
    	position.add(velocity);
    	acceleration.scale(0);
    }
    
    /**
     * Calculates the drag force the water applies to the player.
     */
    public void applyWaterDrag(float waterDragCoefficient) {
		float speed = velocity.length();
		float dragMagnitude = waterDragCoefficient * speed * speed;
		Vector drag = velocity.copy();
		drag.negateLocal();
		drag.normalise();
		drag.scale(dragMagnitude);
		applyForce(drag);
	}
    
    /**
     * Apply a force to the player, according to force = mass * acceleration;
     * 
     * @param force
     */
    public void applyForce(Vector force) {
		Vector newForce = force.copy();
		newForce.scale(1 / mass);
		acceleration.add(newForce);
	}
}
