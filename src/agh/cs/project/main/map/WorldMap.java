package agh.cs.project.main.map;

import agh.cs.project.main.MapObjects.MapObject;
import agh.cs.project.main.map.managers.AnimalManager;
import agh.cs.project.main.map.managers.BreedingManager;
import agh.cs.project.main.map.managers.EatingManager;
import agh.cs.project.main.map.managers.GrassManager;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

public class WorldMap
{
	public WorldMap(InputData data)
	{
		this.data = data;
		this.zoo = new AnimalManager(this, data);
		this.garden = new GrassManager(this, data);
		this.diner = new EatingManager(this, data, garden, zoo);
		this.brothel = new BreedingManager(this, data, zoo);
		this.artist = new MapVisualizer(this);
		zoo.spawnManyRandomAnimals(data.initialAnimalNumber);
	}

	public void run()
	{
		zoo.cleanDeadAnimals();
		zoo.letAnimalsMove();
		diner.feedAllAnimals();
		brothel.breedAllAnimals();
		garden.spawnGrassesInBothAreas();
		System.out.println(artist.draw(new Vector2d(0,0), data.mapSize));
		year += 1;
	}

	public int getYear()
	{
		return year;
	}

	public boolean isPopulated()
	{
		return zoo.getAnimalCount() > 0;
	}

	public boolean isInJungle(Vector2d v)
	{
		if(v.precedes(new Vector2d(0,0))) return false;
		return v.precedes(data.jungleSize);
	}

	public boolean isOccupied(Vector2d pos)
	{
		return zoo.hasAnimalAt(pos) || garden.hasGrassAt(pos);
	}

	public Object objectAt(Vector2d pos)
	{
		if(zoo.hasAnimalAt(pos)) return zoo.getDominantAnimal(pos);
		if(garden.hasGrassAt(pos)) return garden.getGrassAt(pos);
		return null;
	}

	int year;
	private AnimalManager zoo;
	private GrassManager garden;
	private EatingManager diner;
	private BreedingManager brothel;
	private MapVisualizer artist;
	private InputData data;
}
