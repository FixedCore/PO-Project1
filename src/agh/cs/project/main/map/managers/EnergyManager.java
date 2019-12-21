package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.movement.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class EnergyManager implements IAnimalChangeObserver
{
	public EnergyManager()
	{
		this.animals = new ArrayList<>();
	}

	public void addAnimal(Animal a)
	{
		a.addObserver(this);
	}

	public void removeAnimal(Animal a)
	{
		a.removeObserver(this);
	}

	@Override
	public void animalHasSpawned(Animal a)
	{
		animals.add(a);
	}

	@Override
	public void animalHasMoved(Animal a, Vector2d newPosition)
	{

	}

	@Override
	public void animalHasDied(Animal a)
	{
		animals.remove(a);
	}

	public int getAverageEnergy()
	{
		int energySum = 0;
		for(Animal a : animals)
		{
			energySum += a.getEnergy();
		}
		if(animals.isEmpty()) return -1;
		return energySum / animals.size();
	}

	private List<Animal> animals;
}
