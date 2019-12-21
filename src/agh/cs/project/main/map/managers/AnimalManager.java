package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.MapObjects.AnimalUpdate;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;
import agh.cs.project.main.util.input.InputData;

import java.util.*;

public class AnimalManager implements IAnimalChangeObserver
{
	public AnimalManager(WorldMap map, InputData data)
	{
		this.map = map;
		this.data = data;
		this.animals = new HashMap<>();
		this.graveyard = new LinkedList<>();
		if(randomizer == null) randomizer = new Random();
		this.maxAnimalCount = data.mapSize.x * data.mapSize.y;
		this.animalsToProcess = new LinkedList<>();
		this.genomeManager = new GenomeManager();
		this.energyManager = new EnergyManager();
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
		spawnAnimal(new Animal(map, this, this, v, data, map.getYear()), v);
	}

	public boolean spawnAnimal(Animal a, Vector2d pos)
	{
		if(animalCount == maxAnimalCount) return false;
		if(!animals.containsKey(pos)) animals.put(pos, new LinkedList<>());
		animals.get(pos).add(a);
		animals.get(pos).sort(Comparator.comparingInt(Animal::getEnergy).reversed());
		animalCount += 1;
		genomeManager.addAnimal(a);
		energyManager.addAnimal(a);
		return true;
	}

	public boolean cleanDeadAnimals()
	{
		boolean haveKilled = false;
		for(Vector2d v : animals.keySet())
		{
			if(animals.get(v).isEmpty()) animals.remove(v);
			else
			{
				for(Animal a : animals.get(v))
				{
					if(!a.isAlive())
					{
						animalsToProcess.add(new AnimalUpdate(a, new Vector2d(-1,-1)));
						//killAnimal(a);
						haveKilled = true;
					}
				}
			}
		}
		for(AnimalUpdate update : animalsToProcess)
		{
			killAnimal(update.animal);
			animalCount -= 1;
		}
		animalsToProcess.clear();
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
		createMovementQueue();
		processMovementQueue();
	}

	private void createMovementQueue()
	{
		for(List<Animal> l : animals.values())
		{
			for(Animal a : l)
			{
				a.move();
			}
		}
	}

	private void processMovementQueue()
	{
		for(AnimalUpdate update : animalsToProcess)
		{

			if(animals.containsKey(update.animal.getPosition())) {
				animals.get(update.animal.getPosition()).remove(update.animal);
				if(animals.get(update.animal.getPosition()).isEmpty()) animals.remove(update.animal.getPosition());
			}
			spawnAnimal(update.animal, update.newPosition);
			animalCount -= 1;
			update.animal.updatePosition();
		}
		animalsToProcess.clear();
	}

	public void animalHasSpawned(Animal a)
	{
		return;
	}

	public void animalHasMoved(Animal a, Vector2d newPosition)
	{
		if(!animals.containsKey(a.getPosition())) return;
		animalsToProcess.add(new AnimalUpdate(a, newPosition));
		return;
	}

	public void animalHasDied(Animal a)
	{
		return;
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

	public Genome getDominantGenome()
	{
		return genomeManager.getDominantGenome();
	}

	public int getAverageEnergy()
	{
		return energyManager.getAverageEnergy();
	}

	public int getAverageLifespanForDead()
	{
		int sumLifeSpan = 0;
		for(Animal a : graveyard)
		{
			sumLifeSpan += a.getLifespan();
		}
		return sumLifeSpan / graveyard.size();
	}

	public int getAverageChildrenCount()
	{
		int sumChildCount = 0;
		int sumParentCount = 0;
		for(Vector2d v : animals.keySet())
		{
			for(Animal a : animals.get(v))
			{
				sumParentCount += 1;
				sumChildCount += a.getChildrenCount();
			}
		}
		return sumChildCount / sumParentCount;
	}

	private WorldMap map;
	private InputData data;

	private int animalCount;
	private int maxAnimalCount;

	private Map<Vector2d, List<Animal>> animals;
	private List<AnimalUpdate> animalsToProcess;
	private GenomeManager genomeManager;
	private EnergyManager energyManager;

	private List<Animal> graveyard;

	private static Random randomizer;
}
