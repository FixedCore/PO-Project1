package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.MapObjects.AnimalUpdate;
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
		this.animalsToMove = new LinkedList<>();
	}

	public void spawnManyRandomAnimals(int toSpawn)
	{
		for(int i=0; i < toSpawn && animalCount < maxAnimalCount; i++) spawnSingleRandomAnimal();
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
		if(animals.get(a.getPosition()).isEmpty()) animals.remove(a.getPosition());
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
		for(AnimalUpdate update : animalsToMove)
		{

			if(animals.containsKey(update.animal.getPosition())) {
				animals.get(update.animal.getPosition()).remove(update.animal);
				if(animals.get(update.animal.getPosition()).isEmpty()) animals.remove(update.animal.getPosition());
			}
			spawnAnimal(update.animal, update.newPosition);
			update.animal.updatePosition();
		}
		animalsToMove.clear();
	}


	public boolean animalHasMoved(Animal a, Vector2d newPosition)
	{
		if(!animals.containsKey(a.getPosition())) return false;
		animalsToMove.add(new AnimalUpdate(a, newPosition));
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

	public int getAnimalCountAt(Vector2d pos)
	{
		if(!animals.containsKey(pos)) return 0;
		return animals.get(pos).size();
	}

	public int getAnimalCount() {
		return animalCount;
	}

	public boolean spotHasOneDominantAnimal(Vector2d v)
	{
		if(animals.get(v).size() < 2) return true;
		return !(animals.get(v).get(0) == animals.get(v).get(1));
	}

	public Animal getDominantAnimal(Vector2d v)
	{
		return animals.get(v).get(0);
	}

	public List<Animal> getMultipleDominantAnimals(Vector2d v)
	{
		List<Animal> dominant = new LinkedList<>();
		int maxEnergy = animals.get(v).get(0).getEnergy();
		for(Animal a : animals.get(v))
		{
			if(a.getEnergy() == maxEnergy) dominant.add(a);
		}
		return dominant;
	}

	public Vector2d correctPosition(Vector2d newPosition)
	{
		if(newPosition.x >= data.mapSize.x) newPosition = new Vector2d(0, newPosition.y);
		if(newPosition.x < 0) newPosition = new Vector2d(data.mapSize.x-1, newPosition.y);
		if(newPosition.y >= data.mapSize.y) newPosition = new Vector2d(newPosition.x, 0);
		if(newPosition.y < 0) newPosition = new Vector2d(newPosition.x, data.mapSize.y - 1);
		return newPosition;
	}

	private WorldMap map;
	private InputData data;

	private int animalCount;
	private int maxAnimalCount;

	private Map<Vector2d, List<Animal>> animals;
	private List<AnimalUpdate> animalsToMove;

	private List<Animal> graveyard;

	private static Random randomizer;
}
