package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

import java.lang.invoke.VolatileCallSite;
import java.util.*;

public class AnimalManager
{
	public AnimalManager(WorldMap map, InputData data)
	{
		this.map = map;
		this.data = data;
		this.animals = new HashMap<>();
		this.graveyard = new LinkedList<>();
		if(randomizer == null) randomizer = new Random();
		this.maxAnimalCount = data.jungleSize.x * data.jungleSize.y;
	}

	public void spawnManyRandomAnimals(int toSpawn)
	{
		for(int i=0; i < toSpawn; i++) spawnSingleRandomAnimal();

	}

	public void spawnSingleRandomAnimal()
	{
		Vector2d v;
		do {
			v = new Vector2d(randomizer.nextInt(data.mapSize.x), randomizer.nextInt(data.mapSize.y));
		}while(animals.containsKey(v));
		spawnAnimal(new Animal(map,this, v, data, map.getYear()), v);
	}

	public boolean spawnAnimal(Animal a, Vector2d pos)
	{
		if(animalCount == maxAnimalCount) return false;
		if(!animals.containsKey(pos)) animals.put(pos, new LinkedList<>());
		animals.get(pos).add(a);
		animals.get(pos).sort(Comparator.comparingInt(Animal::getEnergy).reversed());
		animalCount += 1;
		return true;
	}

	public boolean cleanDeadAnimals()
	{
		boolean haveKilled = false;
		for(Vector2d v : animals.keySet())
		{
			if(animals.get(v).isEmpty()) animals.put(v, null);
			else
			{
				for(Animal a : animals.get(v))
				{
					if(!a.isAlive())
					{
						killAnimal(a);
						haveKilled = true;
					}
				}
			}
		}
		return haveKilled;
	}

	public boolean killAnimal(Animal a)
	{
		if(!animals.containsKey(a.getPosition())) return false;
		if(!animals.get(a.getPosition()).contains(a)) return false;
		graveyard.add(a);
		animals.get(a.getPosition()).remove(a);
		if(animals.get(a.getPosition()).isEmpty()) animals.put(a.getPosition(), null);
		a.kill();
		return true;
	}

	public void letAnimalsMove()
	{
		for(List<Animal> l : animals.values())
		{
			for(Animal a : l)
			{
				a.move();
			}
		}
	}


	public boolean animalHasMoved(Animal a, Vector2d newPosition)
	{
		if(!animals.containsKey(a.getPosition())) return false;
		animals.get(a.getPosition()).remove(a);
		spawnAnimal(a, newPosition);
		return true;
	}

	public Set<Vector2d> getAllAnimalLocations()
	{
		return animals.keySet();
	}

	public boolean hasAnimalAt(Vector2d pos)
	{
		return animals.containsKey(pos);
	}

	public List<Animal> getAnimalsAt(Vector2d pos)
	{
		return animals.get(pos);
	}

	public int getAnimalCount() {
		return animalCount;
	}

	private WorldMap map;
	private InputData data;

	private int animalCount;
	private int maxAnimalCount;

	private Map<Vector2d, List<Animal>> animals;

	private List<Animal> graveyard;

	private static Random randomizer;
}