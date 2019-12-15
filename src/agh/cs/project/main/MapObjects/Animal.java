package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;
import agh.cs.project.main.util.input.InputData;

public class Animal extends MapObject
{
	public Animal(WorldMap map, Vector2d position, InputData data, int birthYear)
	{
		super(map, position);
		this.data = data;

		this.rotation = MapDirection.getRandom();
		this.birthYear = birthYear;
		this.energy = data.startEnergy;
		this.genome = new Genome();
	}


	public void move()
	{
		rotation = rotation.rotateBy(genome.getRandomGene());
		position.add(rotation.toUnitVector());
		correctPosition();
		energy -= data.moveEnergy;
	}

	private void correctPosition()
	{
		if(this.position.x >= data.mapSize.x) this.position = new Vector2d(0, this.position.y);
		if(this.position.x < 0) this.position = new Vector2d(data.mapSize.x-1, this.position.y);
		if(this.position.y >= data.mapSize.y) this.position = new Vector2d(this.position.x, 0);
		if(this.position.y < 0) this.position = new Vector2d(this.position.x, data.mapSize.y - 1);
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

	//own variables
	private int energy;
	private MapDirection rotation;
	private Genome genome;

	public final int birthYear;
	public int deathYear;
}
