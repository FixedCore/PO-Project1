package agh.cs.project.main.MapObjects;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;

public class Animal extends MapObject
{
	public Animal(WorldMap map, Vector2d position, Genome genome, MapDirection rotation)
	{
		super(map, position);
		this.rotation = rotation;
		this.genome = genome;
	}

	public Animal(WorldMap map, Vector2d position, Genome genome)
	{
		super(map, position);
		this.rotation = MapDirection.getRandom();
		this.genome = genome;
	}

	public void move()
	{
		rotation = rotation.rotateBy(genome.getRandomGene());
		position.add(rotation.toUnitVector());
	}

	private Genome genome;
	private MapDirection rotation;
}
