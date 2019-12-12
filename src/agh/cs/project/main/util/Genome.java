package agh.cs.project.main.util;

import java.util.Arrays;
import java.util.Random;

public class Genome
{
	public Genome(byte[] inherited)
	{
		if(randomizer == null) randomizer = new Random();
		genes = Arrays.copyOf(inherited, GENECOUNT);
		Arrays.sort(genes);
	}

	public Genome()
	{
		if(randomizer == null) randomizer = new Random();
		genes = new byte[GENECOUNT];
		Arrays.sort(genes);
	}

	public byte[] getGenes()
	{
		return Arrays.copyOf(genes, GENECOUNT);
	}

	public int getRandomGene()
	{
		return genes[randomizer.nextInt(GENECOUNT)];
	}

	public static Genome getRandomGenome()
	{
		return new Genome();
	}


	private byte[] genes;
	public static final int GENECOUNT = 32;
	public static Random randomizer;
}
