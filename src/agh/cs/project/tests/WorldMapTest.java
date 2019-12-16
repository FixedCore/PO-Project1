package agh.cs.project.tests;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorldMapTest
{
	@BeforeClass
	public static void setup()
	{
		data = new InputData(new Vector2d(20,20), 0.25, 20, 1, 5);
		map = new WorldMap(data,10);
	}

	@Test
	public void SpawnTest()
	{
		assertTrue(map.isPopulated());
	}
	private static InputData data;
	private static WorldMap map;
}
