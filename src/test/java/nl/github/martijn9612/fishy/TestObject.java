package nl.github.martijn9612.fishy;

import org.junit.Assert;
import org.junit.Test;

public class TestObject {
	
	public static final int X_VALUE = 10;
	public static final int Y_VALUE = 100;
	public static final int WIDTH = 19;
	public static final int HEIGHT = 76;
	
	@Test
	public void testSetPosition() {
		Entity entity = new Entity();
		entity.setPosition(X_VALUE, Y_VALUE);
		Assert.assertEquals(X_VALUE, entity.getX());
		Assert.assertEquals(Y_VALUE, entity.getY());
	}
	
	@Test
	public void testSetDimensions() {
		Entity entity = new Entity();
		entity.setDimensions(WIDTH, HEIGHT);
		Assert.assertEquals(WIDTH, entity.getWidth());
		Assert.assertEquals(HEIGHT, entity.getHeight());
	}
	
//	@Test
//	public void testCreateRectangle() {
//		Entity entity = new Entity();
//		entity.setPosition(X_VALUE, Y_VALUE);
//		entity.setDimensions(WIDTH, HEIGHT);
//		entity.calculateBoundingbox();
//		Assert.assertTrue(entity.objectRect instanceof Rectangle);
//		Dimension dimension = entity.objectRect.getSize();
//		Assert.assertEquals((int) dimension.getWidth(), WIDTH);
//		Assert.assertEquals((int) dimension.getHeight(), HEIGHT);
//	}

}
