package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Grass;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassManager
{
	public GrassManager(WorldMap map, InputData data)
	{
		this.map = map;
		this.data = data;
		this.maxGrassCountInJungle = data.jungleSize.x * data.jungleSize.y;
		this.maxGrassCountOutsideJungle = (data.mapSize.x * data.mapSize.y) - maxGrassCountInJungle;
		this.randomizer = new Random();
		this.grasses = new HashMap<>();
	}

	public boolean spawnGrassesInBothAreas()
	{
		return (spawnSingleRandomGrassInJungle() && spawnSingleRandomGrassOutsideJungle());
	}

	public void spawnManyRandomGrassesInJungle(int toSpawn)
	{
		for (int i = 0; i < toSpawn ; i++) spawnSingleRandomGrassInJungle();
	}

	public boolean spawnSingleRandomGrassInJungle()
	{
		if(grassCountInJungle >= maxGrassCountInJungle) return false;
		Vector2d v;
		do {
			v = new Vector2d(randomizer.nextInt(data.mapSize.x), randomizer.nextInt(data.mapSize.y));
		}while(map.isOccupied(v) || !map.isInJungle(v));
		grassCountInJungle += 1;
		return spawnGrass(new Grass(map, v));
	}

	public void spawnManyRandomGrassesOutsideJungle(int toSpawn)
	{
		for (int i = 0; i < toSpawn ; i++) spawnSingleRandomGrassOutsideJungle();
	}

	public boolean spawnSingleRandomGrassOutsideJungle()
	{
		if(grassCountOutsideJungle >= maxGrassCountOutsideJungle) return false;
		Vector2d v;
		do {
			v = new Vector2d(randomizer.nextInt(data.mapSize.x), randomizer.nextInt(data.mapSize.y));
		}while(map.isOccupied(v) || map.isInJungle(v));
		grassCountOutsideJungle += 1;
		return spawnGrass(new Grass(map, v));
	}

	private boolean spawnGrass(Grass g)
	{
		if(grasses.containsKey(g.getPosition())) return false;
		grasses.put(g.getPosition(), g);
		return true;
	}

	public boolean removeGrassAt(Vector2d v)
	{
		if(!hasGrassAt(v)) return false;
		grasses.remove(v);
		if(map.isInJungle(v)) grassCountInJungle -= 1;
		else grassCountOutsideJungle -= 1;
		return true;
	}

	public boolean removeGrass(Grass g)
	{
		if(!hasGrassAt(g.getPosition())) return false;
		grasses.remove(g.getPosition());
		if(map.isInJungle(g.getPosition())) grassCountInJungle -= 1;
		else grassCountOutsideJungle -= 1;
		return true;
	}

	public boolean hasGrassAt(Vector2d v)
	{
		return grasses.containsKey(v);
	}

	public Grass getGrassAt(Vector2d v)
	{
		return grasses.get(v);
	}

	public int getGrassCount()
	{
		return grassCountInJungle + grassCountOutsideJungle;
	}

	private Map<Vector2d, Grass> grasses;
	private int grassCountInJungle, grassCountOutsideJungle, maxGrassCountInJungle, maxGrassCountOutsideJungle;
	private WorldMap map;
	private InputData data;
	private Random randomizer;
}
