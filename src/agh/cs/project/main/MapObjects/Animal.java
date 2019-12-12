package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;

public class Animal extends MapObject
{
	public Animal(WorldMap map, Vector2d position, Genome genome, MapDirection rotation, int energy)
	{
		super(map, position);
		this.rotation = rotation;
		this.genome = genome;
		this.energy = energy;
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
		energy -= 1;
	}

	private int energy;
	private Genome genome;
	private MapDirection rotation;
}
