package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.MapDirection;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;
import agh.cs.project.main.util.input.InputData;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BreedingManager
{
	public BreedingManager(WorldMap map, InputData data, AnimalManager animals)
	{
		this.data = data;
		this.map = map;
		this.animals = animals;
		if(randomizer == null) randomizer = new Random();
	}

	public void breedAllAnimals()
	{
		List<Animal> toBeSpawned = new LinkedList<>();
		for(Vector2d v : animals.getAllAnimalLocations())
		{
			if(animals.getAnimalCountAt(v) >= 2)
			{
				Animal a = breedAnimalsAt(v);
				if(a != null) toBeSpawned.add(a);
			}
		}
		for(Animal a : toBeSpawned)
		{
			animals.spawnAnimal(a, a.getPosition());
		}
	}

	private Animal breedAnimalsAt(Vector2d v)
	{
		List<Animal> dominant = animals.getMultipleDominantAnimals(v);
		if(dominant.size() <= 2)
		{
			return breedTwoAnimals(animals.getAnimalsAt(v).get(0), animals.getAnimalsAt(v).get(1));
		}
		else
		{
			int index1, index2;
			index1 = randomizer.nextInt(dominant.size());
			do {
				index2 = randomizer.nextInt(dominant.size());
			}while(index1 == index2);
			return breedTwoAnimals(animals.getAnimalsAt(v).get(index1), animals.getAnimalsAt(v).get(index2));
		}
	}

	private Animal breedTwoAnimals(Animal a, Animal b)
	{
		if((a.getEnergy() < (data.startEnergy * 0.5)) || b.getEnergy() < (data.startEnergy * 0.5 )) return null;
		int babyBirthYear = map.getYear();
		Vector2d babyPosition = resolveBabyPosition(a.getPosition());
		Genome babyGenome = resolveGenome(a, b);
		int babyEnergy = a.takeBreedingEnergy() + b.takeBreedingEnergy();
		return new Animal(map, animals, babyPosition, data, babyBirthYear, babyGenome, babyEnergy);
	}

	private Vector2d resolveBabyPosition(Vector2d centerPosition)
	{
		MapDirection offset = MapDirection.getRandom();
		for (int i = 0; i < MapDirection.DIRECTIONCOUNT; i++)
		{
			if(!animals.hasAnimalAt(animals.correctPosition(centerPosition.add(offset.toUnitVector())))) break;
			offset = offset.toRight();
		}
		return animals.correctPosition(centerPosition.add(offset.toUnitVector()));
	}

	private Genome resolveGenome(Animal a, Animal b)
	{
		int splicePoint1, splicePoint2;
		splicePoint1 = randomizer.nextInt(Genome.GENECOUNT - 1) + 1;
		splicePoint2 = randomizer.nextInt(Genome.GENECOUNT - splicePoint1 - 1) + splicePoint1 + 1;

		Animal src1 = randomizer.nextBoolean() ? a : b;
		Animal src2 = randomizer.nextBoolean() ? a : b;
		Animal src3;
		if(src1 == src2 && src1 == a) src3 = b;
		else if(src1 == src2 && src1 == b) src3 = a;
		else src3 = randomizer.nextBoolean() ? a : b;


		byte[] newGenes = new byte[Genome.GENECOUNT];

		byte[] sourceGenes = src1.getGenome().getGeneSection(0,splicePoint1);
		if (splicePoint1 >= 0) System.arraycopy(sourceGenes, 0, newGenes, 0, splicePoint1);

		sourceGenes = src2.getGenome().getGeneSection(splicePoint1, splicePoint2);
		if (splicePoint1 >= 0) System.arraycopy(sourceGenes, 0, newGenes, splicePoint1, splicePoint2 - splicePoint1);

		sourceGenes = src3.getGenome().getGeneSection(splicePoint2, Genome.GENECOUNT);
		if (splicePoint2 >= 0) System.arraycopy(sourceGenes, 0, newGenes, splicePoint2, Genome.GENECOUNT - splicePoint2);

		return new Genome(newGenes);
	}

	private static Random randomizer;
	private InputData data;
	private WorldMap map;
	private AnimalManager animals;
}
