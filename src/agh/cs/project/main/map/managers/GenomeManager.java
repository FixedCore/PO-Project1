package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.movement.Vector2d;
import agh.cs.project.main.util.Genome;

import java.util.HashMap;
import java.util.Map;

public class GenomeManager implements IAnimalChangeObserver
{
	public GenomeManager()
	{
		this.genomeCount = new HashMap<>();
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
		if(!genomeCount.containsKey(a.getGenome())) genomeCount.put(a.getGenome(), 0);
		Integer localGenomeCount = genomeCount.get(a.getGenome());
		localGenomeCount += 1;
		genomeCount.put(a.getGenome(), localGenomeCount);
	}

	@Override
	public void animalHasMoved(Animal a, Vector2d newPosition)
	{
		return;
	}

	@Override
	public void animalHasDied(Animal a)
	{
		if(genomeCount.containsKey(a.getGenome()))
		{
			Integer localGenomeCount = genomeCount.get(a.getGenome());
			localGenomeCount -= 1;
			genomeCount.put(a.getGenome(), localGenomeCount);
			if(genomeCount.get(a.getGenome()) == 0) genomeCount.remove(a.getGenome());
		}
	}

	public Genome getDominantGenome()
	{
		int mostPopularGenomeCount = 0;
		Genome mostPopularGenome = null;
		for(Genome g : genomeCount.keySet())
		{
			if(genomeCount.get(g) >= mostPopularGenomeCount)
			{
				mostPopularGenome = g;
				mostPopularGenomeCount = genomeCount.get(g);
			}
		}
		return mostPopularGenome;
	}

	private Map<Genome, Integer> genomeCount;
}
