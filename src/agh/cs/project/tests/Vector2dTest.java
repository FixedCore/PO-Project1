package agh.cs.project.tests;

import agh.cs.project.main.movement.Vector2d;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vector2dTest
{
	@Test
	public void equalsTest()
	{
		Object o = new Object();
		Vector2d n = null;
		Vector2d v1 = new Vector2d(1,2);
		Vector2d v2 = new Vector2d(1,2);
		Vector2d v3 = new Vector2d(2,4);
		Integer i = 15;

		assert v1.equals(v2);
		assert !v1.equals(v3);
		assert !v2.equals(o);
		assert !v3.equals(n);
		assert !v1.equals(i);
	}

	@Test
	public void toStringTest()
	{
		Vector2d v1 = new Vector2d(5,4);
		Vector2d v2 = new Vector2d(-1,-256);
		assertEquals(v1.toString(), "(5,4)");
		assertEquals(v2.toString(), "(-1,-256)");
	}

	@Test
	public void precedesTest()
	{
		Vector2d v1 = new Vector2d(5,1);
		Vector2d v2 = new Vector2d(5,10);
		Vector2d v3 = new Vector2d(-1,20);
		assert v1.precedes(v2);
		assert !v1.precedes(v3);
		assert !v3.precedes(v1);
		assert !v2.precedes(v3);
	}

	@Test
	public void followsTest()
	{
		Vector2d v1 = new Vector2d(1,5);
		Vector2d v2 = new Vector2d(0,2);
		Vector2d v3 = new Vector2d(-1,20);
		assert v1.follows(v2);
		assert !v1.follows(v3);
		assert !v3.follows(v1);
		assert !v2.follows(v3);
	}

	@Test
	public void addTest()
	{
		Vector2d v1 = new Vector2d(1,5);
		Vector2d v2 = new Vector2d(0,27);
		Vector2d v3 = new Vector2d(-1,20);
		assertEquals(v1.add(v2), new Vector2d(1,32));
		assertEquals(v2.add(v3), new Vector2d(-1,47));
		assertEquals(v3.add(v1), new Vector2d(0,25));
	}

	@Test
	public void subtractTest()
	{
		Vector2d v1 = new Vector2d(1,5);
		Vector2d v2 = new Vector2d(0,27);
		Vector2d v3 = new Vector2d(-1,20);
		assertEquals(v1.subtract(v2), new Vector2d(1,-22));
		assertEquals(v2.subtract(v3), new Vector2d(1,7));
		assertEquals(v3.subtract(v1), new Vector2d(-2,15));
	}
}
