package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;

public class Animal extends MapObject
{
	public Animal(WorldMap map, Vector2d position, Genome genome, int startEnergy)
	{
		super(map, position);
		this.genome = genome;
		this.energy = startEnergy;
		this.rotation = MapDirection.getRandom();
	}

	public static void setMoveEnergy(int energy)
	{
		this.moveEnergy = energy;
	}

	public static void setPlantEnergy(int energy)
	{
		this.moveEnergy = energy;
	}

	public Animal(WorldMap map, Vector2d position, Genome genome, int energy)
	{
		super(map, position);
		this.rotation = MapDirection.getRandom();
		this.genome = genome;
		this.energy = energy;
	}

	public void move()
	{
		rotation = rotation.rotateBy(genome.getRandomGene());
		position.add(rotation.toUnitVector());
		energy -= moveEnergy;
	}

	public void feed(int foodEnergy)
	{
		this.energy += foodEnergy;
	}

	public void feed()
	{
		this.energy += plantEnergy;
	}

	//static constants
	private static int moveEnergy;
	private static int plantEnergy;

	//own variables
	private int energy;
	private Genome genome;
	private MapDirection rotation;
}
