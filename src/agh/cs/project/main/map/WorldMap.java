package agh.cs.project.main.map;

import agh.cs.project.main.map.managers.AnimalManager;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

public class WorldMap
{
	public WorldMap(InputData data)
	{
		this.data = data;
		this.zoo = new AnimalManager(this, data);
		zoo.spawnManyRandomAnimals(data.initialAnimalNumber);
	}


	public void move()
	{
		
	}

	public int getYear()
	{
		return year;
	}

	public boolean isInJungle(Vector2d v)
	{
		if(v.precedes(new Vector2d(0,0))) return false;
		return !v.follows(data.jungleSize);
	}

	int year;
	private AnimalManager zoo;
	private InputData data;
}
