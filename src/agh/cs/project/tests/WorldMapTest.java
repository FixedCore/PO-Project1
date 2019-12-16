package agh.cs.project.tests;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldMapTest
{
	@BeforeClass
	public void setup()
	{
		data = new InputData(new Vector2d(20,20), 0.25, 20, 1, 5, 4);
		map = new WorldMap(data);
	}

	@Test
	public void SpawnTest()
	{

	}
	private InputData data;
	private WorldMap map;
}
