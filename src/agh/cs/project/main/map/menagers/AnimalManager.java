package agh.cs.project.main.map.menagers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

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
		spawnAnimal(new Animal(map, v, data, map.getYear()));
	}

	public boolean spawnAnimal(Animal a)
	{
		if(animalCount == maxAnimalCount) return false;
		if(!animals.containsKey(a.getPosition())) animals.put(a.getPosition(), new LinkedList<>());
		animals.get(a.getPosition()).add(a);
		animals.get(a.getPosition()).sort(Comparator.comparingInt(Animal::getEnergy));
		animalCount += 1;
		return true;
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
