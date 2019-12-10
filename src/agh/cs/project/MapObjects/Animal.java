package agh.cs.project.MapObjects;

import agh.cs.project.map.WorldMap;
import agh.cs.project.movement.MapDirection;
import agh.cs.project.movement.Vector2d;

public class Animal extends MapObject
{
	public Animal(WorldMap map, Vector2d position, MapDirection rotation)
	{
		super(map, position);
		this.rotation = rotation;
	}

	public Animal(WorldMap map, Vector2d position)
	{
		super(map, position);
	}

	private MapDirection rotation;
	private int[] genome;
}
