package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;

public class Grass extends MapObject
{
	public Grass(WorldMap map, Vector2d pos)
	{
		super(map, pos);
	}

	@Override
	public String toString()
	{
		return "* ";
	}
}
