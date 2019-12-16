package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

import java.util.LinkedList;
import java.util.List;

public class EatingManager
{
	public EatingManager(WorldMap map, InputData data, GrassManager grasses, AnimalManager animals)
	{
		this.data = data;
		this.map = map;
		this.grasses = grasses;
		this.animals = animals;
	}

	public void feedAllAnimals()
	{
		for(Vector2d v : animals.getAllAnimalLocations())
		{
			if(grasses.hasGrassAt(v))
			{
				resolveFeeding(v);
			}
		}
	}

	private void resolveFeeding(Vector2d v)
	{
		if(animals.getAnimalsAt(v).isEmpty()) return;
		if(animals.getAnimalsAt(v).size() == 1)
		{
			animals.getAnimalsAt(v).get(0).feed();
			grasses.removeGrassAt(v);
			return;
		}
		if(animals.spotHasOneDominantAnimal(v))
		{
			animals.getDominantAnimal(v).feed();
			grasses.removeGrassAt(v);
			return;
		}
		else
		{
			List<Animal> dominant = animals.getMultipleDominantAnimals(v);
			for(Animal d : dominant)
			{
				d.feed(data.plantEnergy / dominant.size());
			}
			grasses.removeGrassAt(v);
			return;
		}
	}

	private InputData data;
	private WorldMap map;
	private GrassManager grasses;
	private AnimalManager animals;
}
