package agh.cs.project.main.map;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.MapObjects.MapObject;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.InputData;
import agh.cs.project.main.util.OptionsParser;

import java.util.List;
import java.util.Map;

public class WorldMap
{
	public WorldMap(InputData data)
	{
		this.data = data;
		animalCounter = 0;
	}

	public boolean isOn()
	{
		return animalCounter != 0;
	}

	public void move()
	{

	}

	private int animalCounter;
	private Map<Vector2d, List<MapObject>> objects;
	private InputData data;
}
