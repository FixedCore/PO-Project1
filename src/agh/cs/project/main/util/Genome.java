package agh.cs.project.main.util;

import java.util.Arrays;
import java.util.Random;

public class Genome
{
	public Genome(byte[] inherited)
	{
		if(randomizer == null) randomizer = new Random();
		genes = Arrays.copyOf(inherited, GENECOUNT);
		fixMissingGenes();
		Arrays.sort(genes);
	}

	public Genome()
	{
		if(randomizer == null) randomizer = new Random();
		genes = new byte[GENECOUNT];
		for(byte b : genes) b = randomSingleGene();
		fixMissingGenes();
		Arrays.sort(genes);
	}

	private void fixMissingGenes()
	{
		while(!haveAllGenes())
		{
			randomizeRandomGene();
		}
	}

	private boolean haveAllGenes()
	{
		byte[] geneCounts = new byte[VALUECOUNT];
		for(byte b : genes) geneCounts[b] +=1;
		for(byte b : geneCounts) if (b==0) return false;
		return true;
	}

	private void randomizeRandomGene() {
		genes[randomizer.nextInt(GENECOUNT)] = randomSingleGene();
	}

	private byte randomSingleGene()
	{
		return (byte) randomizer.nextInt(VALUECOUNT);
	}

	public byte[] getGenes()
	{
		return Arrays.copyOf(genes, GENECOUNT);
	}

	public int getRandomGene()
	{
		return genes[randomizer.nextInt(GENECOUNT)];
	}

	@Override
	public boolean equals(Object other)
	{
		if(this==other) return true;
		if(!(other instanceof Genome)) return false;
		Genome tested = (Genome) other;
		for(int i = 0; i < GENECOUNT; i++)
		{
			if(this.genes[i] != tested.genes[i]) return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int sum = 0;
		for(int i=0;i<GENECOUNT;i++) sum += genes[i] * PrimeList.primes[i];
		return sum;
	}

	private byte[] genes;
	private static Random randomizer;

	public static final int GENECOUNT = 32;
	public static final int VALUECOUNT = 8;
}
