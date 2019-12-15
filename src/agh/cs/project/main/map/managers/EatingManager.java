package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.input.InputData;

import java.util.LinkedList;
import java.util.List;

public class EatingManager
{
	public EatingManager(InputData data, WorldMap map, GrassManager grasses, AnimalManager animals)
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
				resolveFeeding(animals.getAnimalsAt(v));
			}
		}
	}

	private void resolveFeeding(List<Animal> a)
	{
		if(a.isEmpty()) return;
		if(a.size() == 1)
		{
			a.get(0).feed();
			return;
		}
		if(spotHasOneDominantAnimal(a))
		{
			getDominantAnimal(a).feed();
			return;
		}
		else
		{
			List<Animal> dominant = getMultipleDominantAnimals(a);
			for(Animal d : dominant)
			{
				d.feed(data.plantEnergy / dominant.size());
			}
			return;
		}
	}

	private boolean spotHasOneDominantAnimal(List<Animal> a)
	{
		if(a.size() < 2) return true;
		return !(a.get(0) == a.get(1));
	}

	private Animal getDominantAnimal(List<Animal> a)
	{
		return a.get(0);
	}

	private List<Animal> getMultipleDominantAnimals(List<Animal> a)
	{
		List<Animal> d = new LinkedList<>();
		int maxEnergy = a.get(0).getEnergy();
		for(Animal e : a)
		{
			if(e.getEnergy() == maxEnergy) d.add(e);
			else break;
		}
		return d;
	}

	private InputData data;
	private WorldMap map;
	private GrassManager grasses;
	private AnimalManager animals;
}
