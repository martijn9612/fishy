package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Ellipse;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Vector;

public class TestObject extends TestCase {
	
	public static final float X_VALUE = 10;
	public static final float Y_VALUE = 100;
	public static final float WIDTH = 19;
	public static final float HEIGHT = 76;
	
	@Test
	public void testSetPosition() {
		Entity entity = mock(Entity.class);
		entity.position = new Vector(X_VALUE, Y_VALUE);
		assertEquals(X_VALUE, entity.position.x, 0.1);
		assertEquals(Y_VALUE, entity.position.y, 0.1);
	}
	
	@Test
	public void testSetDimensions() {
		Entity entity = mock(Entity.class);
		entity.dimensions = new Vector(WIDTH, HEIGHT);
		assertEquals(WIDTH, entity.dimensions.x, 0.1);
		assertEquals(HEIGHT, entity.dimensions.y, 0.1);
	}
	
	@Test
	public void testCreateEllipse() {
		Entity entity = mock(Entity.class, Mockito.CALLS_REAL_METHODS);
		entity.position = new Vector(X_VALUE, Y_VALUE);
		entity.dimensions = new Vector(WIDTH, HEIGHT);
		entity.updateBoundingbox();
		assertTrue(entity.getEllipse() instanceof Ellipse);
		float width = entity.getEllipse().getWidth();
		float height = entity.getEllipse().getHeight();
		assertEquals(width, WIDTH, 0.1);
		assertEquals(height, HEIGHT, 0.1);
	}

}
