package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;

public class MapObject
{
	public MapObject(WorldMap map, Vector2d position)
	{
		this.map = map;
		this.position = position;
	}

	public MapObject()
	{
		this.position = new Vector2d();
	}

	public Vector2d getPosition()
	{
		return new Vector2d(this.position);
	}
	Vector2d position;
	WorldMap map;
}
