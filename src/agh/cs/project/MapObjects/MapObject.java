package agh.cs.project.MapObjects;

import agh.cs.project.map.WorldMap;
import agh.cs.project.movement.Vector2d;

public class MapObject
{
	public MapObject()
	{
		this.position = new Vector2d();
	}

	public MapObject(WorldMap map, Vector2d position)
	{
		this.map = map;
		this.position = position;
	}

	public Vector2d getPosition()
	{
		return new Vector2d(this.position);
	}
	private Vector2d position;
	private WorldMap map;
}
