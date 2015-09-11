package nl.github.martijn9612.fishy;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.junit.Test;

import junit.framework.Assert;

public class TestObject {
	
	public static final int X_VALUE = 10;
	public static final int Y_VALUE = 100;
	public static final int WIDTH = 19;
	public static final int HEIGHT = 76;
	
	
	@Test
	public void testSetPosition() {
		Object obj = new Object();
		obj.setPosition(X_VALUE, Y_VALUE);
		Assert.assertEquals(X_VALUE, obj.getX());
		Assert.assertEquals(Y_VALUE, obj.getY());
	}
	
	@Test
	public void testSetDimensions() {
		Object obj = new Object();
		obj.setDimensions(WIDTH, HEIGHT);
		Assert.assertEquals(WIDTH, obj.getWidth());
		Assert.assertEquals(HEIGHT, obj.getHeight());
	}
	
	@Test
	public void testCreateRectangle() {
		Object obj = new Object();
		obj.setPosition(X_VALUE, Y_VALUE);
		obj.setDimensions(WIDTH, HEIGHT);
		obj.calculateRectangle();
		Assert.assertTrue(obj.objectRect instanceof Rectangle);
		Dimension dim = obj.objectRect.getSize();
		Assert.assertEquals((int) dim.getWidth(), WIDTH);
		Assert.assertEquals((int) dim.getHeight(), HEIGHT);
	}

}
