package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.map.managers.AnimalManager;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;
import agh.cs.project.main.util.input.InputData;

public class Animal extends MapObject
{
	public Animal(WorldMap map, AnimalManager manager, Vector2d position, InputData data, int birthYear)
	{
		super(map, position);
		this.data = data;
		this.manager = manager;
		this.nextPosition = this.position;

		this.rotation = MapDirection.getRandom();
		this.birthYear = birthYear;
		this.energy = data.startEnergy;
		this.genome = new Genome();
	}

	public Animal(WorldMap map, AnimalManager manager, Vector2d position, InputData data, int birthYear, Genome genome, int startEnergy)
	{
		super(map, position);
		this.data = data;
		this.manager = manager;

		this.rotation = MapDirection.getRandom();
		this.birthYear = birthYear;
		this.energy = startEnergy;
		this.genome = genome;
	}

	public void move()
	{
		rotation = rotation.rotateBy(genome.getRandomGene());
		nextPosition = manager.correctPosition(position.add(rotation.toUnitVector()));
		manager.animalHasMoved(this, nextPosition);
		energy -= data.moveEnergy;
	}

	public void updatePosition()
	{
		position = nextPosition;
	}

	public void feed()
	{
		this.energy += data.plantEnergy;
	}

	public void feed(int foodEnergy)
	{
		this.energy += foodEnergy;
	}

	public boolean isAlive()
	{
		return this.energy > 0;
	}

	public void kill()
	{
		this.deathYear = map.getYear();
		this.position = new Vector2d(-1,-1);
	}

	public int getEnergy()
	{
		return this.energy;
	}

	public boolean canBreed()
	{
		return this.energy >= (0.5 * data.startEnergy);
	}

	public int takeBreedingEnergy()
	{
		int energyForBaby = energy / 4;
		energy = energy - energyForBaby;
		return energyForBaby;

	}

	public Genome getGenome()
	{
		return this.genome;
	}

	@Override
	public String toString()
	{
		return this.rotation.toString();
	}
	//static info about everything
	private InputData data;

	private Vector2d nextPosition;
	//own variables
	private int energy;
	private MapDirection rotation;
	private Genome genome;
	private AnimalManager manager;

	public final int birthYear;
	public int deathYear;
}
