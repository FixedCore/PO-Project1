package agh.cs.project.main.map;

import agh.cs.project.main.map.managers.AnimalManager;
import agh.cs.project.main.map.managers.BreedingManager;
import agh.cs.project.main.map.managers.EatingManager;
import agh.cs.project.main.map.managers.GrassManager;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;
import agh.cs.project.main.util.input.InputData;
import agh.cs.project.main.viewer.MapVisualizer;

public class WorldMap
{
	public WorldMap(InputData data, int initialAnimalNumber)
	{
		this.data = data;
		this.initJungleBounds();
		this.zoo = new AnimalManager(this, data);
		this.garden = new GrassManager(this, data);
		this.diner = new EatingManager(this, data, garden, zoo);
		this.brothel = new BreedingManager(this, data, zoo);
		this.artist = new MapVisualizer(this);
		zoo.spawnManyRandomAnimals(initialAnimalNumber);
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

	private void initJungleBounds()
	{
		Vector2d center = new Vector2d(data.mapSize.x / 2,  data.mapSize.y / 2);
		Vector2d jungleRadius = new Vector2d(data.jungleSize.x / 2, data.jungleSize.y / 2);
		jungleLowerLeftBound = center.subtract(jungleRadius);
		jungleUpperRightBound = center.add(jungleRadius);
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
		return v.follows(jungleLowerLeftBound) && v.precedes(jungleUpperRightBound);
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

	private int year;
	private AnimalManager zoo;
	private GrassManager garden;
	private EatingManager diner;
	private BreedingManager brothel;
	private MapVisualizer artist;
	private InputData data;
	private Vector2d jungleLowerLeftBound;
	private Vector2d jungleUpperRightBound;

	class GetStats
	{
		public int animalCount()
		{
			return zoo.getAnimalCount();
		}

		public int grassCount()
		{
			return garden.getGrassCount();
		}

		public byte[] dominantGenome()
		{
			return zoo.getDominantGenome().getGenes();
		}

		public int AverageEnergy()
		{
			return zoo.getAverageEnergy();
		}

		public int AverageLifespan()
		{
			return zoo.getAverageLifespanForDead();
		}

		public int getAverageChildrenCount()
		{
			return zoo.getAverageChildrenCount();
		}
	}
}
